package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.service.PreparationService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/preparations")
public class PreparationController {

    @Autowired
    private PreparationService preparationService;
    public PreparationController(){

    }

    @GetMapping()
    public List<Preparation> getAll(){
        return preparationService.getAllPreparations();
    }

    @PostMapping()
    public String add(@RequestBody Preparation preparation){
        preparationService.savePreparation(preparation);
        return "New preparation added";
    }

    @GetMapping("/recipe/{id}")
    public List<Preparation> getAllOfRecipe(@PathVariable int id){
        return preparationService.getAllPreparationsRecipe(id);
    }


}
