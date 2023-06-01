package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.dto.RecipeShortDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    private ModelMapper modelMapper = new ModelMapper();
    RecipeController recipeController;
    @BeforeEach
    void setUp(){
        recipeController = new RecipeController();
    }

    @Test
    public void convertToRecipeShortDTO(){
        Recipe recipe = new Recipe();
        recipe.setId(1);
        recipe.setSlug("123456789");
        recipe.setDescription("lalalala");
        KitchenType type = new KitchenType();
        type.setName("vegan");
        type.setId(4);
        recipe.setKitchenType(type);

        RecipeShortDto recipeShortDto = covertRecipeToShortDto(recipe);

        assertEquals(recipe.getId(),recipeShortDto.getId());
        assertEquals(recipe.getDescription(),recipeShortDto.getDescription());
        assertEquals(recipe.getKitchenType().getName(),recipeShortDto.getKitchenType().getName());

        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe);
        recipes.add(recipe);

        List<RecipeShortDto> recipesShort = recipes.stream().map(this::covertRecipeToShortDto).collect(Collectors.toList());
        assertEquals(recipes.get(1).getDescription(),recipesShort.get(1).getDescription());
    }

    @Test
    public void priceAndCalorieCalculationTest(){
        Article article1 = new Article();
        article1.setCalories(200);
        article1.setPrice(250);
        article1.setUnit("g");
        article1.setAmount(200);
        article1.setId(1);
        Article article2 = new Article();
        article2.setCalories(150);
        article2.setPrice(250);
        article2.setUnit("100g");
        article2.setId(2);

        IngredientDto ingredient1 = new IngredientDto();
        ingredient1.setArticle(article1);
        ingredient1.setAmount(400);
        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setArticle(article2);
        ingredient2.setAmount(320);

        List<IngredientDto> ingredients= new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        int totalPrice = 250 * 400 + 250 * 320;
        int totalCal   = 200 * 400 + 150 * 320;

        assertEquals(totalPrice,recipeController.calculateCurrentPrice(ingredients));
        assertEquals(totalCal,recipeController.calculateCalories(ingredients));

    }

    public RecipeShortDto covertRecipeToShortDto(Recipe recipe){
        return modelMapper.map(recipe, RecipeShortDto.class);
    }
}