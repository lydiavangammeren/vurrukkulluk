package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.dto.ArticleUnitDto;
import com.lydia.vurrukkulluk.dto.UnitDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;
import com.lydia.vurrukkulluk.service.ArticleUnitService;
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
class ArticleUnitControllerTest {

    @Mock
    ArticleUnitService articleUnitService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    ArticleUnit articleUnit;
    @Mock
    ArticleUnitDto articleUnitDto;
    ArticleUnitController controller;

    @BeforeEach
    void makeController(){
        controller = new ArticleUnitController(articleUnitService,modelMapper);
    }

    @Test
    void getAllItems() {
        List<ArticleUnit> articleUnits = new ArrayList<>();
        articleUnits.add(articleUnit);
        articleUnits.add(articleUnit);
        articleUnits.add(articleUnit);

        List<ArticleUnitDto> articleUnitDtos = new ArrayList<>();
        articleUnitDtos.add(articleUnitDto);
        articleUnitDtos.add(articleUnitDto);
        articleUnitDtos.add(articleUnitDto);


        when(articleUnitService.getAll()).thenReturn(articleUnits);
        when(modelMapper.map(articleUnit,ArticleUnitDto.class)).thenReturn(articleUnitDto);

        assertEquals(articleUnitDtos,controller.getAllItems());

    }

    @Test
    void convertArticlUnitToDto() {
        controller.setModelMapper(new ModelMapper());

        ArticleUnit articleUnit1 = new ArticleUnit(1, new Article(),new Unit(1,"g"),0.001d,new Unit(2,"kg"));
        articleUnit1.getArticle().setId(1);
        articleUnit1.getArticle().setName("name");
        ArticleUnitDto articleUnitDto1 = new ArticleUnitDto(1,new ArticleDto(),new UnitDto(1,"g"),0.001d,new UnitDto(2,"kg"));
        articleUnitDto1.getArticle().setId(1);
        articleUnitDto1.getArticle().setName("name");

        assertEquals(articleUnitDto1,controller.convertArticlUnitToDto(articleUnit1));


    }
}