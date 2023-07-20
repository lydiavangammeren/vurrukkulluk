package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.FavoriteDto;
import com.lydia.vurrukkulluk.model.Favorite;
import com.lydia.vurrukkulluk.service.FavoriteService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/favorites")
@CrossOrigin
public class FavoriteController {
  @Autowired
  private FavoriteService favoriteService;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private SecurityUtil securityUtil;

  public FavoriteController() {
  }

  @PostMapping()
  public String add(@RequestBody FavoriteDto favoriteDto) {
    if (!securityUtil.isAuthorizedUserOrAdmin(favoriteDto.getUserId())){
      return "not authorized";
    }
    Favorite favorite = reverseFavoriteFromDto(favoriteDto);
    favoriteService.saveFavorite(favorite);
    return "new favorite is added";
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
    if (!securityUtil.isAuthorizedUserOrAdmin(favoriteDto.getUserId())){
      return "not authorized";
    }
    Favorite favorite = reverseFavoriteFromDto(favoriteDto);
    favoriteService.saveFavorite(favorite);
    return "Favorite is updated";
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(favoriteService.getFavoriteById(id).getUser().getId())){
      return "not authorized";
    }
    favoriteService.deleteFavoriteById(id);
    return "Favorite is deleted";
  }

  public FavoriteDto convertFavoriteToDto(Favorite favorite){
    return modelMapper.map(favorite, FavoriteDto.class);
  }

  public Favorite reverseFavoriteFromDto(FavoriteDto favoriteDto){
    return modelMapper.map(favoriteDto,Favorite.class);
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
