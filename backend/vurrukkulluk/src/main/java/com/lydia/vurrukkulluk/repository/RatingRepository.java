package com.lydia.vurrukkulluk.repository;

import com.lydia.vurrukkulluk.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    List<Rating> findAllByRecipeId(int recipeId);

    Rating findAllByUserIdAndRecipeId(int userId,int recipId);

    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.recipe.id=?1 GROUP BY r.recipe.id ")
    float findAvgByRecipeId(int recipeId);

}
