package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.KitchenCategory;
import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.model.KitchenType;

import java.util.List;

public class RecipeShortDto {

    private int id;
    private String title;

    private String description;

    private KitchenType kitchenType;
    private int persons;
    private int price;
    private int calories;
    private float avgRating;

    private int imgId;

    private String slug;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public KitchenType getKitchenType() {
        return kitchenType;
    }

    public void setKitchenType(KitchenType kitchenType) {
        this.kitchenType = kitchenType;
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

    public float getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(float avgRating) {
        this.avgRating = avgRating;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
