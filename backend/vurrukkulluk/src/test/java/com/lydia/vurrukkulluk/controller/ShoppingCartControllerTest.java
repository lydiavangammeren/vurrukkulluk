package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ShoppingCartDto;
import com.lydia.vurrukkulluk.dto.ShoppingCartPostDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

    @Mock
    ArticleService articleService;
    @Mock
    IngredientService ingredientService;
    @Mock
    RecipeService recipeService;
    @Mock
    Recipe recipe;
    ShoppingCartController controller;

    @BeforeEach
    void makeController(){
        controller = new ShoppingCartController(articleService,ingredientService,recipeService);
    }

    @Test
    void getCart() {

        Article article1 = new Article();
        article1.setId(1);
        article1.setAmount(100);

        Article article2 = new Article();
        article2.setId(2);
        article2.setAmount(5);

        Ingredient ingredient1 = new Ingredient(1,10.0d,new ArticleUnit(),new Recipe());
        ingredient1.getArticleunit().setArticle(article1);
        ingredient1.getArticleunit().setAmount(10.0d);
        Ingredient ingredient2 = new Ingredient(2,15.3d,new ArticleUnit(),new Recipe());
        ingredient2.getArticleunit().setArticle(article2);
        ingredient2.getArticleunit().setAmount(1.0);

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(ingredient1);
        ingredients1.add(ingredient2);

        List<Ingredient> ingredients2 = new ArrayList<>();
        ingredients2.add(ingredient1);
        ingredients2.add(ingredient2);
        ingredients2.add(ingredient2);


        HashMap<Integer,Double> totalArticlesAmount = new HashMap<>() ;
        totalArticlesAmount.put(1,200.0d);
        totalArticlesAmount.put(2,45.90d);
        HashMap<Integer,Integer> cart = new HashMap<>();
        cart.put(1,2);
        cart.put(2,10);

        ShoppingCartDto expectedResult = new ShoppingCartDto();
        expectedResult.setArticlesToBuy(cart);
        expectedResult.setArticlesAmount(totalArticlesAmount);

        ShoppingCartPostDto postDto = new ShoppingCartPostDto();
        HashMap<Integer,Integer> recipeIds = new HashMap<>();
        recipeIds.put(1,4);
        recipeIds.put(2,4);
        postDto.setRecipeIdsPersonMap(recipeIds);

        when(ingredientService.getIngredientsRecipeId(1)).thenReturn(ingredients1);
        when(ingredientService.getIngredientsRecipeId(2)).thenReturn(ingredients2);
        when(articleService.getArticleById(1)).thenReturn(article1);
        when(articleService.getArticleById(2)).thenReturn(article2);
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipeService.getRecipeById(2)).thenReturn(recipe);
        when(recipe.getPersons()).thenReturn(4);

        ShoppingCartDto result = controller.getCart(postDto);
        assertEquals(expectedResult.getArticlesAmount(),result.getArticlesAmount());
        assertEquals(expectedResult.getArticlesToBuy(),result.getArticlesToBuy());

    }
}