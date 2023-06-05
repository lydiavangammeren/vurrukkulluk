package com.lydia.vurrukkulluk.model;

import jakarta.persistence.*;

@Entity
public class User {

  public User() {
  }

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private String name;
  private String password;
  private String email;
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "image_id",nullable = true)
  private Image image;
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
