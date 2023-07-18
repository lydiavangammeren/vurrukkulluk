package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.repository.RatingRepository;
import lombok.AllArgsConstructor;
import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public Rating getRatingById(int id) {
        return ratingRepository.getReferenceById(id);
    }

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
    public void deleteById(int id) {
        ratingRepository.deleteById(id);
    }

}
