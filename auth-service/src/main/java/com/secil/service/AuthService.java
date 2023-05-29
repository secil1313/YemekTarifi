package com.secil.service;

import com.secil.dto.request.*;
import com.secil.dto.response.ForgotPasswordMailResponseDto;
import com.secil.dto.response.RegisterResponseDto;
import com.secil.exception.ErrorType;
import com.secil.exception.AuthManagerException;
import com.secil.manager.ICategoryManager;
import com.secil.manager.IEmailManager;
import com.secil.manager.IUserProfileManager;
import com.secil.mapper.IAddressMapper;
import com.secil.mapper.IAuthMapper;
import com.secil.rabbitmq.producer.RegisterMailProducer;
import com.secil.repository.IAuthRepository;
import com.secil.repository.entity.Address;
import com.secil.repository.entity.Auth;
import com.secil.repository.entity.enums.EStatus;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static com.secil.utility.CodeGenerator.generateCode;

@Service
public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final IUserProfileManager userProfileManager;
    private final RegisterMailProducer registerMailProducer;
    private final IEmailManager emailManager;
    private final AddressService addressService;
    private final ICategoryManager categoryManager;

    public AuthService(IAuthRepository authRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, IUserProfileManager userProfileManager, RegisterMailProducer registerMailProducer, IEmailManager emailManager, AddressService addressService, ICategoryManager categoryManager) {
        super(authRepository);
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userProfileManager = userProfileManager;
        this.registerMailProducer = registerMailProducer;
        this.emailManager = emailManager;
        this.addressService = addressService;
        this.categoryManager = categoryManager;
    }

    public RegisterResponseDto register(RegisterRequestDto dto) {
        Auth auth = IAuthMapper.INSTANCE.fromRequestDtoToUser(dto);
        if (dto.getPassword().equals(dto.getRepassword())) {
            auth.setActivationCode(generateCode());
            auth.setPassword(passwordEncoder.encode(dto.getPassword()));
            save(auth);
            userProfileManager.createUser(IAuthMapper.INSTANCE.fromAuthToNewCreateUserDto(auth));
            registerMailProducer.sendActivationCode(IAuthMapper.INSTANCE.fromAuthToRegisterMailModel(auth));
        } else {
            throw new AuthManagerException(ErrorType.PASSWORD_ERROR);
        }
        RegisterResponseDto responseDto = IAuthMapper.INSTANCE.authToRegisterResponseDto(auth);
        return responseDto;
    }

    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth = findById(dto.getId());
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        } else if (auth.get().getActivationCode().equals(dto.getActivateCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            update(auth.get());
            userProfileManager.activateStatus(auth.get().getAuthId());
            return true;
        }
        throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> auth = authRepository.findOptionalByUsername(dto.getUsername());
        if (auth.isEmpty() || !passwordEncoder.matches(dto.getPassword(), auth.get().getPassword())) {
            throw new AuthManagerException(ErrorType.LOGIN_ERROR);
        }
        if (!auth.get().getStatus().equals(EStatus.ACTIVE)) {
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
        return jwtTokenProvider.createToken(auth.get().getAuthId(), auth.get().getRole())
                .orElseThrow(() -> {
                    throw new AuthManagerException(ErrorType.TOKEN_NOT_CREATED);
                });
    }

    public Boolean forgotPassword(String email) {
        Optional<Auth> auth = authRepository.findOptionalByEmail(email);
        if (auth.isPresent()) {
            String randomPassword = UUID.randomUUID().toString();
            auth.get().setPassword(passwordEncoder.encode(randomPassword));
            save(auth.get());
            ForgotPasswordMailResponseDto dto = ForgotPasswordMailResponseDto.builder()
                    .password(randomPassword)
                    .email(email)
                    .build();
            emailManager.forgotPasswordMail(dto);
            UserProfileChangePasswordRequestDto requestDto= UserProfileChangePasswordRequestDto.builder()
             .authId(auth.get().getAuthId())
             .password(auth.get().getPassword())
             .build();
             userProfileManager.forgotPassword(requestDto);
            return true;
        } else {
            if (auth.get().getStatus().equals(EStatus.DELETED)) {
                throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
            }
            throw new AuthManagerException(ErrorType.ACTIVATE_CODE_ERROR);
        }
    }

    public Boolean delete(Long authId) {
        Optional<Auth> auth = authRepository.findById(authId);
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setStatus(EStatus.DELETED);
        update(auth.get());
        return true;
    }

    public Boolean updateUser(ToAuthUserProfileUpdateRequestDto dto) {
        System.out.println(dto);
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        if (auth.get().getAddressId() != null) {
            Optional<Address> address = addressService.findById(auth.get().getAddressId());
            if (address.isEmpty())
                throw new AuthManagerException(ErrorType.ADRESS_NOT_FOUND);
            addressService.update(IAddressMapper.INSTANCE.updateAddress(dto, address.get()));
        } else {
            Address address = IAddressMapper.INSTANCE.saveAddress(dto);
            addressService.save(address);
            auth.get().setAddressId(address.getAddressId());
        }
        update(IAuthMapper.INSTANCE.updateAuth(dto, auth.get()));
        return true;
    }

    public Boolean passwordChange(FromUserProfilePasswordChangeDto dto) {
        Optional<Auth> auth = authRepository.findById(dto.getAuthId());
        if (auth.isEmpty()) {
            throw new AuthManagerException(ErrorType.USER_NOT_FOUND);
        }
        auth.get().setPassword(passwordEncoder.encode(dto.getPassword()));
        authRepository.save(auth.get());
        return true;
    }

}
