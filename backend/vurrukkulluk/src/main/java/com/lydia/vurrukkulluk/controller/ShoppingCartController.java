package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ShoppingCartDto;
import com.lydia.vurrukkulluk.dto.ShoppingCartPostDto;
import com.lydia.vurrukkulluk.model.Article;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.service.ArticleService;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.service.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/cart")
@CrossOrigin
public class ShoppingCartController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private IngredientService ingredientService;
    @PostMapping
    public ShoppingCartDto getCart(@RequestBody ShoppingCartPostDto recipesIds){

        HashMap<Integer,Double> totalArticlesAmount = getTotalArticlesAmount(recipesIds);

        HashMap<Integer,Integer> cart = getTotalArticlesToBuy(totalArticlesAmount);

        ShoppingCartDto result = new ShoppingCartDto();
        result.setArticlesToBuy(cart);
        result.setArticlesAmount(totalArticlesAmount);
        return result;
    }

    private HashMap<Integer,Double> getTotalArticlesAmount(ShoppingCartPostDto recipesIds){
        HashMap<Integer,Double> totalArticlesAmount = new HashMap<>();
        for (int i: recipesIds.getRecipeIds()) {
            List<Ingredient> ingredients = ingredientService.getIngredientsRecipeId(i);
            for (Ingredient ingredient: ingredients){
                Integer key = ingredient.getArticle().getId();

                if (totalArticlesAmount.containsKey(key)){
                    totalArticlesAmount.put(key, round(totalArticlesAmount.get(key) + ingredient.getAmount(),2));
                } else {
                    totalArticlesAmount.put(key,ingredient.getAmount());
                }
            }
        }
        return totalArticlesAmount;
    }

    private HashMap<Integer,Integer> getTotalArticlesToBuy( HashMap<Integer,Double> totalArticlesAmount){
        HashMap<Integer,Integer> cart = new HashMap<>();
        for ( Integer articleId: totalArticlesAmount.keySet()) {
            Article article = articleService.getArticleById(articleId);
            Integer articleAmount = article.getAmount();
            int totalRoundedDown = (int) round(totalArticlesAmount.get(articleId),0);
            cart.put(articleId,divideAndRoundUp(totalRoundedDown,articleAmount));
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

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private double convertUnitToStandard(Ingredient ingredient){
        return ingredient.getArticleunit().getAmount() * ingredient.getAmount();
    }

}
