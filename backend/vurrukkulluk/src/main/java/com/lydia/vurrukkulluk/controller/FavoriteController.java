package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
@CrossOrigin
public class FavoriteController {
  @Autowired
  private FavoriteService favoriteService;

  public FavoriteController() {
  }

  @PostMapping()
  public String add(@RequestBody Favorite Favorite) {
    favoriteService.saveFavorite(Favorite);
    return "New favorite is added";
  }

  @GetMapping()
  public List<Favorite> getAll() {
    return favoriteService.getAllFavorites();
  }

  @GetMapping("/{id}")
  public Favorite getId(@PathVariable int id){
    return favoriteService.getFavoriteById(id);
  }

  @PutMapping()
  // Postman Body: {
  //        "id": 3,
  //        "recipe":{ "id": 3},
  //        "user": {"id": 4}
  //    }
  public String update(@RequestBody Favorite Favorite) {
    favoriteService.updateFavorite(Favorite);
    return "Favorite is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody Favorite Favorite) {
    favoriteService.deleteFavorite(Favorite);
    return "Favorite is deleted";
  }
}
