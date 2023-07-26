package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.ArticleUnitDto;
import com.lydia.vurrukkulluk.dto.IngredientCreateDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.model.ArticleUnit;
import com.lydia.vurrukkulluk.model.Ingredient;
import com.lydia.vurrukkulluk.model.Recipe;
import com.lydia.vurrukkulluk.model.User;
import com.lydia.vurrukkulluk.service.IngredientService;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @Mock
    Ingredient ingredient;
    @Mock
    IngredientCreateDto ingredientCreateDto;
    @Mock
    Recipe recipe;
    @Mock
    User user;
    @Mock
    IngredientDto ingredientDto;
    @Mock
    IngredientService ingredientService;
    @Mock
    ModelMapper modelMapper;
    @Mock
    SecurityUtil securityUtil;

    List<Ingredient> ingredients;

    List<IngredientDto> ingredientDtos;

    IngredientController controller;
    @BeforeEach
    void makeController(){
        controller = new IngredientController(modelMapper,ingredientService,securityUtil);
        ingredients = new ArrayList<>() {{
            add(ingredient);
            add(ingredient);
            add(ingredient);
        }};
        ingredientDtos = new ArrayList<>(){{
            add(ingredientDto);
            add(ingredientDto);
            add(ingredientDto);
        }};
    }

    @Test
    void noArgsConstructor(){
        IngredientController test = new IngredientController();
        assertNotNull(test);
    }
    @Test
    void getAll() {
        when(modelMapper.map(ingredient, IngredientDto.class)).thenReturn(ingredientDto);
        assertNotNull(ingredients.get(0));
        when(ingredientService.getAllIngredients()).thenReturn(ingredients);

        assertEquals(ingredientDtos,controller.getAll());

    }
    @Test
    void addAuthorized() {
        addAndUpdateAuthorizationMock();
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(true);
        when(modelMapper.map(ingredientCreateDto, Ingredient.class)).thenReturn(ingredient);

        assertEquals("saved",controller.add(ingredientCreateDto));
        verify(ingredientService).saveIngredient(ingredient);
    }
    @Test
    void addNotAuthorized() {
        addAndUpdateAuthorizationMock();
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(false);

        assertEquals("not authorized",controller.add(ingredientCreateDto));
    }
    @Test
    void updateAuthorized() {
        addAndUpdateAuthorizationMock();
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(true);
        when(modelMapper.map(ingredientCreateDto, Ingredient.class)).thenReturn(ingredient);

        assertEquals("saved",controller.add(ingredientCreateDto));
        verify(ingredientService).saveIngredient(ingredient);
    }
    @Test
    void updateNotAuthorized() {
        addAndUpdateAuthorizationMock();
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(false);

        assertEquals("not authorized",controller.add(ingredientCreateDto));
    }

    @Test
    void deleteAuthorized() {
        when(ingredientService.getIngredientById(1)).thenReturn(ingredient);
        when(ingredient.getRecipe()).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(true);

        assertEquals("deleted",controller.delete(1));
        verify(ingredientService).deleteById(1);
    }

    @Test
    void deleteNotAuthorized() {
        when(ingredientService.getIngredientById(1)).thenReturn(ingredient);
        when(ingredient.getRecipe()).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
        when(securityUtil.isAuthorizedUserOrAdmin(1)).thenReturn(false);

        assertEquals("not authorized",controller.delete(1));
    }

    @Test
    void getId() {
        when(modelMapper.map(ingredient, IngredientDto.class)).thenReturn(ingredientDto);
        when(ingredientService.getIngredientById(1)).thenReturn(ingredient);

        assertEquals(ingredientDto,controller.getId(1));
    }

    @Test
    void getRecepId() {
        when(modelMapper.map(ingredient, IngredientDto.class)).thenReturn(ingredientDto);
        when(ingredientService.getIngredientsRecipeId(1)).thenReturn(ingredients);

        assertEquals(ingredientDtos,controller.getRecepId(1));
    }

    @Test
    void convertIngredientToDto() {
        controller.setModelMapper(new ModelMapper());

        Ingredient ingredient1 = new Ingredient(1,1.2d,new ArticleUnit(),new Recipe());
        ingredient1.getArticleunit().setId(1);
        ingredient1.getRecipe().setId(1);
        ingredient1.getRecipe().setTimeAdded(null);
        ArticleUnitDto articleUnitDto = new ArticleUnitDto();
        articleUnitDto.setId(1);
        IngredientDto ingredientDto1 = new IngredientDto(articleUnitDto,1.2d,1);

        assertEquals(ingredientDto1,controller.convertIngredientToDto(ingredient1));
    }

    @Test
    void reverseIngredientFromCreateDto() {
        controller.setModelMapper(new ModelMapper());
        IngredientCreateDto ingredientCreateDto1 = new IngredientCreateDto(1,2,3,12.34d);
        Ingredient ingredient1 = new Ingredient(1,12.34d,new ArticleUnit(),new Recipe());
        ingredient1.getRecipe().setId(3);
        ingredient1.getRecipe().setTimeAdded(null);
        ingredient1.getArticleunit().setId(2);
        Ingredient result = controller.reverseIngredientFromCreateDto(ingredientCreateDto1);
        result.getRecipe().setTimeAdded(null);
        assertEquals(ingredient1,result);
        
    }

    void addAndUpdateAuthorizationMock(){
        when(ingredientCreateDto.getRecipeId()).thenReturn(1);
        when(ingredientService.getIngredientById(1)).thenReturn(ingredient);
        when(ingredient.getRecipe()).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(1);
    }

}