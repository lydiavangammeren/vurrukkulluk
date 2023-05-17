package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    public List<Rating> findAllByRecipeId(int recipeId);
}
