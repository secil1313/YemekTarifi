package com.secil.manager;

import com.secil.dto.request.NewCreateUserRequestDto;
import com.secil.dto.request.UserProfileChangePasswordRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.secil.constant.ApiUrls.*;

@FeignClient(url = "http://localhost:8090/api/v1/user_profile",name="auth-userprofile")
public interface IUserProfileManager {
    @PostMapping("/create")
    public ResponseEntity<Boolean> createUser(@RequestBody NewCreateUserRequestDto dto);
    @PutMapping("/activate-status/{authId}")
    public ResponseEntity<Boolean> activateStatus(@PathVariable Long authId);
    @PutMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPassword(@RequestBody UserProfileChangePasswordRequestDto dto);



}
