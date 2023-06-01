package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ImageService {
  public Image uploadImage(MultipartFile file) throws IOException;
  public byte[] downloadImage(int id);
}
