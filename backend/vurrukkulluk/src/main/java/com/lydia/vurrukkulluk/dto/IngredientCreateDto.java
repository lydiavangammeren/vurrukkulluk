package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.model.Article;

public class IngredientCreateDto {
    int articleId;
    int amount;

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
