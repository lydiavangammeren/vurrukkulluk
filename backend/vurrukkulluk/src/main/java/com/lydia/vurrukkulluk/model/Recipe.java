package com.lydia.vurrukkulluk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Recipe {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;


  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kitchen_type_id", nullable=false)

  private KitchenType kitchenType;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kitchen_region_id", nullable=true)
  private KitchenRegion kitchenRegion;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable=false)
  private User user;

  private String title;

  @Column(unique=true)
  private String slug;

  @Column(length = 1500)
  private String description;

  private LocalDateTime timeAdded = LocalDateTime.now();

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image_id", nullable=true)
  private Image image;

  private int persons;
}
