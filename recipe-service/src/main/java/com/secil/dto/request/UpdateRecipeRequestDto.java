package com.secil.dto.request;

import com.secil.repository.entity.Ingredient;
import com.secil.repository.entity.NutritionalValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRecipeRequestDto {
    private String token;
    private String recipeId;
    private String recipeName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String recipeSteps;
    private String photo;
    private List<String> commentId;
    private List<Ingredient> ingredient;
    private NutritionalValue nutritionalValue;
    private List<String> categoryIds;
}
