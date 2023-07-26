package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.KitchenType;
import com.lydia.vurrukkulluk.repository.KitchenTypeRepository;
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
class KitchenTypeServiceImplTest {
    @Mock
    KitchenType kitchenType;
    @Mock
    KitchenTypeRepository repository;

    KitchenTypeServiceImpl kitchenTypeService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);
        kitchenTypeService = new KitchenTypeServiceImpl(repository);
    }

    @Test
    void saveKitchenType() {
        when(repository.save(kitchenType)).thenReturn(kitchenType);
        assertEquals(kitchenType,kitchenTypeService.saveKitchenType(kitchenType));
        verify(repository).save(kitchenType);
    }

    @Test
    void updateKitchenType() {
        when(repository.save(kitchenType)).thenReturn(kitchenType);
        assertEquals(kitchenType,kitchenTypeService.updateKitchenType(kitchenType));
        verify(repository).save(kitchenType);
    }

    @Test
    void deleteById() {
        kitchenTypeService.deleteById(1);
        verify(repository).deleteById(1);
    }

    @Test
    void getAll() {
        List<KitchenType> kitchenTypes = new ArrayList<>();
        kitchenTypes.add(kitchenType);
        kitchenTypes.add(kitchenType);
        kitchenTypes.add(kitchenType);

        when(repository.findAll()).thenReturn(kitchenTypes);
        assertEquals(kitchenTypes,kitchenTypeService.getAll());
        verify(repository).findAll();

    }

    @Test
    void getById() {
        when(repository.findById(1)).thenReturn(kitchenType);
        assertEquals(kitchenType,kitchenTypeService.getById(1));
        verify(repository).findById(1);
    }
}