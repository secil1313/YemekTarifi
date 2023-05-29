package com.secil.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Document
public class Recipe extends Base implements Serializable {
    @Id
    private String recipeId;
    private String recipeName;
    private String type;
    private String preparationTime;
    private String cookingTime;
    private String recipeSteps;
    private String photo;
    private List<String> commentId=new ArrayList<>();
    private List<String> pointId=new ArrayList<>();
    private List<Ingredient> ingredient=new ArrayList<>();
    private NutritionalValue nutritionalValue;
    private List<String> categoryIds=new ArrayList<>();

}
