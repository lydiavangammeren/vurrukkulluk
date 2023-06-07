package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    @Autowired
    private IngredientRepository ingredientRepository;
    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient getIngredientById(int id) {
        return ingredientRepository.findById(id);
    }

    @Override
    public List<Ingredient> getIngredientsRecipeId(int id) {
        return ingredientRepository.findByRecipeId(id);
    }

    @Override
    public void delete(Ingredient ingredient) {
        ingredientRepository.delete(ingredient);
    }
}
