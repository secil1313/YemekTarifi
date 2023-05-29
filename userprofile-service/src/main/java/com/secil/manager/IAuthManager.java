package com.secil.manager;

import com.secil.dto.request.PasswordChangeDto;
import com.secil.dto.request.ToAuthPasswordChangeDto;
import com.secil.dto.request.ToAuthUserProfileUpdateRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.secil.constant.ApiUrls.*;

@FeignClient(url = "http://localhost:8050/api/v1/auth", name = "userprofile-auth")

public interface IAuthManager {
    @DeleteMapping(DELETE_BY_ID + "/{authId}")
    public ResponseEntity<Boolean> delete(@PathVariable Long authId);
    @PutMapping(UPDATE)
    public ResponseEntity<Boolean> updateUser(@RequestBody ToAuthUserProfileUpdateRequestDto dto);
    @PutMapping(PASSWORD_CHANGE)
    public ResponseEntity<Boolean> passwordChange(@RequestBody ToAuthPasswordChangeDto dto);
}
