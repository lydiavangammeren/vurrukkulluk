package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RecipeShortDto;
import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.model.Recipe;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    private ModelMapper modelMapper = new ModelMapper();

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

        RecipeShortDto recipeShortDto =covertRecipeToShortDto(recipe);

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

    public RecipeShortDto covertRecipeToShortDto(Recipe recipe){
        return modelMapper.map(recipe, RecipeShortDto.class);
    }
}