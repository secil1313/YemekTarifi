package com.secil.manager;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(url = "http://localhost:8080/api/v1/recipe",name="comment-to-recipe")
public interface IRecipeManager {
@GetMapping("/existByRecipeId/{recipeId}")
public ResponseEntity<Boolean> existByRecipeId(@PathVariable String recipeId);
    @DeleteMapping("/recipescomment/{recipeId}/{commentId}")
    public ResponseEntity<Boolean> deleteCommentFromRecipe(@PathVariable String recipeId, @PathVariable String commentId);
    @PostMapping("/recipeToCommentManager/{commandId}/{recipeId}")
    public ResponseEntity<Boolean> recipeToCommentManager(@PathVariable String commandId, @PathVariable String recipeId);

    @DeleteMapping("/recipespoint/{recipeId}/{pointId}")
    public ResponseEntity<Boolean> deletePointFromRecipe(@PathVariable String recipeId, @PathVariable String pointId);
    @PostMapping("/recipeToPointManager/{pointId}/{recipeId}")
    public ResponseEntity<Boolean> recipeToPointManager(@PathVariable String pointId, @PathVariable String recipeId);
    @GetMapping("/existsByPointId/{pointId}")
    public ResponseEntity<Boolean> existsByPointId(@PathVariable String pointId);
    @GetMapping("/existsByPointIdCommentId/{recipeId}/{pointId}")
    public ResponseEntity<Boolean> existsByPointIdAndRecipeId(@PathVariable String recipeId,@PathVariable String pointId);

}
