package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.PreparationDto;
import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.service.PreparationService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/preparations")
@CrossOrigin
public class PreparationController {

    @Autowired
    private PreparationService preparationService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SecurityUtil securityUtil;
    public PreparationController(){

    }

    @GetMapping()
    public List<PreparationDto> getAll(){
        return preparationService.getAllPreparations().stream().map(this::convertPreparationToDto).collect(Collectors.toList());
    }

    @PostMapping()
    public ResponseEntity<String> add(@RequestBody PreparationDto preparationDto){
        preparationService.savePreparation(reversePreparationFromDto(preparationDto));
        return ResponseEntity.status(HttpStatus.OK).body("New preparation added");
    }

    @PutMapping()
    public ResponseEntity<String> put(@RequestBody PreparationDto preparationDto){
        if (!securityUtil.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        preparationService.savePreparation(reversePreparationFromDto(preparationDto));
        return ResponseEntity.status(HttpStatus.OK).body("Preparation updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id){
        if (!securityUtil.isAdmin()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
        }
        preparationService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Preparation deleted");
    }

    @GetMapping("/recipe/{id}")
    public List<PreparationDto> getAllOfRecipe(@PathVariable int id){
        return preparationService.getAllPreparationsRecipe(id).stream().map(this::convertPreparationToDto).collect(Collectors.toList());
    }

    public PreparationDto convertPreparationToDto(Preparation preparation){
        return modelMapper.map(preparation,PreparationDto.class);
    }

    public Preparation reversePreparationFromDto(PreparationDto preparationDto){
        return modelMapper.map(preparationDto,Preparation.class);
    }

    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
