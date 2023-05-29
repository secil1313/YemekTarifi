package com.secil.constant;

public class ApiUrls {

    public static final String VERSION = "api/v1";
    public static final String RECIPE = VERSION + "/recipe";
    public static final String CATEGORY = VERSION + "/category";

    //AuthController
    public static final String REGISTER = "/register";
    public static final String ADD = "/add";
    public static final String DELETE = "/delete";
    public static final String LOGIN = "/login";
    public static final String UPDATE = "/update";
    public static final String DELETE_BY_ID = "/delete-by-id";
    public static final String FIND_BY_ID = "/find-by-id";
    public static final String FIND_ALL = "/find-all";
    public static final String ACTIVATE_STATUS = "/activate-status";
    public static final String PASSWORD_CHANGE = "/password-change";
    public static final String FORGOT_PASSWORD = "/forgot-password";
    public static final String RECIPE_CATEGORY_FILTER = "/recipeCategoryFilter";
    public static final String RECIPE_FOODNAME_FILTER = "/recipeFoodnameFilter";
    public static final String RECIPE_INGREDIENT_FILTER = "/recipeIngredientFilter";
    public static final String RECIPE_CALORIE_FILTER = "/recipeCalorieFilter";
    public static final String RECIPE_TO_COMMENT_MANAGER = "/recipeToCommentManager";
    public static final String EXIST_BY_RECIPEID = "/existByRecipeId";
    public static final String EXIST_BY_POINTID_COMMENTID = "/existsByPointIdCommentId";
    public static final String DELETE_COMMENT_FOR_DELETED_COMMENT_IN_RECIPE = "/recipescomment";
    public static final String DELETE_POINT_FOR_DELETED_POINT_IN_RECIPE = "/recipespoint";
    public static final String RECIPE_TO_POINT_MANAGER = "/recipeToPointManager";
    public static final String SEND_RECIPE_CATEGORYID_USER_TO_RECIPE = "/sendRecipeAndCategoryIdUserToRecipe";
}
