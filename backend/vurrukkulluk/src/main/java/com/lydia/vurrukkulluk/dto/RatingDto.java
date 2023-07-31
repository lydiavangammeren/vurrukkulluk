package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private int id;
    @NotNull
    private int rating;
    @NotNull
    private int userId;
    @NotNull
    private int recipeId;
}
