package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientDto {
    @Autowired
    private ArticleService articleService;
    ArticleUnitDto articleUnit;
    int amount;
    int recipeId;

    public ArticleDto getArticle(){
        return articleUnit.getArticle();
    }

}
