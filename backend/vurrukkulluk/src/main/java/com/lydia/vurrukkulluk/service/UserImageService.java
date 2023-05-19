package com.lydia.vurrukkulluk.service;

import org.springframework.web.multipart.MultipartFile;
import com.lydia.vurrukkulluk.model.UserImage;

import java.io.IOException;


public interface UserImageService {
  public UserImage uploadImage(MultipartFile file) throws IOException;
  public byte[] downloadImage(String fileName);
}
