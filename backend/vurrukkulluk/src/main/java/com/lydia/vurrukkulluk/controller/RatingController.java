package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;
    public  RatingController(){

    }
    @GetMapping()
    public List<Rating> getAll(){
        return ratingService.getAllRatings();
    }

    @PostMapping()
    public String add(@RequestBody Rating rating){
        ratingService.saveRating(rating);
        return "Saved rating";
    }
    @GetMapping("/recipe/{recipeId}")
    public List<Rating> getAllOfRecipe(@PathVariable int recipeId){
        return ratingService.getAllRatingsRecipe(recipeId);
    }


}
