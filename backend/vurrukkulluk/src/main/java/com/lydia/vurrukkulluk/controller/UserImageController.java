package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.service.UserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// POST: In Postman, make a HTTP POST request to upload image to the URL http://localhost:8080/upload.
// In order to send the image, inside the HTTP body select form-data, enter productImage as Key
// change type of Key from text to file
// and select image which you want to upload as Value.

// GET: In Postman, make a HTTP GET request to
// http://localhost:8080/download/{name of image}.png. You will see the image in the response body downloaded from the database.


@RestController
public class UserImageController {

  @Autowired
  private UserImageService imageService;

  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping("/upload")
  public void uploadImage(@RequestParam("userImage") MultipartFile file) throws IOException {
    imageService.uploadImage(file);
  }

  @GetMapping("/download/{fileName}")
  public ResponseEntity<byte[]> downloadImage(@PathVariable String fileName) {
    byte[] image = imageService.downloadImage(fileName);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }
}

