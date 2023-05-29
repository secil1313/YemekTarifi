package com.secil.manager;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@FeignClient(url = "http://localhost:8060/api/v1/comment",name = "recipe-comment")
public interface ICommentManager {

    @PostMapping("/commentToRecipeManager/{recipeId}")
    ResponseEntity<Boolean> commentToRecipeManager(@PathVariable String recipeId);
}
