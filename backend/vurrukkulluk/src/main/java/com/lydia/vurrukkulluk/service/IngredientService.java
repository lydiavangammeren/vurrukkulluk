package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Ingredient;

import java.util.List;

public interface IngredientService {

    public Ingredient saveIngredient(Ingredient ingredient);
    public List<Ingredient> getAllIngredients();
    public Ingredient getIngredientById(int id);

    List<Ingredient> getIngredientsRecipeId(int id);
    void deleteById(int id);
}
