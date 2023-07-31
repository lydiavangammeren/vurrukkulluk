package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCreateDto {
    int id;
    @NotNull
    int articleunitId;
    @NotNull
    int recipeId;
    @NotNull
    double amount;
}
