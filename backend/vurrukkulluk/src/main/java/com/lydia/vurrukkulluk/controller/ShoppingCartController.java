package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ShoppingCartDto;
import com.lydia.vurrukkulluk.dto.ShoppingCartPostDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("api/cart")
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    private RecipeService recipeService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private IngredientService ingredientService;
    @PostMapping
    public ShoppingCartDto getCart(@RequestBody ShoppingCartPostDto recipesIds){

        HashMap<Integer,Integer> totalArticlesAmount = getTotalArticlesAmount(recipesIds);

        HashMap<Integer,Integer> cart = getTotalArticlesToBuy(totalArticlesAmount);

        ShoppingCartDto result = new ShoppingCartDto();
        result.setArticlesToBuy(cart);
        result.setArticlesAmount(totalArticlesAmount);
        return result;
    }

    private HashMap<Integer,Integer> getTotalArticlesAmount(ShoppingCartPostDto recipesIds){
        HashMap<Integer,Integer> totalArticlesAmount = new HashMap<>();
        for (int i: recipesIds.getRecipeIds()) {
            List<Ingredient> ingredients = ingredientService.getIngredientsRecipeId(i);
            for (Ingredient ingredient: ingredients){
                Integer key = ingredient.getArticleUnits().getArticle().getId();
                if (totalArticlesAmount.containsKey(key)){
                    totalArticlesAmount.put(key, totalArticlesAmount.get(key) + ingredient.getAmount());
                } else {
                    totalArticlesAmount.put(key,ingredient.getAmount());
                }
            }
        }
        return totalArticlesAmount;
    }

    private HashMap<Integer,Integer> getTotalArticlesToBuy( HashMap<Integer,Integer> totalArticlesAmount){
        HashMap<Integer,Integer> cart = new HashMap<>();
        for ( Integer articleId: totalArticlesAmount.keySet()) {
            Article article = articleService.getArticleById(articleId);
            Integer articleAmount = article.getAmount();
            cart.put(articleId,divideAndRoundUp(totalArticlesAmount.get(articleId), articleAmount));
        }
        return cart;
    }


    private Integer divideAndRoundUp(Integer divided,Integer divider){
        int result = 0;
        if (divider != 0) {
            result = ((divided % divider) == 0) ? (divided / divider) : ((divided / divider) + 1);
        }
        return result;
    }

}
