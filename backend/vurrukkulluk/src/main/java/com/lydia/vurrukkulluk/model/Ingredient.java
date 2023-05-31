package com.lydia.vurrukkulluk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private long amount;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id",nullable = false)
    private Article article;

    @ManyToOne(cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id",nullable = false)
    private Recipe recipe;

    public Ingredient(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Article getArticle() {
        return article;
    }
    public void setArticle(Article article) {
        this.article = article;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
