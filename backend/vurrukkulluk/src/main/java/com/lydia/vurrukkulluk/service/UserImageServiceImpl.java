package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserImageServiceImpl implements UserImageService {
  @Autowired
  private ImageRepository imageRepository;
  @Override
  public Image uploadImage(MultipartFile file) throws IOException {
    Image image = new Image();
    image.setName(file.getOriginalFilename());
    image.setType(file.getContentType());
    image.setImageData(UserImageUtil.compressImage(file.getBytes()));
    return imageRepository.save(image);
  }

  @Override
  public byte[] downloadImage(String fileName) {
    Optional<Image> imageData = imageRepository.findByName(fileName);
    return UserImageUtil.decompressImage(imageData.get().getImageData());
  }
}
