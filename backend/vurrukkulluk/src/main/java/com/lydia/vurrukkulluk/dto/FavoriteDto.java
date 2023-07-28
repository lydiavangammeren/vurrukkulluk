package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteDto {
    private int id;
    @NotNull
    private int UserId;
    @NotNull
    private int RecipeId;
}
