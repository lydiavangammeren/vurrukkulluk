package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.*;

import java.util.List;

public class RecipeDto {

    private int id;
    private String title;

    private String description;
    private int persons;
    private int price;
    private int calories;
    private KitchenType kitchenType;

    private KitchenRegion kitchenRegion;

    private List<KitchenCategory> categories;
    private List<CommentDto> comments;

    private List<IngredientDto> ingredients;

    private List<PreparationDto> preparation;
    private float avgRating;

    private int imageId;

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public KitchenType getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(KitchenType kitchenType) {
        this.kitchenType = kitchenType;
    }

    public KitchenRegion getKitchenRegion() {
        return kitchenRegion;
    }

    public void setKitchenRegion(KitchenRegion kitchenRegion) {
        this.kitchenRegion = kitchenRegion;
    }

    public List<KitchenCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<KitchenCategory> categories) {
        this.categories = categories;
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

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<PreparationDto> getPreparation() {
        return preparation;
    }

    public void setPreparation(List<PreparationDto> preparation) {
        this.preparation = preparation;
    }

    public int getPersons() {
        return persons;
    }

    public void setPersons(int persons) {
        this.persons = persons;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
