package com.secil.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class AddPointRequestDto {
    @Min(0)
    @Max(5)
    private Double point;
    private String recipeId;
}
