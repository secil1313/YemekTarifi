package com.secil.service;

import com.secil.dto.request.*;
import com.secil.dto.response.GetUserForFavoriteCategoryResponseDto;
import com.secil.exception.ErrorType;
import com.secil.exception.UserProfileManagerException;
import com.secil.manager.IAuthManager;
import com.secil.manager.IRecipeManager;
import com.secil.mapper.IUserProfileMapper;
import com.secil.repository.IUserprofileRepository;
import com.secil.repository.entity.UserProfile;
import com.secil.repository.entity.enums.ERole;
import com.secil.repository.entity.enums.EStatus;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserProfileService extends ServiceManager<UserProfile, String> {
    private final IUserprofileRepository userprofileRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final IAuthManager authManager;
    private final PasswordEncoder passwordEncoder;
    private final IRecipeManager recipeManager;

    public UserProfileService(IUserprofileRepository userprofileRepository, JwtTokenProvider jwtTokenProvider, IAuthManager authManager, PasswordEncoder passwordEncoder, IRecipeManager recipeManager) {
        super(userprofileRepository);
        this.userprofileRepository = userprofileRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authManager = authManager;
        this.passwordEncoder = passwordEncoder;
        this.recipeManager = recipeManager;
    }

    public Boolean createUser(NewCreateUserRequestDto dto) {
        try {
            save(IUserProfileMapper.INSTANCE.fromDtoToUserProfile(dto));
            return true;
        } catch (Exception e) {
            throw new UserProfileManagerException(ErrorType.COULDNT_SAVE);
        }
    }

    public Boolean activateStatus(Long authId) {
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId);
        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.COULDNT_FOUND_AUTHID);
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        update(userProfile.get());
        return true;
    }

    public Boolean forgotPassword(UserProfileChangePasswordRequestDto dto) {
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(dto.getAuthId());
        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setPassword(dto.getPassword());
        update(userProfile.get());
        return true;
    }

    public UserProfile updateUser(UserProfileUpdateRequestDto dto, String token) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId.get());

        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        update(IUserProfileMapper.INSTANCE.updateFromDtoToUserProfile(dto, userProfile.get()));
        authManager.updateUser(IUserProfileMapper.INSTANCE.userUpdateToAuthUpdate(userProfile.get()));
        return userProfile.get();
    }

    public Boolean delete(String token) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId.get());
        if (userProfile.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
        userProfile.get().setStatus(EStatus.DELETED);
        update(userProfile.get());
        authManager.delete(authId.get());
        return true;
    }

    public Boolean passwordChange(PasswordChangeDto dto) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(dto.getToken());
        if (authId.isEmpty()) {
            throw new UserProfileManagerException(ErrorType.INVALID_TOKEN);
        }
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId.get());
        if (userProfile.isPresent()) {
            if (passwordEncoder.matches(dto.getOldPassword(), userProfile.get().getPassword())) {
                String newPass = dto.getNewPassword();
                userProfile.get().setPassword(passwordEncoder.encode(newPass));
                userprofileRepository.save(userProfile.get());
                authManager.passwordChange(IUserProfileMapper.INSTANCE.fromUserProfileToAuthPasswordChangeDto(userProfile.get()));
                return true;
            } else {
                throw new UserProfileManagerException(ErrorType.PASSWORD_ERROR);
            }
        } else {
            throw new UserProfileManagerException(ErrorType.USER_NOT_FOUND);
        }
    }

    public SendUsernameAndIdRequestDto sendfindOptionalByAuthId(Long authId) {
        Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId);
        SendUsernameAndIdRequestDto dto = IUserProfileMapper.INSTANCE.userProfileToSendUsernameAndIdRequestDto(userProfile.get());
        return dto;
    }

    public Boolean takeRecipeToFavorites(String recipeId, String token) {
        Optional<Long> authId = jwtTokenProvider.getIdFromToken(token);
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        if (role.get().equals(ERole.USER.toString())) {
            Optional<UserProfile> userProfile = userprofileRepository.findOptionalByAuthId(authId.get());
            userProfile.get().getRecipeId().add(recipeId);
            SendRecipeAndCategoryId id = recipeManager.sendRecipeAndCategoryIdUserToRecipe(recipeId).getBody();
            id.getCategoryId().forEach(x -> {
                if (!userProfile.get().getCategoryId().contains(x))
                    userProfile.get().getCategoryId().add(x);
            });
            update(userProfile.get());
        } else {
            throw new UserProfileManagerException(ErrorType.NOT_USER);
        }
        return true;
    }

    public Set<GetUserForFavoriteCategoryResponseDto> getUserWithFavoriteCategoryFromRecipeService(List<String> categoryId) {
        List<UserProfile> userProfileList = userprofileRepository.findAll();
        Set<GetUserForFavoriteCategoryResponseDto> userSet = new HashSet<>();
        userProfileList.forEach(x -> {
            categoryId.forEach(y -> {
                if (x.getCategoryId().contains(y)) {
                    userSet.add(IUserProfileMapper.INSTANCE.userProfileToGetUserForFavoriteCategoryResponseDto(x));
                }
            });
        });
        return userSet;
    }


}
