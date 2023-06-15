package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeDto {

    private int id;
    private String title;
    private String slug;
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
}
