package com.lydia.vurrukkulluk.model;

import jakarta.persistence.*;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;

  private String name;
  private String type;
  @Lob
  @Column(name = "imagedata", length=1000000)
  private byte[] imageData;

}
