package com.lydia.vurrukkulluk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreateDto {
    private String title;
    private int imageId;
    private int persons;
    private String slug;
    private String description;
    private int kitchenTypeId;
    private int kitchenRegionId;
    private int userId;
    private List<Integer> categoryIds;
    private List<IngredientInRecipeDto> ingredients;
    private List<PreparationInRecipeDto> preparations;
    private int id = 0; // Deze stond uit, maar moest aan staan om te kunnen posten - 25-06-2023
}
