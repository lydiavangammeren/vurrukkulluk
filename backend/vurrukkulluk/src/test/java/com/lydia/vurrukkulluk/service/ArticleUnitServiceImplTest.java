package com.lydia.vurrukkulluk.service;

import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.repository.ArticleUnitRepository;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleUnitServiceImplTest {

    @Mock
    Unit unit;
    @Mock
    ArticleUnit articleUnit;
    @Mock
    ArticleUnitRepository repository;

    ArticleUnitServiceImpl articleUnitService;

    @BeforeEach
    void setup(){
        assertNotNull(repository);

        articleUnitService = new ArticleUnitServiceImpl(repository);
    }

    @Test
    void save() {
        when(repository.save(articleUnit)).thenReturn(articleUnit);

        assertEquals(articleUnit,articleUnitService.save(articleUnit));
        verify(repository).save(articleUnit);
    }

    @Test
    void getDefaultUnitArticleFromArticleId() {
        when(repository.getDefaultUnitArticleFromArticleId(1)).thenReturn(articleUnit);

        assertEquals(articleUnit,articleUnitService.getDefaultUnitArticleFromArticleId(1));
        verify(repository).getDefaultUnitArticleFromArticleId(1);
    }

    @Test
    void getDefaultUnitFromArticleId() {
        when(repository.getDefaultUnitArticleFromArticleId(1)).thenReturn(articleUnit);
        when(articleUnit.getUnit()).thenReturn(unit);
        assertEquals(unit,articleUnitService.getDefaultUnitFromArticleId(1));
        verify(repository).getDefaultUnitArticleFromArticleId(1);
        verify(articleUnit).getUnit();

    }

}