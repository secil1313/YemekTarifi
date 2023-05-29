package com.secil.repository;

import com.secil.repository.entity.Recipe;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe,String> {
    Boolean existsByRecipeNameIgnoreCase(String recipeName);


    Boolean existsByRecipeId(String recipeId);
    Boolean existsByRecipeIdAndPointId(String recipeId,String pointId);


}
