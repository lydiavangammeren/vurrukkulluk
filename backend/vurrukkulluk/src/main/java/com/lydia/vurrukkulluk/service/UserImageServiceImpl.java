package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.UserImage;
import com.lydia.vurrukkulluk.repository.UserImageRepository;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserImageServiceImpl implements UserImageService {
  @Autowired
  private UserImageRepository imageRepository;
  @Override
  public UserImage uploadImage(MultipartFile file) throws IOException {
    UserImage image = new UserImage();
    image.setName(file.getOriginalFilename());
    image.setType(file.getContentType());
    image.setImageData(UserImageUtil.compressImage(file.getBytes()));
    return imageRepository.save(image);
  }

  @Override
  public byte[] downloadImage(String fileName) {
    Optional<UserImage> imageData = imageRepository.findByName(fileName);
    return UserImageUtil.decompressImage(imageData.get().getImageData());
  }
}
