package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ImageServiceImpl implements ImageService {
  @Autowired
  private ImageRepository imageRepository;
  @Override
  public Image saveImage(Image image) throws IOException {
    return imageRepository.save(image);
  }

  @Override
  public byte[] getImageById(int id) {
    Optional<Image> imageData = imageRepository.findById(id);
    return UserImageUtil.decompressImage(imageData.get().getImageData());
  }

  @Override
  public Image updateImage(int id, MultipartFile file) throws IOException {

    return imageRepository.save(new Image(id,file));
  }

  @Override
  public void deleteImage(int id) {
    imageRepository.deleteById(id);
  }

}
