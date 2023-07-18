package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    @Mock
    Image image;
    @Mock
    Recipe recipe;
    @Mock
    RecipeRepository repository;
    RecipeServiceImpl recipeService;
    @BeforeEach
    void setup(){
        assertNotNull(repository);
        recipeService = new RecipeServiceImpl(repository);
    }

    @Test
    void saveRecipe() {
        when(repository.save(recipe)).thenReturn(recipe);
        assertEquals(recipe,recipeService.saveRecipe(recipe));
    }

    @Test
    void deleteById() {
        recipeService.deleteById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void deleteRecipe() {
        recipeService.deleteRecipe(recipe);
        verify(repository).delete(recipe);
    }

    @Test
    void getRecipeById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(recipe));
        assertEquals(recipe,recipeService.getRecipeById(1));
    }

    @Test
    void setImageInRecipe() {
        recipeService.setImageInRecipe(recipe,image);
        verify(recipe).setImage(image);
        verify(repository).save(recipe);
    }

    @Test
    void getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe);
        recipes.add(recipe);

        when(repository.findAll()).thenReturn(recipes);
        assertEquals(recipes,recipeService.getAllRecipes());
    }

    @Test
    void getRecipeByTitle() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe);
        when(repository.findByTitle("title")).thenReturn(recipes);
        assertEquals(recipes,recipeService.getRecipeByTitle("title"));
    }

    @Test
    void getRecipeBySlug() {
        when(repository.findBySlug("slug")).thenReturn(recipe);
        assertEquals(recipe,recipeService.getRecipeBySlug("slug"));
    }

    @Test
    void updateRecipe() {
        when(repository.save(recipe)).thenReturn(recipe);
        assertEquals(recipe,recipeService.updateRecipe(recipe));
    }
}