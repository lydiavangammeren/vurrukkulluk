package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UnitDto;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.service.UnitService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/units")
@CrossOrigin
public class UnitController {

    @Autowired
    private UnitService unitService;
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping()
    public List<UnitDto> getall(){
        return unitService.getAll().stream().map(this::convertUnitToDto).collect(Collectors.toList());
    }

    public UnitDto convertUnitToDto(Unit unit){
        return modelMapper.map(unit,UnitDto.class);
    }


}
