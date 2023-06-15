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
    private int image;
    private String slug;
    private String description;
    private int kitchenTypeId;
    private int kitchenRegionId;
    private int userId;
    private List<Integer> categoryIds;
    private List<IngredientCreateDto> ingredients;
}
