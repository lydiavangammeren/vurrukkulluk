package com.lydia.vurrukkulluk.dto;

import com.lydia.vurrukkulluk.auth.AuthenticationRequest;
import com.lydia.vurrukkulluk.auth.AuthenticationRequestOTP;
import com.lydia.vurrukkulluk.auth.AuthenticationResponse;
import com.lydia.vurrukkulluk.auth.RegisterRequest;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.CoreMatchers.allOf;

class dtoTests {
    @ParameterizedTest
    @ValueSource(classes = {ArticleDto.class, CommentDto.class, CommentCreateDto.class,
            FavoriteDto.class,IngredientCreateDto.class,IngredientDto.class,IngredientInRecipeDto.class,
            KitchenCategoryDto.class,PreparationDto.class,PreparationInRecipeDto.class,
            RatingDto.class,RecipeCreateDto.class,RecipeDto.class,ShoppingCartDto.class,
            ShoppingCartPostDto.class,UserCreateDto.class,UserDto.class,
            AuthenticationResponse.class,AuthenticationRequest.class,RegisterRequest.class,
            AuthenticationRequestOTP.class})
    public void dtoConstructorGettersSettersHashEqualsTest(Class clazz) throws Exception {



        //assertNotNull(new clasz());

        assertThat(clazz, allOf(hasValidBeanConstructor(), hasValidBeanEquals(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode()));
    }

}