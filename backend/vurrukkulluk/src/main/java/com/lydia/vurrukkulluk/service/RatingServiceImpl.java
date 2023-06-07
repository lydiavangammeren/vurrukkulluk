package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.repository.RatingRepository;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllRatingsRecipe(int recipeId) {
        return ratingRepository.findAllByRecipeId(recipeId);
    }

    @Override
    public void saveRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public float getAvgRatingOfRecipe(int recipeId) {
        float avg;
        try {
            avg = ratingRepository.findAvgByRecipeId(recipeId);
        } catch (AopInvocationException e){
            avg = (float) 0.0;
        }
        
        return avg;
    }

    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

}
