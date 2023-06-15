package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    ArticleDto article;
    int amount;
    int recipeId;
}
