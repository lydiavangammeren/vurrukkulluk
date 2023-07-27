package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleUnitDto;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.CalendarItem;
import com.lydia.vurrukkulluk.service.ArticleUnitService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("api/articleunits")
@CrossOrigin
public class ArticleUnitController {

    @Autowired
    private ArticleUnitService articleUnitService;
    @Setter
    @Autowired
    private ModelMapper modelMapper;
    @GetMapping()
    public List<ArticleUnitDto> getAllItems(){
        return articleUnitService.getAll().stream().map(this::convertArticlUnitToDto).collect(Collectors.toList());
    }

    public ArticleUnitDto convertArticlUnitToDto(ArticleUnit articleUnit){
        return modelMapper.map(articleUnit,ArticleUnitDto.class);
    }

}
