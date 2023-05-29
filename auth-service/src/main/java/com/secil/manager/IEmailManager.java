package com.secil.manager;

import com.secil.dto.response.ForgotPasswordMailResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.secil.constant.ApiUrls.FORGOT_PASSWORD;

@FeignClient(url = "http://localhost:8070/api/v1/mail", name = "auth-mail")
public interface IEmailManager {
    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPasswordMail(@RequestBody ForgotPasswordMailResponseDto dto);
}
