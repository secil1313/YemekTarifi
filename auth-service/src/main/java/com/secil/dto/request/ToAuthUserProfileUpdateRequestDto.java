package com.secil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToAuthUserProfileUpdateRequestDto {
    private Long authId;
    private String name;
    private String surname;
    private String avatar;
    @Email(message = "Lütfen geçerli bir email giriniz.")
    @NotBlank(message = "Eposta bilgilerini boş bırakmayınız.")
    private String email;
    @NotBlank(message = "Kullanıcı adını boş bırakmayınız.")
    @Size(min = 3, max = 20, message = "Kullanıcı adı en az 3 en fazla 20 karakter olabilir.")
    private String username;
    private String province;
    private String district;
    private String neighbourhood;
    private String street;
    private String country;
    private Integer buildingNo;
    private Integer apartmentNumber;
    private Integer postCode;

}
