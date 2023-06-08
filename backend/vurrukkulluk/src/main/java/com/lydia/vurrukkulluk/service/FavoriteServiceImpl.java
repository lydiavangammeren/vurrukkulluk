package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.repository.FavoriteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
  @Autowired
  private FavoriteRepository favoriteRepository;
  @Override
  public void saveFavorite(Favorite favorite) {
    favoriteRepository.save(favorite);
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
  public void updateFavorite(Favorite favorite) {
    favoriteRepository.save(favorite);
  }

  @Override
  public void deleteFavoriteById(int id) {
    favoriteRepository.deleteById(id);
  }

}
