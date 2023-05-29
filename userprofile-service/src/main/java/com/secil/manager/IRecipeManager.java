package com.secil.manager;

import com.secil.dto.request.SendRecipeAndCategoryId;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080/api/v1/recipe", name = "userprofile-recipe")

public interface IRecipeManager {
    @GetMapping("/sendRecipeAndCategoryIdUserToRecipe/{recipeId}")
    public ResponseEntity<SendRecipeAndCategoryId> sendRecipeAndCategoryIdUserToRecipe(@PathVariable String recipeId);
}
