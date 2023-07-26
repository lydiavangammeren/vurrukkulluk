package com.lydia.vurrukkulluk.service;
import com.lydia.vurrukkulluk.model.Favorite;
import java.util.List;

public interface FavoriteService {
  Favorite saveFavorite(Favorite favorite);

  List<Favorite> getAllFavorites();

  Favorite getFavoriteById(int id);

  Favorite updateFavorite(Favorite favorite);
  void deleteFavoriteById(int id);

  public List<Favorite> getFavoritesUserId(int id);
}
