package com.secil.controller;

import com.secil.dto.request.*;
import com.secil.dto.response.RegisterResponseDto;
import com.secil.service.AuthService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import static com.secil.constant.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(AUTH)
public class AuthController {
    private final AuthService authService;
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        return ResponseEntity.ok(authService.register(dto));
    }
    @PostMapping(ACTIVATE_STATUS)
    public ResponseEntity<Boolean> activateStatus(@RequestBody @Valid ActivateRequestDto dto){
        return ResponseEntity.ok(authService.activateStatus(dto));
    }
    @PostMapping(LOGIN)
    public ResponseEntity<String> login(@RequestBody LoginRequestDto dto){
        return ResponseEntity.ok(authService.login(dto));
    }
    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword( String email){
        return ResponseEntity.ok(authService.forgotPassword(email));
    }
    @Hidden
    @DeleteMapping(DELETE_BY_ID + "/{authId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long authId){
        return ResponseEntity.ok(authService.delete(authId));
    }
    @Hidden
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody @Valid ToAuthUserProfileUpdateRequestDto dto){
        return ResponseEntity.ok(authService.updateUser(dto));
    }
    @Hidden
    @PutMapping(PASSWORD_CHANGE)
    public ResponseEntity<Boolean> passwordChange(@RequestBody FromUserProfilePasswordChangeDto dto){
        return ResponseEntity.ok(authService.passwordChange(dto));
    }


}
