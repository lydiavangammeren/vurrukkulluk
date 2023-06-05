package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.service.KitchenTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/kitchentypes")
@CrossOrigin
public class KitchenTypeController {
  @Autowired
  private KitchenTypeService kitchenTypeService;

  @GetMapping()
  public List<KitchenType> getAll(){
    return kitchenTypeService.getAll();
  }


  @PostMapping()
  public String add(@RequestBody KitchenType kitchenType) {
    kitchenTypeService.saveKitchenType(kitchenType);
    return "New kitchen type is added";
  }

  @PutMapping()
  public String update(@RequestBody KitchenType kitchenType) {
    kitchenTypeService.updateKitchenType(kitchenType);
    return "This kitchen type is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody KitchenType kitchenType) {
    kitchenTypeService.deleteKitchenType(kitchenType);
    return "Kitchen type is deleted";
  }



}
