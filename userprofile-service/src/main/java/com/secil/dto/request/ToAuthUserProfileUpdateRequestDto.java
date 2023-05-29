package com.secil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToAuthUserProfileUpdateRequestDto {
    private Long authId;
    private String name;
    private String surname;
    private String avatar;
    private String email;
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
