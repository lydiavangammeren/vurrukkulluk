package com.lydia.vurrukkulluk.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Recipe {

  public Recipe() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  // Postman: "kitchenType":{ "id": 1},
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "kitchen_id", nullable=false)
  @JsonIgnore
  private KitchenType kitchenType;

  // Postman: "user": {"id": 4},
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable=false)
  @JsonIgnore
  private User user;

  // should also be a FK
  private int categoryId;
  private String title;
  @Column(unique=true)
  private String slug;
  private String description;
  private LocalDateTime timeAdded = LocalDateTime.now();
  private String image;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public KitchenType getKitchenType() {
    return kitchenType;
  }

  public void setKitchenType(KitchenType kitchenType) {
    this.kitchenType = kitchenType;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getTimeAdded() {
    return timeAdded;
  }

  public void setTimeAdded(LocalDateTime timeAdded) {
    this.timeAdded = timeAdded;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }
}
