package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UnitServiceImplTest {

    @Mock
    Unit unit;
    @Mock
    UnitRepository repository;
    UnitServiceImpl unitService;

    @BeforeEach
    void makeService(){
        assertNotNull(repository);
        unitService = new UnitServiceImpl(repository);
    }

    @Test
    void save() {

        when(repository.save(unit)).thenReturn(unit);
        assertEquals(unit,unitService.save(unit));
    }
}