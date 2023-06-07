package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;

public class IngredientDto {
    Article article;

    int amount;

    int recipeId;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
