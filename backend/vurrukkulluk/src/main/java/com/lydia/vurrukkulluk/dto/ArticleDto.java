package com.lydia.vurrukkulluk.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private int Id;
    @NotBlank
    private String name;
    private String description;
    private int price;
    private int calories;
    private int amount;
    private int imageId;
}