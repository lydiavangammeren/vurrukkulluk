package com.lydia.vurrukkulluk.model;

import jakarta.persistence.*;

@Entity
public class KitchenCategoriesLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kitchen_category_id",nullable = false)
  private KitchenCategory kitchenCategory;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "recipe_id",nullable = false)
  private Recipe recipe;

  public KitchenCategoriesLink() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public KitchenCategory getKitchenCategory() {
    return kitchenCategory;
  }

  public void setKitchenCategory(KitchenCategory kitchenCategory) {
    this.kitchenCategory = kitchenCategory;
  }

  public Recipe getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipe recipe) {
    this.recipe = recipe;
  }
}
