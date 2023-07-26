package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.repository.RatingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.AopInvocationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {

    @Mock
    Rating rating;
    @Mock
    RatingRepository repository;

    RatingServiceImpl ratingService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        ratingService = new RatingServiceImpl(repository);
    }

    @Test
    void getRatingById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(rating));
        assertEquals(rating,ratingService.getRatingById(1));

    }

    @Test
    void getAllRatings() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating);
        ratings.add(rating);

        when(repository.findAll()).thenReturn(ratings);
        assertEquals(ratings,ratingService.getAllRatings());
    }

    @Test
    void getAllRatingsRecipe() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating);
        ratings.add(rating);

        when(repository.findAllByRecipeId(1)).thenReturn(ratings);
        assertEquals(ratings,ratingService.getAllRatingsRecipe(1));
    }

    @Test
    void saveRating() {
        when(repository.save(rating)).thenReturn(rating);
        assertEquals(rating,ratingService.saveRating(rating));
    }

    @Test
    void getAvgRatingOfRecipe() {
        when(repository.findAvgByRecipeId(1)).thenReturn(4.7F);
        when(repository.findAvgByRecipeId(2)).thenThrow(new AopInvocationException("no ratings"));

        assertEquals(4.7F,ratingService.getAvgRatingOfRecipe(1));
        assertEquals(0.0F,ratingService.getAvgRatingOfRecipe(2));

        verify(repository).findAvgByRecipeId(1);
        verify(repository).findAvgByRecipeId(2);

    }

    @Test
    void deleteById() {
        ratingService.deleteById(1);
        verify(repository).deleteById(1);
    }
}