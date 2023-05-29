package com.secil.controller;

import com.secil.dto.ForgotPasswordMailResponseDto;
import com.secil.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.secil.constant.ApiUrls.*;

@RequestMapping("api/v1/mail")
@RestController
@RequiredArgsConstructor
public class MailSenderController {
    private final MailSenderService mailSenderService;
    @PostMapping(FORGOT_PASSWORD)
    public ResponseEntity<Boolean> forgotPasswordMail(@RequestBody ForgotPasswordMailResponseDto dto){
       return ResponseEntity.ok(mailSenderService.sendMailForgotPassword(dto));
    }

}
