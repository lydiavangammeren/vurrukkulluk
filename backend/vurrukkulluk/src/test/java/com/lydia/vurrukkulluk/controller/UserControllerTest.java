package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UserCreateDto;
import com.lydia.vurrukkulluk.dto.UserDto;
import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.model.Image;
import com.lydia.vurrukkulluk.model.Rating;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.FavoriteService;
import com.lydia.vurrukkulluk.service.ImageService;
import com.lydia.vurrukkulluk.service.UserService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
    @Mock
    UserCreateDto userCreateDto;
    @Mock
    UserDto userDto;
    @Mock
    User user;
    @Mock
    Image image;
    @Mock
    Favorite favorite;
    @Mock
    UserService userService;
    @Mock
    FavoriteService favoriteService;
    @Mock
    ImageService imageService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;
    UserController controller;

    @BeforeEach
    void makeController(){
        controller = new UserController(userService,favoriteService,
                imageService,modelMapper,securityUtil);
    }

    @Test
    void noArgsConstructor(){
        UserController test = new UserController();
        assertNotNull(test);
    }

    @Test
    void add() {
        when(modelMapper.map(userCreateDto, User.class)).thenReturn(user);
        assertEquals("New user is added",controller.add(userCreateDto));
        verify(userService).saveUser(user);
    }

    @Test
    void getAll() {
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        List<UserDto> userDtos = new ArrayList<>();
        userDtos.add(userDto);
        userDtos.add(userDto);
        userDtos.add(userDto);

        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userService.getAllUsers()).thenReturn(users);
        assertEquals(userDtos,controller.getAll());


    }

    @Test
    void getEmail() {
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userService.getUserByEmail("email@a.com")).thenReturn(user);
        assertEquals(userDto,controller.getEmail("email@a.com"));
    }

    @Test
    void getId() {
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        when(userService.getUserById(1)).thenReturn(user);
        assertEquals(userDto,controller.getId(1));

    }

    @Test
    void updateAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(modelMapper.map(userCreateDto, User.class)).thenReturn(user);
        assertEquals("User is updated",controller.update(userCreateDto,1));
        verify(userService).saveUser(user);
    }
    @Test
    void updateNotAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        assertEquals("not authorized",controller.update(userCreateDto,1));
        verifyNoInteractions(userService);
    }
    @Test
    void deleteAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);

        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favorite);
        favorites.add(favorite);
        favorites.add(favorite);
        favorites.add(favorite);
        when(favoriteService.getFavoritesUserId(1)).thenReturn(favorites);
        when(favorite.getId()).thenReturn(5);
        when(userService.getUserById(1)).thenReturn(user);
        when(user.getImage()).thenReturn(image);
        when(image.getId()).thenReturn(25);

        assertEquals("User is deleted",controller.delete(1));
        verify(imageService).deleteImage(25);
        verify(userService).deleteById(1);
        verify(favoriteService, times(4)).deleteFavoriteById(5);
    }
    @Test
    void deleteNotAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        assertEquals("not authorized",controller.delete(1));
        verifyNoInteractions(userService);
    }

    @Test
    void convertUserToDto() {
    }

    @Test
    void reverseUserToCreateDto() {
    }
}