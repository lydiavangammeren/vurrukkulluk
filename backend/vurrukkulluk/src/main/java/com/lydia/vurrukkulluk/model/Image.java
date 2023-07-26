package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.UserImageUtil;
import jakarta.persistence.*;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Data
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

  public Image(MultipartFile file) throws IOException {
    this.setName(file.getOriginalFilename());
    this.setType(file.getContentType());
    this.setImageData(UserImageUtil.compressImage(file.getBytes()));
  }

  public Image(int id, MultipartFile file) throws IOException {
    this.setId(id);
    this.setName(file.getOriginalFilename());
    this.setType(file.getContentType());
    this.setImageData(UserImageUtil.compressImage(file.getBytes()));
  }
}
