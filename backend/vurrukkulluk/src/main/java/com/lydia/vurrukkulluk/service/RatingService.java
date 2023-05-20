package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;

import java.util.List;

public interface RatingService {

    public List<Rating> getAllRatings();

    public List<Rating> getAllRatingsRecipe(int recipeId);

    public void saveRating(Rating rating);

    public int getAvgRatingOfRecipe(int recipeId);

}
