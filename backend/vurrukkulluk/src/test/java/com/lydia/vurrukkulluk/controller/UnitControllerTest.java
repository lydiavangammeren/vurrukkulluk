package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.UnitDto;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.service.UnitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UnitControllerTest {
    @Mock
    UnitService unitService;
    @Mock ModelMapper modelMapper;
    @Mock
    Unit unit;
    @Mock
    UnitDto unitDto;
    UnitController controller;

    @BeforeEach
    void makeController(){
        controller = new UnitController(unitService,modelMapper);
    }
    @Test
    void getall() {
        List<Unit> units = new ArrayList<>();
        units.add(unit);
        units.add(unit);
        units.add(unit);
        List<UnitDto> unitDtos = new ArrayList<>();
        unitDtos.add(unitDto);
        unitDtos.add(unitDto);
        unitDtos.add(unitDto);

        when(unitService.getAll()).thenReturn(units);
        when(modelMapper.map(unit,UnitDto.class)).thenReturn(unitDto);

        assertEquals(unitDtos,controller.getall());
    }

    @Test
    void convertUnitToDto() {
        controller.setModelMapper(new ModelMapper());
        Unit unit1 = new Unit(1,"name");
        UnitDto unitDto1 = new UnitDto(1,"name");

        assertEquals(unitDto1,controller.convertUnitToDto(unit1));

    }
}