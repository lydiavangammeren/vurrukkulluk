package com.lydia.vurrukkulluk.model;

import com.lydia.vurrukkulluk.util.UserImageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ImageTest {

    @Test
    void loadAndRead() throws IOException {
        Image image = new Image();

        image.setId(1);
        image.setType("file");
        image.setName("name");
        byte [] imagedata = UserImageUtil.compressImage(new byte[128]);
        image.setImageData(imagedata);

        assertEquals(1,image.getId());
        assertEquals("file",image.getType());
        assertEquals("name",image.getName());
        assertEquals(imagedata,image.getImageData());

    }

    @Test
    void makeFromMultipartfile() throws IOException {

        Image image = new Image(new MockMultipartFile("imagename","profileimg.png","file",new byte[128]));

        assertEquals(0,image.getId());
        assertEquals("file",image.getType());
        assertEquals("profileimg.png",image.getName());
        assertEquals(Arrays.hashCode(UserImageUtil.compressImage(new byte[128])), Arrays.hashCode(image.getImageData()));
    }

    @Test
    void makeFromMultipartfileWithId() throws IOException {

        Image image = new Image(1,new MockMultipartFile("imagename","profileimg.png","file",new byte[128]));

        assertEquals(1,image.getId());
        assertEquals("file",image.getType());
        assertEquals("profileimg.png",image.getName());
        assertEquals(Arrays.hashCode(UserImageUtil.compressImage(new byte[128])), Arrays.hashCode(image.getImageData()));
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