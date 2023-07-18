package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface ImageService {
  public Image saveImage(Image image) throws IOException;
  public byte[] getImageById(int id);
  public Image updateImage(Image image) throws IOException;
  public void deleteImage(int id);

}
