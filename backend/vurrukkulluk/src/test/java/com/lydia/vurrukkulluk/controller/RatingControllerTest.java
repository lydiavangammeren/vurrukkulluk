package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RatingDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.RatingService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RatingControllerTest {

    @Mock
    Rating rating;
    @Mock
    User user;
    @Mock
    RatingDto ratingDto;
    @Mock
    RatingService ratingService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;
    RatingController controller;

    @BeforeEach
    void makeController(){
        controller = new RatingController(ratingService,modelMapper,securityUtil);
    }

    @Test
    void getAll() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating);
        ratings.add(rating);
        List<RatingDto> ratingsDto = new ArrayList<>();
        ratingsDto.add(ratingDto);
        ratingsDto.add(ratingDto);
        ratingsDto.add(ratingDto);

        when(ratingService.getAllRatings()).thenReturn(ratings);
        when(modelMapper.map(rating, RatingDto.class)).thenReturn(ratingDto);

        assertEquals(ratingsDto,controller.getAll());

    }

    @Test
    void addAuthorized() {
        when(ratingDto.getUserId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(modelMapper.map(ratingDto, Rating.class)).thenReturn(rating);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("0.0"),controller.add(ratingDto));
        verify(ratingService).saveRating(rating);
    }
    @Test
    void addNotAuthorized() {
        when(ratingDto.getUserId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.add(ratingDto));
        verifyNoInteractions(ratingService);
    }

    @Test
    void deleteAuthorized() {
        when(ratingService.getRatingById(1)).thenReturn(rating);
        when(rating.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Updated rating"),controller.delete(1));
        verify(ratingService).deleteById(1);

    }
    @Test
    void deleteNotAuthorized() {
        when(ratingService.getRatingById(1)).thenReturn(rating);
        when(rating.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.delete(1));

    }

    @Test
    void getAllOfRecipe() {
        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating);
        ratings.add(rating);
        List<RatingDto> ratingsDto = new ArrayList<>();
        ratingsDto.add(ratingDto);
        ratingsDto.add(ratingDto);
        ratingsDto.add(ratingDto);

        when(ratingService.getAllRatingsRecipe(1)).thenReturn(ratings);
        when(modelMapper.map(rating, RatingDto.class)).thenReturn(ratingDto);

        assertEquals(ratingsDto,controller.getAllOfRecipe(1));
    }

    @Test
    void convertRatingToDto() {
        controller.setModelMapper(new ModelMapper());
        Rating rating1 = new Rating(1,3,new User(),new Recipe());
        rating1.getUser().setId(4);
        rating1.getRecipe().setId(2);
        RatingDto ratingDto1 = new RatingDto(1,3,4,2);

        assertEquals(ratingDto1,controller.convertRatingToDto(rating1));
    }

    @Test
    void reverseRatingFromDto() {
        controller.setModelMapper(new ModelMapper());
        Rating rating1 = new Rating(1,3,new User(),new Recipe());
        rating1.getUser().setId(4);
        rating1.getRecipe().setId(2);
        rating1.getRecipe().setTimeAdded(null);
        RatingDto ratingDto1 = new RatingDto(1,3,4,2);

        Rating result = controller.reverseRatingFromDto(ratingDto1);
        result.getRecipe().setTimeAdded(null);
        assertEquals(rating1,result);

    }
}