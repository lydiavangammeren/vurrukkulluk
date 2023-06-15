package com.lydia.vurrukkulluk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id",nullable = false)
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id",nullable = false)
    private Recipe recipe;
}
