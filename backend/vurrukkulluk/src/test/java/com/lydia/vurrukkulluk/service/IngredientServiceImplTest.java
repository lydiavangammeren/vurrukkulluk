package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.repository.IngredientRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientServiceImplTest {

    @Mock
    Ingredient ingredient;

    @Mock
    IngredientRepository repository;

    IngredientServiceImpl ingredientService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        ingredientService = new IngredientServiceImpl(repository);
    }

    @Test
    void saveIngredient() {
        when(repository.save(ingredient)).thenReturn(ingredient);
        assertEquals(ingredient,ingredientService.saveIngredient(ingredient));
        verify(repository).save(ingredient);
    }

    @Test
    void getAllIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        when(repository.findAll()).thenReturn(ingredients);
        assertEquals(ingredients,ingredientService.getAllIngredients());
        verify(repository).findAll();

    }

    @Test
    void getIngredientById() {
        when(repository.findById(1)).thenReturn(ingredient);
        assertEquals(ingredient,ingredientService.getIngredientById(1));
        verify(repository).findById(1);
    }

    @Test
    void getIngredientsRecipeId() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        when(repository.findByRecipeId(1)).thenReturn(ingredients);
        assertEquals(ingredients,ingredientService.getIngredientsRecipeId(1));
        verify(repository).findByRecipeId(1);
    }

    @Test
    void deleteById() {
        ingredientService.deleteById(1);
        verify(repository).deleteById(1);
    }
}