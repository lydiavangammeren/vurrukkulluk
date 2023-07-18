package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ImageServiceImplTest {

    @Mock
    Image image;
    @Mock
    ImageRepository repository;

    ImageServiceImpl imageService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        imageService = new ImageServiceImpl(repository);
    }

    @Test
    void saveImage() throws IOException {
        when(repository.save(image)).thenReturn(image);
        assertEquals(image,imageService.saveImage(image));
        verify(repository).save(image);

    }

    @Test
    void getImageById() {
        when(repository.findById(1)).thenReturn(Optional.ofNullable(image));
        byte[] dumyBytes = UserImageUtil.compressImage(new byte[16]);

        when(image.getImageData()).thenReturn(dumyBytes);

        assertEquals(Arrays.hashCode(UserImageUtil.decompressImage(dumyBytes)), Arrays.hashCode(imageService.getImageById(1)));

    }

    @Test
    void updateImage() {
        when(repository.save(image)).thenReturn(image);
        assertEquals(image,imageService.updateImage(image));
        verify(repository).save(image);

    }

    @Test
    void deleteImage() {
        imageService.deleteImage(1);
        verify(repository).deleteById(1);
    }
}