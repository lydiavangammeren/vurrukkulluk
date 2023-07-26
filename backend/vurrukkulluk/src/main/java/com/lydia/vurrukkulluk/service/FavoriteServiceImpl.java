package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.repository.FavoriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteServiceImpl implements FavoriteService {
  @Autowired
  private FavoriteRepository favoriteRepository;
  @Override
  public Favorite saveFavorite(Favorite favorite) {
    return favoriteRepository.save(favorite);
  }

  @Override
  public List<Favorite> getAllFavorites() {
    return favoriteRepository.findAll();
  }

  @Override
  public Favorite getFavoriteById(int id) {
    return favoriteRepository.findById(id);
  }

  @Override
  public Favorite updateFavorite(Favorite favorite) {
    return favoriteRepository.save(favorite);
  }

  @Override
  public void deleteFavoriteById(int id) {
    favoriteRepository.deleteById(id);
  }

  @Override
  public List<Favorite> getFavoritesUserId(int id) {
    return favoriteRepository.findByUserId(id);
  }
}
