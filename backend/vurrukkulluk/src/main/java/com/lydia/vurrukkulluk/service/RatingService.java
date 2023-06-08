package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> getAllRatings();

    List<Rating> getAllRatingsRecipe(int recipeId);

    void saveRating(Rating rating);

    float getAvgRatingOfRecipe(int recipeId);
    void deleteById(int id);
}
