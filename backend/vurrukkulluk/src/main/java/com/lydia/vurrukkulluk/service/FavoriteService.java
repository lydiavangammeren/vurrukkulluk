package com.lydia.vurrukkulluk.service;
import com.lydia.vurrukkulluk.model.Favorite;
import java.util.List;

public interface FavoriteService {
  void saveFavorite(Favorite favorite);

  List<Favorite> getAllFavorites();

  Favorite getFavoriteById(int id);

  void updateFavorite(Favorite favorite);

  void deleteFavorite(Favorite favorite);
}
