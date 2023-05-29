package com.secil.dto.request;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequestDto {
    @NotBlank(message = "Kullanıcı adını boş bırakmayınız.")
    @Size(min = 3, max = 20, message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    private String username;

    @Email(message = "Lütfen geçerli bir email giriniz.")
    @NotBlank(message = "Eposta bilgilerini boş bırakmayınız.")
    private String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$",
            message = "Şifre en az bir büyük, bir küçük, harf, rakam, ve özel karakterden oluşmalıdır.")
    @NotBlank(message = "Şifre bilgilerini boş bırakmayınız.")
    @Size(min = 8, max = 32, message = "Şifre en az 8 en çok 32 karakter olabilir.")
    private String password;
    private String repassword;

}
