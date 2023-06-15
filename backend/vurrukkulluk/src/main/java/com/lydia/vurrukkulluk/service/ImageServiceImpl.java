package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
public class ImageServiceImpl implements ImageService {
  @Autowired
  private ImageRepository imageRepository;
  @Override
  public Image saveImage(MultipartFile file) throws IOException {
    Image image = new Image();
    image.setName(file.getOriginalFilename());
    image.setType(file.getContentType());
    image.setImageData(UserImageUtil.compressImage(file.getBytes()));
    return imageRepository.save(image);
  }

  @Override
  public byte[] getImageById(int id) {
    Optional<Image> imageData = imageRepository.findById(id);
    return UserImageUtil.decompressImage(imageData.get().getImageData());
  }

  @Override
  public Image updateImage(MultipartFile file, int id) throws IOException {
    Image image = new Image();
    image.setId(id);
    image.setName(file.getOriginalFilename());
    image.setType(file.getContentType());
    image.setImageData(UserImageUtil.compressImage(file.getBytes()));
    return imageRepository.save(image);

  }

  @Override
  public void deleteImage(int id) {
    imageRepository.deleteById(id);
  }

//  public List<Image> getImagesRecipeId(int id){
//    return imageRepository.findAllByRecipeId(id);
//  }
}
