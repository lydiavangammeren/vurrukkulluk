package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenRegion;
import com.lydia.vurrukkulluk.repository.KitchenRegionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KitchenRegionServiceImplTest {

    @Mock
    KitchenRegion kitchenRegion;
    @Mock
    KitchenRegionRepository repository;

    KitchenRegionServiceImpl kitchenRegionService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        kitchenRegionService = new KitchenRegionServiceImpl(repository);
    }
    @Test
    void saveKitchenRegion() {
        when(repository.save(kitchenRegion)).thenReturn(kitchenRegion);
        assertEquals(kitchenRegion,kitchenRegionService.saveKitchenRegion(kitchenRegion));
        verify(repository).save(kitchenRegion);
    }

    @Test
    void getById() {
        when(repository.findById(1)).thenReturn(kitchenRegion);
        assertEquals(kitchenRegion,kitchenRegionService.getById(1));
        verify(repository).findById(1);
    }

    @Test
    void getAllRegions() {
        List<KitchenRegion> regions = new ArrayList<>();
        regions.add(kitchenRegion);
        regions.add(kitchenRegion);
        regions.add(kitchenRegion);
        when(repository.findAll()).thenReturn(regions);
        assertEquals(regions,kitchenRegionService.getAllRegions());
    }
}