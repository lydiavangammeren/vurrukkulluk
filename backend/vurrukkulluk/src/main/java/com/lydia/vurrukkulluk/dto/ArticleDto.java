package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private int Id;
    @NotBlank(message = "must have a name")
    private String name;
    @NotBlank(message = "must have a description")
    private String description;
    @NotNull
    @Min(0)
    private int price;
    @NotNull
    @Min(0)
    private int calories;
    @NotNull
    @Min(1)
    private int amount;
    @NotNull
    private boolean isAvailable;
    private int imageId;
    @Min(1)
    private int userId;

    private int defaultUnitId;
    private HashMap<Integer,Double> units;

}