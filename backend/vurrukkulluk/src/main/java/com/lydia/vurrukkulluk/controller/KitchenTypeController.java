package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.service.KitchenTypeService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/kitchentypes")
@CrossOrigin
public class KitchenTypeController {
  @Autowired
  private KitchenTypeService kitchenTypeService;
  @Autowired
  private SecurityUtil securityUtil;

  @GetMapping()
  public List<KitchenType> getAll(){
    return kitchenTypeService.getAll();
  }

  @PostMapping()
  public ResponseEntity<String> add(@RequestBody KitchenType kitchenType) {
    kitchenTypeService.saveKitchenType(kitchenType);
    return ResponseEntity.status(HttpStatus.OK).body("New kitchen type is added");
  }

  @PutMapping()
  public ResponseEntity<String> update(@RequestBody KitchenType kitchenType) {
    if (!securityUtil.isAdmin()){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    kitchenTypeService.updateKitchenType(kitchenType);
    return ResponseEntity.status(HttpStatus.OK).body("This kitchen type is updated");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    if (!securityUtil.isAdmin()){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    kitchenTypeService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Kitchen type is deleted");
  }



}
