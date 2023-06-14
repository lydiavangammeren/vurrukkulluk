package com.lydia.vurrukkulluk.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class KitchenCategoriesLink {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kitchen_category_id",nullable = false)
  private KitchenCategory kitchenCategory;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "recipe_id",nullable = false)
  private Recipe recipe;
}
