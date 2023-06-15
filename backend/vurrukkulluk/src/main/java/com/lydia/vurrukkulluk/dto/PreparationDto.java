package com.lydia.vurrukkulluk.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparationDto {
    private int id;
    private int step;
    private String instructions;
    private int recipeId;
}
