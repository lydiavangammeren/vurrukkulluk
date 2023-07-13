package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleDto;
import com.lydia.vurrukkulluk.dto.ArticleUnitDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Unit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecipeControllerTest {

    private ModelMapper modelMapper = new ModelMapper();
    RecipeController recipeController;
    @BeforeEach
    void setUp(){
        recipeController = new RecipeController();
    }


    @Test
    public void priceAndCalorieCalculationTest(){
        ArticleDto article1 = new ArticleDto();
        article1.setCalories(200);
        article1.setPrice(250);
        article1.setAmount(200);
        article1.setId(1);

        ArticleDto article2 = new ArticleDto();
        article2.setCalories(1050);
        article2.setPrice(25000);
        article2.setAmount(100);
        article2.setId(2);

        Unit unit1 = new Unit();
        unit1.setName("g");
        unit1.setId(1);
        Unit unit2 = new Unit();
        unit2.setName("kg");
        unit2.setId(2);
        Unit unit3 = new Unit();
        unit3.setName("mg");
        unit3.setId(3);

        ArticleUnitDto articleUnit1 = new ArticleUnitDto();
        articleUnit1.setUnit(unit1);
        articleUnit1.setId(1);
        articleUnit1.setArticle(article1);
        articleUnit1.setDefUnit(unit1);
        articleUnit1.setAmount(1.0d);

        ArticleUnitDto articleUnit2 = new ArticleUnitDto();
        articleUnit2.setUnit(unit2);
        articleUnit2.setId(2);
        articleUnit2.setDefUnit(unit1);
        articleUnit2.setArticle(article2);
        articleUnit2.setAmount(1000.0d);

        ArticleUnitDto articleUnit3 = new ArticleUnitDto();
        articleUnit3.setUnit(unit3);
        articleUnit3.setId(3);
        articleUnit3.setArticle(article2);
        articleUnit3.setAmount(0.001d);


        IngredientDto ingredient1 = new IngredientDto();
        ingredient1.setArticleUnit(articleUnit1);
        ingredient1.setAmount(400);
        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setArticleUnit(articleUnit1);
        ingredient2.setAmount(320);
        IngredientDto ingredient3 = new IngredientDto();
        ingredient3.setArticleUnit(articleUnit2);
        ingredient3.setAmount(5);
        IngredientDto ingredient4 = new IngredientDto();
        ingredient4.setArticleUnit(articleUnit3);
        ingredient4.setAmount(4340);

        List<IngredientDto> ingredients1= new ArrayList<>();
        ingredients1.add(ingredient1);
        ingredients1.add(ingredient2);

        List<IngredientDto> ingredients2= new ArrayList<>();
        ingredients2.add(ingredient3);
        ingredients2.add(ingredient4);

        List<IngredientDto> ingredients3= new ArrayList<>();
        ingredients3.add(ingredient1);
        ingredients3.add(ingredient2);
        ingredients3.add(ingredient3);
        ingredients3.add(ingredient4);

        // (amount * price) / article amount
        int totalPriceSimple = (400 * 250) / 200 + (320 * 250) / 200;
        int totalCalSimple   = (400 * 200) / 200 + (320 * 200) / 200;

        int totalPriceComp = (5000 * 25000) / 100 + (int)(4.340 * 25000) / 100 ;
        int totalCalComp = (5000 * 1050) / 100 + (int)(4.340 * 1050) / 100;

        int totalPriceAll= totalPriceComp + totalPriceSimple;
        int totalCalAll = totalCalComp + totalCalSimple;


        assertEquals(totalPriceSimple,recipeController.calculateCurrentPrice(ingredients1));
        assertEquals(totalCalSimple,recipeController.calculateCalories(ingredients1));

        assertEquals(totalPriceComp,recipeController.calculateCurrentPrice(ingredients2));
        assertEquals(totalCalComp,recipeController.calculateCalories(ingredients2));

        assertEquals(totalPriceAll,recipeController.calculateCurrentPrice(ingredients3));
        assertEquals(totalCalAll,recipeController.calculateCalories(ingredients3));

    }
}