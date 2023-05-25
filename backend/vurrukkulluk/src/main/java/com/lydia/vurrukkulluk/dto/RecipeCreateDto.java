package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.model.KitchenType;

import java.util.List;

public class RecipeCreateDto {
    private String title;
    private String image;
    private  String slug;
    private String description;

    private int kitchenTypeId;

    private int kitchenRegionId;

    private int userId;

    private List<Integer> categoryIds;
    private List<IngredientCreateDto> ingredients;
    public int getUserId() {
        return userId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getKitchenTypeId() {
        return kitchenTypeId;
    }

    public void setKitchenTypeId(int kitchenTypeId) {
        this.kitchenTypeId = kitchenTypeId;
    }

    public int getKitchenRegionId() {
        return kitchenRegionId;
    }

    public void setKitchenRegionId(int kitchenRegionId) {
        this.kitchenRegionId = kitchenRegionId;
    }

    public List<Integer> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(List<Integer> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public List<IngredientCreateDto> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientCreateDto> ingredients) {
        this.ingredients = ingredients;
    }
}
