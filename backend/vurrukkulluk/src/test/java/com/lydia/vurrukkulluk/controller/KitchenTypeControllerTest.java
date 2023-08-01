package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.service.ArticleServiceImpl;
import com.lydia.vurrukkulluk.service.KitchenTypeService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KitchenTypeControllerTest {

    @Mock
    KitchenTypeService kitchenTypeService;
    @Mock
    SecurityUtil securityUtil;
    @Mock
    List<KitchenType> kitchenTypes;
    @Mock
    KitchenType kitchenType;

    KitchenTypeController kitchenTypeController;

    @BeforeEach
    void makeController(){
        kitchenTypeController = new KitchenTypeController(kitchenTypeService,securityUtil);
    }

    @Test
    void getAll() {
        when(kitchenTypeService.getAll()).thenReturn(kitchenTypes);
        assertEquals(kitchenTypes,kitchenTypeController.getAll());
    }

    @Test
    void add() {
        when(kitchenTypeService.saveKitchenType(kitchenType)).thenReturn(kitchenType);
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("New kitchen type is added"),kitchenTypeController.add(kitchenType));
    }

    @Test
    void updateWhenAutorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("This kitchen type is updated"),kitchenTypeController.update(kitchenType));
        verify(kitchenTypeService).updateKitchenType(kitchenType);
    }
    @Test
    void updateWhenNotAutorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),kitchenTypeController.update(kitchenType));
        verifyNoInteractions(kitchenTypeService);
    }

    @Test
    void deleteWhenAutorized() {
        when(securityUtil.isAdmin()).thenReturn(true);
        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Kitchen type is deleted"),kitchenTypeController.delete(1));
        verify(kitchenTypeService).deleteById(1);

    }
    @Test
    void deleteWhenNotAutorized() {
        when(securityUtil.isAdmin()).thenReturn(false);
        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),kitchenTypeController.delete(1));
        verifyNoInteractions(kitchenTypeService);
    }
}