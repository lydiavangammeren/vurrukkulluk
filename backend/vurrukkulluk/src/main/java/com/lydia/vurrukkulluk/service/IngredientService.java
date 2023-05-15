package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Ingredient;

import java.util.List;

public interface IngredientService {
    public Ingredient saveIngredient(Ingredient ingredient);
    public List<Ingredient> getAllIngredients();
    public List<Ingredient> getIngredientsByName(String name);

}
