package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RatingDto;
import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.service.RatingService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ratings")
@CrossOrigin
public class RatingController {

    @Autowired
    private RatingService ratingService;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SecurityUtil securityUtil;
    public  RatingController(){

    }
    @GetMapping()
    public List<RatingDto> getAll(){
        return ratingService.getAllRatings().stream().map(this ::convertRatingToDto).collect(Collectors.toList());
    }

    @PostMapping()
    public String add(@RequestBody RatingDto ratingDto){
        ratingService.saveRating(reverseRatingFromDto(ratingDto));
        return "Saved rating";
    }

    @PutMapping()
    public String put(@RequestBody RatingDto ratingDto){
        ratingService.saveRating(reverseRatingFromDto(ratingDto));
        return "Updated rating";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id){
        Rating rating = ratingService.getRatingById(id);

        if (securityUtil.isIdOfAuthorizedUser(rating.getUser().getId())){
            ratingService.deleteById(id);
            return "Updated rating";
        }

        return "Not yours";
    }


    @GetMapping("/recipe/{recipeId}")
    public List<RatingDto> getAllOfRecipe(@PathVariable int recipeId){
        return ratingService.getAllRatingsRecipe(recipeId).stream().map(this ::convertRatingToDto).collect(Collectors.toList());
    }



    public RatingDto convertRatingToDto(Rating rating){ return modelMapper.map(rating, RatingDto.class);}

    public Rating reverseRatingFromDto(RatingDto ratingDto){ return modelMapper.map(ratingDto, Rating.class);}

}
