package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.service.KitchenRegionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/kitchenregions")
@CrossOrigin
public class KitchenRegionController {
  @Autowired
  private KitchenRegionService kitchenRegionService;

  public KitchenRegionController(){

  }

  @GetMapping()
  public List<KitchenRegion> getAll(){
    return kitchenRegionService.getAllRegions();
  }
}
