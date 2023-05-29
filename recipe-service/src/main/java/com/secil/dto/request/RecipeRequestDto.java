package com.secil.dto.request;

import com.secil.repository.entity.Category;
import com.secil.repository.entity.Ingredient;
import com.secil.repository.entity.NutritionalValue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecipeRequestDto {
    private String recipeName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String recipeSteps;
    private String photo;
    private List<Ingredient> ingredient=new ArrayList<>();
    private NutritionalValue nutritionalValue;
    private List<String> categoryIds=new ArrayList<>();
}
