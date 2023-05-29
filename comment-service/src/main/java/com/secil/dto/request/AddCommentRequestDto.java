package com.secil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddCommentRequestDto {
    @NotBlank(message = "Boş yorum atamazsınız.")
    private String comment;
    private String recipeId;
}
