package com.lydia.vurrukkulluk.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PreparationDto {
    private int id;
    @NotNull
    private int step;
    @NotBlank
    private String instructions;
    @NotNull
    private int recipeId;
}
