package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.RatingDto;
import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.service.RatingService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/ratings")
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
    public ResponseEntity<String> add(@Valid @RequestBody RatingDto ratingDto){
        if (!securityUtil.isAuthorizedUserOrAdmin(ratingDto.getUserId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }

        Rating rating = reverseRatingFromDto(ratingDto);
        Rating rating1 = ratingService.getRatingOfUserOfRecipe(ratingDto.getUserId(),ratingDto.getRecipeId());
        if (  rating1 != null){
            rating.setId(rating1.getId());
        }

        ratingService.saveRating(rating);

        return ResponseEntity.status(HttpStatus.OK).body(String.valueOf(ratingService.getAvgRatingOfRecipe(ratingDto.getRecipeId())));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){

        if (!securityUtil.isAuthorizedUserOrAdmin(ratingService.getRatingById(id).getUser().getId())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }

        ratingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Updated rating");

    }

    @GetMapping("/recipe/{recipeId}")
    public List<RatingDto> getAllOfRecipe(@PathVariable int recipeId){
        return ratingService.getAllRatingsRecipe(recipeId).stream().map(this ::convertRatingToDto).collect(Collectors.toList());
    }

    public RatingDto convertRatingToDto(Rating rating){ return modelMapper.map(rating, RatingDto.class);}

    public Rating reverseRatingFromDto(RatingDto ratingDto){ return modelMapper.map(ratingDto, Rating.class);}

    public void setModelMapper(ModelMapper modelMapper) { this.modelMapper = modelMapper; }
}
