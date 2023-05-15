package com.lydia.vurrukkulluk.reposetory;

import com.lydia.vurrukkulluk.model.Ingredient;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Integer> {
    List<Ingredient> findByName(String name);
}
