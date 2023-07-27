package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.PreparationDto;
import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.PreparationService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PreparationControllerTest {

    @Mock
    PreparationService preparationService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;
    @Mock Preparation preparation;
    @Mock PreparationDto preparationDto;

    PreparationController controller;
    @BeforeEach
    void makeController(){
        controller = new PreparationController(preparationService,modelMapper,securityUtil);
    }

    @Test
    void noArgsConstructor(){
        PreparationController test = new PreparationController();
        assertNotNull(test);
    }
    @Test
    void getAll() {
        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);
        preparations.add(preparation);
        List<PreparationDto> preparationDtos = new ArrayList<>();
        preparationDtos.add(preparationDto);
        preparationDtos.add(preparationDto);
        preparationDtos.add(preparationDto);
        when(preparationService.getAllPreparations()).thenReturn(preparations);
        when(modelMapper.map(preparation,PreparationDto.class)).thenReturn(preparationDto);

        assertEquals(preparationDtos,controller.getAll());

    }

    @Test
    void add() {
        when(modelMapper.map(preparationDto,Preparation.class)).thenReturn(preparation);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("New preparation added"),controller.add(preparationDto));
        verify(preparationService).savePreparation(preparation);
    }

    @Test
    void putAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        when(modelMapper.map(preparationDto,Preparation.class)).thenReturn(preparation);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Preparation updated"),controller.put(preparationDto));
        verify(preparationService).savePreparation(preparation);
    }
    @Test
    void putNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.put(preparationDto));
        verifyNoInteractions(preparationService);
    }

    @Test
    void deleteAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(true);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Preparation deleted"),controller.delete(1));
        verify(preparationService).deleteById(1);
    }
    @Test
    void deleteNotAuthorized() {
        when(securityUtil.isAdmin()).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.delete(1));
        verifyNoInteractions(preparationService);

    }

    @Test
    void getAllOfRecipe() {
        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);
        preparations.add(preparation);
        List<PreparationDto> preparationDtos = new ArrayList<>();
        preparationDtos.add(preparationDto);
        preparationDtos.add(preparationDto);
        preparationDtos.add(preparationDto);
        when(preparationService.getAllPreparationsRecipe(1)).thenReturn(preparations);
        when(modelMapper.map(preparation,PreparationDto.class)).thenReturn(preparationDto);

        assertEquals(preparationDtos,controller.getAllOfRecipe(1));
    }

    @Test
    void convertPreparationToDto() {
        controller.setModelMapper(new ModelMapper());
        Preparation preparation1 = new Preparation(1,new Recipe(),3,"instructions");
        preparation1.getRecipe().setId(2);
        PreparationDto preparationDto1 = new PreparationDto(1,3,"instructions",2);
        assertEquals(preparationDto1,controller.convertPreparationToDto(preparation1));
    }

    @Test
    void reversePreparationFromDto() {
        controller.setModelMapper(new ModelMapper());
        Preparation preparation1 = new Preparation(1,new Recipe(),3,"instructions");
        preparation1.getRecipe().setId(2);
        preparation1.getRecipe().setTimeAdded(null);
        PreparationDto preparationDto1 = new PreparationDto(1,3,"instructions",2);
        Preparation result = controller.reversePreparationFromDto(preparationDto1);
        result.getRecipe().setTimeAdded(null);
        assertEquals(preparation1,result);
    }
}