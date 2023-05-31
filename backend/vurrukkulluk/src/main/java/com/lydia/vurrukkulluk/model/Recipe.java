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
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kitchen_type_id", nullable=false)
  private KitchenType kitchenType;
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "kitchen_region_id", nullable=true)
  private KitchenRegion kitchenRegion;

  // Postman: "user": {"id": 4},
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", nullable=false)
  private User user;

  private String title;
  @Column(unique=true)
  private String slug;
  private String description;
  private LocalDateTime timeAdded = LocalDateTime.now();
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "image_id", nullable=true)
  private Image image;
  private int persons;


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

  public KitchenRegion getKitchenRegion() {
    return kitchenRegion;
  }

  public void setKitchenRegion(KitchenRegion kitchenRegion) {
    this.kitchenRegion = kitchenRegion;
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

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }

  public int getPersons() {
    return persons;
  }

  public void setPersons(int persons) {
    this.persons = persons;
  }

}
