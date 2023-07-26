package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Preparation;
import com.lydia.vurrukkulluk.repository.PreparationRepository;
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
class PreparationServiceImplTest {

    @Mock
    Preparation preparation;
    @Mock
    PreparationRepository repository;

    PreparationServiceImpl preparationService;
    @BeforeEach
    void setup(){
        assertNotNull(repository);
        preparationService = new PreparationServiceImpl(repository);
    }
    @Test
    void getAllPreparations() {
        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);
        preparations.add(preparation);

        when(repository.findAll()).thenReturn(preparations);
        assertEquals(preparations,preparationService.getAllPreparations());
        verify(repository).findAll();

    }

    @Test
    void getAllPreparationsRecipe() {
        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);
        preparations.add(preparation);

        when(repository.findByRecipeId(1)).thenReturn(preparations);
        assertEquals(preparations,preparationService.getAllPreparationsRecipe(1));
        verify(repository).findByRecipeId(1);

    }

    @Test
    void savePreparation() {
        when(repository.save(preparation)).thenReturn(preparation);
        assertEquals(preparation,preparationService.savePreparation(preparation));

    }

    @Test
    void deleteById() {
        preparationService.deleteById(1);
        verify(repository).deleteById(1);
    }
}