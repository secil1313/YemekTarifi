package com.secil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewCreateUserRequestDto {
    private Long authId;
    private String username;
    @Email(message = "Geçerli bir email adresi giriniz.")
    @NotBlank(message = "Email boş bırakılamaz.")
    private String email;
    private String password;

}
