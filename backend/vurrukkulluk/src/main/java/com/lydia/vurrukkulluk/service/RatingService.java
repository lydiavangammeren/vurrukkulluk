package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;

import java.util.List;

public interface RatingService {

    Rating getRatingById(int id);
    List<Rating> getAllRatings();

    List<Rating> getAllRatingsRecipe(int recipeId);

    Rating saveRating(Rating rating);

    float getAvgRatingOfRecipe(int recipeId);
    void deleteById(int id);
}
