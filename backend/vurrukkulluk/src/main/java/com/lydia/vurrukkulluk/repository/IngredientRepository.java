package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.KitchenType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
}
