package com.lydia.vurrukkulluk.model;

import jakarta.persistence.*;
import jakarta.persistence.Lob;


@Entity
public class UserImage {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  private String name;
  private String type;
  @Lob
  @Column(name = "imagedata", length=1000000)

  private byte[] imageData;
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
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }
  public byte[] getImageData() {
    return imageData;
  }
  public void setImageData(byte[] imageData) {
    this.imageData = imageData;
  }

}
