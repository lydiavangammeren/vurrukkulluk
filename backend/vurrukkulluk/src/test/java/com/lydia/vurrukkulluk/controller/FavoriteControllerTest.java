package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.dto.FavoriteDto;
import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.FavoriteService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FavoriteControllerTest {

    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    FavoriteService favoriteService;
    @Mock
    FavoriteDto favoriteDto;
    @Mock
    Favorite favorite;
    @Mock
    User user;
    FavoriteController controller;
    @BeforeEach
    void makeController(){
        controller = new FavoriteController(favoriteService,modelMapper,securityUtil);
    }
    @Test
    void addAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(favoriteDto.getUserId()).thenReturn(1);
        when(modelMapper.map(favoriteDto, Favorite.class)).thenReturn(favorite);
        assertEquals("new favorite is added",controller.add(favoriteDto));
        verify(favoriteService).saveFavorite(favorite);

    }
    @Test
    void addNotAuthorized() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        when(favoriteDto.getUserId()).thenReturn(1);
        assertEquals("not authorized",controller.add(favoriteDto));
        verifyNoInteractions(favoriteService);
    }
    @Test
    void getAll() {
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favorite);
        favorites.add(favorite);
        favorites.add(favorite);
        List<FavoriteDto> result = new ArrayList<>();
        result.add(favoriteDto);
        result.add(favoriteDto);
        result.add(favoriteDto);
        when(modelMapper.map(favorite, FavoriteDto.class)).thenReturn(favoriteDto);
        when(favoriteService.getAllFavorites()).thenReturn(favorites);

        assertEquals(result,controller.getAll());

    }

    @Test
    void getId() {
        when(favoriteService.getFavoriteById(1)).thenReturn(favorite);
        assertEquals(favorite,controller.getId(1));
    }

    @Test
    void updateAuthenticated() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);
        when(favoriteDto.getUserId()).thenReturn(1);
        when(modelMapper.map(favoriteDto, Favorite.class)).thenReturn(favorite);
        assertEquals("Favorite is updated",controller.update(favoriteDto));
        verify(favoriteService).saveFavorite(favorite);

    }
    @Test
    void updateNotAuthenticated() {
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);
        when(favoriteDto.getUserId()).thenReturn(1);
        assertEquals("not authorized",controller.update(favoriteDto));
        verifyNoInteractions(favoriteService);

    }
    @Test
    void deleteAuthenticated() {
        when(favoriteService.getFavoriteById(1)).thenReturn(favorite);
        when(favorite.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);

        assertEquals("Favorite is deleted",controller.delete(1));
        verify(favoriteService).deleteFavoriteById(1);
    }
    @Test
    void deleteNotAuthenticated() {
        when(favoriteService.getFavoriteById(1)).thenReturn(favorite);
        when(favorite.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);

        assertEquals("not authorized",controller.delete(1));
        verify(favoriteService).getFavoriteById(1);
        verifyNoMoreInteractions(favoriteService);
    }

    @Test
    void convertFavoriteToDto() {
        controller.setModelMapper(new ModelMapper());
        Favorite favorite1 = new Favorite(1,new User(),new Recipe());
        favorite1.getUser().setId(2);
        favorite1.getRecipe().setId(3);
        FavoriteDto favoriteDto1 = new FavoriteDto(1,2,3);

        assertEquals(favoriteDto1,controller.convertFavoriteToDto(favorite1));

    }

    @Test
    void reverseFavoriteFromDto() {
        controller.setModelMapper(new ModelMapper());
        Favorite favorite1 = new Favorite(1,new User(),new Recipe());
        favorite1.getUser().setId(2);
        favorite1.getRecipe().setId(3);
        favorite1.getRecipe().setTimeAdded(null);
        FavoriteDto favoriteDto1 = new FavoriteDto(1,2,3);

        Favorite result = controller.reverseFavoriteFromDto(favoriteDto1);
        result.getRecipe().setTimeAdded(null);
        assertEquals(favorite1,result);
    }
}