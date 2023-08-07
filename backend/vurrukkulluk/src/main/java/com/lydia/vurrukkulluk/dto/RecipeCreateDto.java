package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreateDto {
    @NotBlank
    private String title;
    private int imageId;
    @NotNull
    private int persons;
    @NotNull
    private int prepTime;
    @NotBlank
    private String slug;
    @NotBlank
    private String description;
    @NotNull
    private int kitchenTypeId;
    @NotNull
    private int kitchenRegionId;
    @NotNull
    private int userId;
    @NotNull
    private List<CategoryLinkInRecipeDto> categoryIds;
    @NotNull
    private List<IngredientInRecipeDto> ingredients;
    @NotNull
    private List<PreparationInRecipeDto> preparations;
    private int id = 0;
}
