package com.secil.service;

import com.secil.dto.request.RecipeRequestDto;
import com.secil.dto.request.SendRecipeAndCategoryId;
import com.secil.dto.request.UpdateRecipeRequestDto;
import com.secil.dto.response.GetUserForFavoriteCategoryResponseDto;
import com.secil.exception.ErrorType;
import com.secil.exception.RecipeManagerException;
import com.secil.manager.IUserProfileManager;
import com.secil.manager.ICommentManager;
import com.secil.mapper.IRecipeMapper;
import com.secil.rabbitmq.model.RecipeMailModel;
import com.secil.rabbitmq.producer.RecipeMailProducer;
import com.secil.repository.RecipeRepository;
import com.secil.repository.entity.ERole;
import com.secil.repository.entity.Recipe;
import com.secil.utility.JwtTokenProvider;
import com.secil.utility.ServiceManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService extends ServiceManager<Recipe, String> {
    private final RecipeRepository recipeRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final ICommentManager recipeToCommentManager;
    private final CategoryService categoryService;
    private final IUserProfileManager userProfileManager;
    private final RecipeMailProducer registerMailProducer;
    private final IRecipeMapper recipeMapper;


    public RecipeService(RecipeRepository recipeRepository, JwtTokenProvider jwtTokenProvider, ICommentManager recipeToCommentManager, CategoryService categoryService, IUserProfileManager userProfileManager, RecipeMailProducer registerMailProducer, IRecipeMapper recipeMapper) {
        super(recipeRepository);
        this.recipeRepository = recipeRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.recipeToCommentManager = recipeToCommentManager;
        this.categoryService = categoryService;
        this.userProfileManager = userProfileManager;
        this.registerMailProducer = registerMailProducer;
        this.recipeMapper = recipeMapper;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Ingredient-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true)
            },
            put = {
                    @CachePut(value = "recipe-foodName-Filter", key = "#dto.getRecipeName().toLowerCase()")
            }
    )

    public Recipe addRecipe(RecipeRequestDto dto, String token) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        Recipe recipe = IRecipeMapper.INSTANCE.recipeDtoToRecipe(dto);
        if (role.get().equals(ERole.ADMIN.toString())) {
            dto.getCategoryIds().forEach(x->{
                if(!categoryService.existsByCategoryId(x)){
                    throw new RecipeManagerException(ErrorType.CATEGORY_NOT_FOUND);
                }
            });
            save(recipe);
        } else {
            throw new RecipeManagerException(ErrorType.NOT_ADMIN);
        }
        Set<GetUserForFavoriteCategoryResponseDto> getUserForFavoriteCategoryResponseDtos=userProfileManager.getUserForFavoriteCategory(recipe.getCategoryIds()).getBody();
        getUserForFavoriteCategoryResponseDtos.forEach(x->{
            registerMailProducer.sendFavoriteFoodwithSameCategoriesFood(RecipeMailModel.builder()
                            .recipeName(recipe.getRecipeName())
                            .username(x.getUsername())
                            .email(x.getEmail())
                    .build());
                });

        return recipe;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public boolean deleteRecipe(String recipeId, String token) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(token);
        if (role.get().equals(ERole.ADMIN.toString())) {
            Optional<Recipe> recipe = findById(recipeId);
            delete(recipe.get());
            recipeToCommentManager.commentToRecipeManager(recipeId);
        } else {
            throw new RecipeManagerException(ErrorType.NOT_ADMIN);
        }
        return true;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public boolean updateRecipe(UpdateRecipeRequestDto dto) {
        Optional<String> role = jwtTokenProvider.getRoleFromToken(dto.getToken());
        Optional<Recipe> recipe = findById(dto.getRecipeId());
        if (role.get().equals(ERole.ADMIN.toString())) {
            Recipe recipe1 = IRecipeMapper.INSTANCE.updateRecipe(dto, recipe.get());
            update(recipe1);
        } else {
            throw new RecipeManagerException(ErrorType.NOT_ADMIN);
        }
        return true;
    }
    @Cacheable(value = "find-all")
    public List<Recipe> findAll(){

        return recipeRepository.findAll();
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public Boolean recipeToCommentManager(String commentId, String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        recipe.get().getCommentId().add(commentId);
        update(recipe.get());
        return true;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public Boolean recipeToPointManager(String pointId, String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isPresent()) {
            recipe.get().getPointId().add(pointId);
            update(recipe.get());
        } else {
            throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
        return true;
    }

    public Boolean existByRecipeId(String recipeId) {

        return recipeRepository.existsByRecipeId(recipeId);
    }

    public Boolean existsByPointIdAndRecipeId(String recipeId, String pointId) {
        Optional<Recipe> recipe = findById(recipeId);
        if(recipe.isEmpty())
            return false;
        return recipe.get().getPointId().contains(pointId);
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public Boolean deleteCommentForDeletedCommentInRecipeService(String recipeId, String commentId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
        recipe.get().getCommentId().remove(commentId);
        update(recipe.get());
        return true;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )
    public Boolean deletePointForDeletedPointInRecipeService(String recipeId, String pointId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
        recipe.get().getPointId().remove(pointId);
        update(recipe.get());
        return true;
    }
    @Caching(
            evict = {
                    @CacheEvict(value = "find-all", allEntries = true),
                    @CacheEvict(value = "recipe-Calorie-Filter", allEntries = true),
                    @CacheEvict(value = "recipe-foodName-Filter", allEntries = true)
            }
    )

    public SendRecipeAndCategoryId sendRecipeAndCategoryIdUserToRecipe(String recipeId) {
        Optional<Recipe> recipe = recipeRepository.findById(recipeId);
        if (recipe.isEmpty()) {
            throw new RecipeManagerException(ErrorType.RECIPE_NOT_FOUND);
        }
        SendRecipeAndCategoryId id = IRecipeMapper.INSTANCE.recipeToSendRecipeAndCategoryId(recipe.get());
        id.setCategoryId(recipe.get().getCategoryIds());
        return id;
    }
    public List<Recipe> recipeCategoryFilter(List<String> categoryIds) {
            List<Recipe> recipes = recipeRepository.findAll();
            if(categoryIds.size()>1){
                return recipes.stream().filter(recipe -> recipe.getCategoryIds().containsAll(categoryIds))
                        .collect(Collectors.toList());
            }else{
                String categoryId=categoryIds.get(0);
                return recipes.stream().filter(recipe->recipe.getCategoryIds().contains(categoryId))
                        .collect(Collectors.toList());
            }
        }
    @Cacheable(value = "recipe-foodName-Filter",key = "#foodName.toLowerCase()")

    public List<Recipe> recipeFoodnameFilter(String foodName) {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().filter(recipe -> recipe.getRecipeName().contains(foodName))
                .collect(Collectors.toList());
    }

    public List<Recipe> recipeIngredientFilter(List<String> ingredientNames) {
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().filter(recipe -> ingredientNames.stream().allMatch(ingredientName -> recipe.getIngredient().stream()
                                .anyMatch(ingredient -> ingredient.getProductName().equalsIgnoreCase(ingredientName))))
                .collect(Collectors.toList());
    }
    @Cacheable(value = "recipe-Calorie-Filter")
    public List<Recipe> recipeCalorieFilter() {
        List<Recipe> recipes = recipeRepository.findAll();
        recipes.sort(Comparator.comparingDouble(recipe -> recipe.getNutritionalValue().getCalorie()));
        return recipes;
    }
        }



