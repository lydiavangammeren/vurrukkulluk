package com.lydia.vurrukkulluk.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private int Id;
    private String name;
    private String description;
    private int price;
    private int calories;
    private String unit;
    private int amount;
    private int imageId;
}