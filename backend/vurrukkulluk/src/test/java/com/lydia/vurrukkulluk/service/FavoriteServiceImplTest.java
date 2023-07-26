package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.repository.FavoriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FavoriteServiceImplTest {

    @Mock
    Favorite favorite;

    @Mock
    FavoriteRepository repository;

    FavoriteServiceImpl favoriteService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        favoriteService = new FavoriteServiceImpl(repository);
    }

    @Test
    void saveFavorite() {
        when(repository.save(favorite)).thenReturn(favorite);
        assertEquals(favorite,favoriteService.saveFavorite(favorite));
        verify(repository).save(favorite);
    }

    @Test
    void getAllFavorites() {
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favorite);
        favorites.add(favorite);
        favorites.add(favorite);

        when(repository.findAll()).thenReturn(favorites);
        assertEquals(favorites,favoriteService.getAllFavorites());
        verify(repository).findAll();

    }

    @Test
    void getFavoriteById() {

        when(repository.findById(1)).thenReturn(favorite);
        assertEquals(favorite,favoriteService.getFavoriteById(1));
        verify(repository).findById(1);
    }

    @Test
    void updateFavorite() {
        when(repository.save(favorite)).thenReturn(favorite);
        assertEquals(favorite,favoriteService.updateFavorite(favorite));
        verify(repository).save(favorite);
    }

    @Test
    void deleteFavoriteById() {
        favoriteService.deleteFavoriteById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void getFavoritesUserId() {
        List<Favorite> favorites = new ArrayList<>();
        favorites.add(favorite);
        favorites.add(favorite);
        favorites.add(favorite);

        when(repository.findByUserId(1)).thenReturn(favorites);
        assertEquals(favorites,favoriteService.getFavoritesUserId(1));
        verify(repository).findByUserId(1);

    }
}