package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.KitchenType;

import java.util.List;

public class RecipeDto {

    private int id;

    private String title;

    private String description;

    private List<CommentDto> comments;

    private List<IngredientDto> ingredients;

    private int avgRating;

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDto> ingredients) {
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
