package com.secil.manager;

import com.secil.dto.request.SendUsernameAndIdRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url="http://localhost:8090/api/v1/user_profile",name = "userprofile-comment")
public interface IUserProfileManager {
    @GetMapping("/send-username-userId/{authId}")
    public ResponseEntity<SendUsernameAndIdRequestDto> send(@PathVariable Long authId);

}
