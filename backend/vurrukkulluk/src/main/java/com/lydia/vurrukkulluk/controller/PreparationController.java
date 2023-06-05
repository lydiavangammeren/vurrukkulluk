package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.PreparationDto;
import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.service.PreparationService;
import net.bytebuddy.asm.Advice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/preparations")
public class PreparationController {

    @Autowired
    private PreparationService preparationService;
    @Autowired
    private ModelMapper modelMapper;
    public PreparationController(){

    }

    @GetMapping()
    public List<PreparationDto> getAll(){
        return preparationService.getAllPreparations().stream().map(this::convertPreparationToDto).collect(Collectors.toList());
    }

    @PostMapping()
    public String add(@RequestBody PreparationDto preparationDto){
        preparationService.savePreparation(reversePreparationFromDto(preparationDto));
        return "New preparation added";
    }

    @PutMapping()
    public String put(@RequestBody PreparationDto preparationDto){
        preparationService.savePreparation(reversePreparationFromDto(preparationDto));
        return "Preparation updated";
    }

    @DeleteMapping()
    public String delete(@RequestBody PreparationDto preparationDto){
        preparationService.delete(reversePreparationFromDto(preparationDto));
        return "Preparation updated";
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

}
