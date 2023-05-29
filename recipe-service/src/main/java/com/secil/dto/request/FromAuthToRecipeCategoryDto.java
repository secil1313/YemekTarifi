package com.secil.dto.request;

import com.secil.repository.entity.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FromAuthToRecipeCategoryDto {
    private String username;
    private String password;
    private String email;
    private ERole role;
}
