package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;

public class IngredientDto {
    ArticleDto article;

    int amount;

    int recipeId;

    public ArticleDto getArticle() {
        return article;
    }

    public void setArticle(ArticleDto article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }
}
