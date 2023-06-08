package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// POST: In Postman, make a HTTP POST request to upload image to the URL http://localhost:8080/image.
// In order to send the image, inside the HTTP body select form-data, enter image as Key
// change type of Key from text to file
// and select image which you want to upload as Value.

// GET: In Postman, make a HTTP GET request to
// http://localhost:8080/image/{id} You will see the image in the response body downloaded from the database.


@RestController
@RequestMapping("/image")
public class ImageController {

  @Autowired
  private ImageService imageService;

  @GetMapping("/{id}")
  public ResponseEntity<byte[]> downloadImage(@PathVariable int id) {
    byte[] image = imageService.downloadImage(id);
    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
  }
  @ResponseStatus(value = HttpStatus.OK)
  @PostMapping()
  public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
    imageService.uploadImage(file);
  }

}

