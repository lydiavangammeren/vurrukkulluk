package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test
    void loadAndRead() throws IOException {
        Image image = new Image();

        image.setId(1);
        image.setType("file");
        image.setName("name");
        String imagePath = "src\\main\\resources\\images\\VeganBurger.jpg";
        byte [] imagedata = UserImageUtil.compressImage(Files.readAllBytes(Paths.get(imagePath)));
        image.setImageData(imagedata);


        assertEquals(1,image.getId());
        assertEquals("file",image.getType());
        assertEquals("name",image.getName());
        assertEquals(imagedata,image.getImageData());

    }

    @Test
    void emptyWhenNotFilled(){
        Image image = new Image();

        assertEquals(0,image.getId());
        assertNull(image.getImageData());
        assertNull(image.getType());
        assertNull(image.getName());
    }
}