package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.dto.FavoriteDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.service.FavoriteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favorites")
@CrossOrigin
public class FavoriteController {
  @Autowired
  private FavoriteService favoriteService;

  @Autowired
  private ModelMapper modelMapper;

  public FavoriteController() {
  }

  @PostMapping()
  public String add(@RequestBody FavoriteDto favoriteDto) {
    Favorite favorite = reverseFavoriteFromDto(favoriteDto);
    favoriteService.saveFavorite(favorite);
    return "New favorite is added";
  }

  @GetMapping()
  public List<FavoriteDto> getAll() {
    return favoriteService.getAllFavorites().stream().map(this::convertFavoriteToDto).collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public Favorite getId(@PathVariable int id){
    return favoriteService.getFavoriteById(id);
  }

  @PutMapping()
  public String update(@RequestBody FavoriteDto favoriteDto) {
    Favorite favorite = reverseFavoriteFromDto(favoriteDto);
    favoriteService.saveFavorite(favorite);
    return "Favorite is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody FavoriteDto favoriteDto) {
    Favorite favorite = reverseFavoriteFromDto(favoriteDto);
    favoriteService.deleteFavorite(favorite);
    return "Favorite is deleted";
  }

  public FavoriteDto convertFavoriteToDto(Favorite favorite){
    return modelMapper.map(favorite, FavoriteDto.class);
  }

  public Favorite reverseFavoriteFromDto(FavoriteDto favoriteDto){
    return modelMapper.map(favoriteDto,Favorite.class);
  }


}
