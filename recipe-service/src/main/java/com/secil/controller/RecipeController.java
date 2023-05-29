package com.secil.controller;

import com.secil.dto.request.RecipeRequestDto;
import com.secil.dto.request.SendRecipeAndCategoryId;
import com.secil.dto.request.UpdateRecipeRequestDto;
import com.secil.repository.entity.Recipe;
import com.secil.service.RecipeService;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.secil.constant.ApiUrls.*;
@RestController
@RequiredArgsConstructor
@RequestMapping(RECIPE)
public class RecipeController {
    private final RecipeService recipeService;
    @PostMapping(ADD+"/{token}")
    public ResponseEntity<Recipe> addRecipe(@RequestBody RecipeRequestDto dto, @PathVariable String token){
        return ResponseEntity.ok(recipeService.addRecipe(dto,token));
    }
    @PostMapping(DELETE+"/{recipeId}/{token}")
    public ResponseEntity<Boolean> deleteRecipe(@PathVariable String recipeId, @PathVariable String token){
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId,token));
    }
    @PostMapping(UPDATE)
    public ResponseEntity<Boolean> updateRecipe(@RequestBody UpdateRecipeRequestDto dto){
        return ResponseEntity.ok(recipeService.updateRecipe(dto));
    }
    @PostMapping(RECIPE_TO_COMMENT_MANAGER+"/{commandId}/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> recipeToCommentManager(@PathVariable String commandId, @PathVariable String recipeId){
        return ResponseEntity.ok(recipeService.recipeToCommentManager(commandId,recipeId));
    }
    @GetMapping(EXIST_BY_RECIPEID+"/{recipeId}")
    @Hidden
    public ResponseEntity<Boolean> existByRecipeId(@PathVariable String recipeId){
        return ResponseEntity.ok(recipeService.existByRecipeId(recipeId));
    }
    @GetMapping(EXIST_BY_POINTID_COMMENTID+"/{recipeId}/{pointId}")
    @Hidden
    public ResponseEntity<Boolean> existsByPointIdAndRecipeId(@PathVariable String recipeId,@PathVariable String pointId){
        return ResponseEntity.ok(recipeService.existsByPointIdAndRecipeId(recipeId,pointId));
    }
    @DeleteMapping(DELETE_COMMENT_FOR_DELETED_COMMENT_IN_RECIPE+"/{recipeId}/{commentId}")
    @Hidden
    public ResponseEntity<Boolean> deleteCommentFromRecipe(@PathVariable String recipeId, @PathVariable String commentId){
        return ResponseEntity.ok(recipeService.deleteCommentForDeletedCommentInRecipeService(recipeId, commentId));
    }
    @Hidden
    @DeleteMapping(DELETE_POINT_FOR_DELETED_POINT_IN_RECIPE+"/{recipeId}/{pointId}")
    public ResponseEntity<Boolean> deletePointFromRecipe(@PathVariable String recipeId, @PathVariable String pointId){
        return ResponseEntity.ok(recipeService.deletePointForDeletedPointInRecipeService(recipeId,pointId));
    }
    @Hidden
    @PostMapping(RECIPE_TO_POINT_MANAGER+"/{pointId}/{recipeId}")
    public ResponseEntity<Boolean> recipeToPointManager(@PathVariable String pointId, @PathVariable String recipeId){
        return ResponseEntity.ok(recipeService.recipeToPointManager(pointId, recipeId));
    }
    @Hidden
    @GetMapping(SEND_RECIPE_CATEGORYID_USER_TO_RECIPE+"/{recipeId}")
    public ResponseEntity<SendRecipeAndCategoryId> sendRecipeAndCategoryIdUserToRecipe(@PathVariable String recipeId){
        return ResponseEntity.ok(recipeService.sendRecipeAndCategoryIdUserToRecipe(recipeId));
    }
    @PostMapping(RECIPE_CATEGORY_FILTER+"/{categoryIds}")
    public ResponseEntity<List<Recipe>> recipeCategoryFilter(@PathVariable List<String> categoryIds) {
        return ResponseEntity.ok(recipeService.recipeCategoryFilter(categoryIds));
    }
    @PostMapping(RECIPE_FOODNAME_FILTER+"/{foodName}")
    public ResponseEntity<List<Recipe>> recipeFoodnameFilter(@PathVariable String foodName){
        return ResponseEntity.ok(recipeService.recipeFoodnameFilter(foodName));
    }
    @PostMapping(RECIPE_INGREDIENT_FILTER+"/{ingredientName}")
    public ResponseEntity<List<Recipe>> recipeIngredientFilter(@PathVariable List<String> ingredientName){
        return ResponseEntity.ok(recipeService.recipeIngredientFilter(ingredientName));
    }
    @PostMapping(RECIPE_CALORIE_FILTER)
    public ResponseEntity<List<Recipe>> recipeCalorieFilter(){
        return ResponseEntity.ok(recipeService.recipeCalorieFilter());
    }
    @GetMapping(FIND_ALL)
    public ResponseEntity<List<Recipe>> findAll(){
        return ResponseEntity.ok(recipeService.findAll());
    }

    }
