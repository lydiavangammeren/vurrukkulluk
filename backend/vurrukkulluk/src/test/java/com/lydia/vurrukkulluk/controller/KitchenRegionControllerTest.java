package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.service.KitchenRegionService;
import jakarta.persistence.ManyToOne;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenRegionControllerTest {

    @Mock
    List<KitchenRegion> kitchenRegions;
    @Mock
    KitchenRegionService kitchenRegionService;
    KitchenRegionController controller;
    @BeforeEach
    void makeController(){
        controller = new KitchenRegionController(kitchenRegionService);
    }

    @Test
    void noArgsConstructor(){
        KitchenRegionController test = new KitchenRegionController();
        assertNotNull(test);
    }

    @Test
    void getAll() {
        when(kitchenRegionService.getAllRegions()).thenReturn(kitchenRegions);
        assertEquals(kitchenRegions,controller.getAll());
    }
}