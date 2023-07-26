package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.*;
import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.service.*;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private PreparationService preparationService;
    @Mock
    private CommentService commentService;
    @Mock
    private RatingService ratingService;
    @Mock
    private IngredientService ingredientService;
    @Mock
    private KitchenCategoriesLinkService kitchenCategoriesLinkService;
    @Mock
    private UserService userService;
    @Mock
    private ImageService imageService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private SecurityUtil securityUtil;
    @Mock Recipe recipe;
    @Mock RecipeDto recipeDto;
    @Mock Comment comment;
    @Mock CommentDto commentDto;
    @Mock Ingredient ingredient;
    @Mock IngredientDto ingredientDto;
    @Mock KitchenCategoriesLink kitchenCategoriesLink;
    @Mock KitchenCategory kitchenCategory;
    @Mock KitchenCategoryDto kitchenCategoryDto;
    @Mock Preparation preparation;
    @Mock PreparationDto preparationDto;
    RecipeController controller;
    @BeforeEach
    void setUp(){
        controller = new RecipeController(recipeService,preparationService,
                commentService,ratingService,
                ingredientService,kitchenCategoriesLinkService,
                userService,imageService,modelMapper,securityUtil);
    }


    @Test
    public void priceAndCalorieCalculationTest(){
        controller.setModelMapper(new ModelMapper());
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
        ingredient1.setArticleunit(articleUnit1);
        ingredient1.setAmount(400);
        IngredientDto ingredient2 = new IngredientDto();
        ingredient2.setArticleunit(articleUnit1);
        ingredient2.setAmount(320);
        IngredientDto ingredient3 = new IngredientDto();
        ingredient3.setArticleunit(articleUnit2);
        ingredient3.setAmount(5);
        IngredientDto ingredient4 = new IngredientDto();
        ingredient4.setArticleunit(articleUnit3);
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


        assertEquals(totalPriceSimple,controller.calculateCurrentPrice(ingredients1));
        assertEquals(totalCalSimple,controller.calculateCalories(ingredients1));

        assertEquals(totalPriceComp,controller.calculateCurrentPrice(ingredients2));
        assertEquals(totalCalComp,controller.calculateCalories(ingredients2));

        assertEquals(totalPriceAll,controller.calculateCurrentPrice(ingredients3));
        assertEquals(totalCalAll,controller.calculateCalories(ingredients3));

    }

    @Test
    void add() {
    }

    @Test
    void get() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe);
        recipes.add(recipe);
        recipes.add(recipe);

        List<RecipeDto> recipeDtos = new ArrayList<>();
        recipeDtos.add(recipeDto);
        recipeDtos.add(recipeDto);
        recipeDtos.add(recipeDto);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);
        List<CommentDto> commentDtos = new ArrayList<>();
        commentDtos.add(commentDto);
        commentDtos.add(commentDto);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);
        List<IngredientDto> ingredientDtos = new ArrayList<>();
        ingredientDtos.add(ingredientDto);
        ingredientDtos.add(ingredientDto);

        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);
        List<PreparationDto> preparationDtos = new ArrayList<>();
        preparationDtos.add(preparationDto);
        preparationDtos.add(preparationDto);

        List<KitchenCategoriesLink> kitchenCategoriesLinks = new ArrayList<>();
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        List<KitchenCategoryDto> kitchenCategories = new ArrayList<>();
        kitchenCategories.add(kitchenCategoryDto);
        kitchenCategories.add(kitchenCategoryDto);
        kitchenCategories.add(kitchenCategoryDto);

        when(recipeService.getAllRecipes()).thenReturn(recipes);
        when(modelMapper.map(recipe,RecipeDto.class)).thenReturn(recipeDto);
        when(recipeDto.getId()).thenReturn(1);
        // add comments
        when(commentService.getAllCommentsOfRecipe(1)).thenReturn(comments);
        when(modelMapper.map(comment,CommentDto.class)).thenReturn(commentDto);
        // add ingredient
        when(ingredientService.getIngredientsRecipeId(1)).thenReturn(ingredients);
        when(modelMapper.map(ingredient,IngredientDto.class)).thenReturn(ingredientDto);
        //add preparation
        when(preparationService.getAllPreparationsRecipe(1)).thenReturn(preparations);
        when(modelMapper.map(preparation,PreparationDto.class)).thenReturn(preparationDto);
        //add kitchencategory
        when(kitchenCategoriesLinkService.getKCLinkByRecipeId(1)).thenReturn(kitchenCategoriesLinks);
        when(kitchenCategoriesLink.getKitchenCategory()).thenReturn(kitchenCategory);
        when(modelMapper.map(kitchenCategory,KitchenCategoryDto.class)).thenReturn(kitchenCategoryDto);

        //add rating
        when(ratingService.getAvgRatingOfRecipe(1)).thenReturn(3.5f);
        assertEquals(recipeDtos,controller.get());

        verify(recipeDto,times(3)).setComments(commentDtos);
        verify(recipeDto,times(3)).setIngredients(ingredientDtos);
        verify(recipeDto,times(3)).setPreparation(preparationDtos);
        verify(recipeDto,times(3)).setCategories(kitchenCategories);
        verify(recipeDto,times(3)).setAvgRating(3.5F);
    }

    @Test
    void getTitle() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void convertRecipeToDto() {
    }

    @Test
    void convertCommentToDto() {
        controller.setModelMapper(new ModelMapper());
        User user1 = new User();
        user1.setId(1);
        Recipe recipe = new Recipe();
        recipe.setId(1);
        Comment comment1 = new Comment(1,user1,recipe,"comment");
        CommentDto commentDto1 = new CommentDto();
        commentDto1.setId(1);
        UserDto userDto =  new UserDto();
        userDto.setId(1);
        commentDto1.setUser(userDto);
        commentDto1.setCommentText("comment");

        assertEquals(commentDto1,controller.convertCommentToDto(comment1));

    }

    @Test
    void convertIngredientToDto() {
    }

    @Test
    void convertPreparationToDto() {
    }

    @Test
    void convertCategoryToDto() {
    }

    @Test
    void revertCreateRecipeDto() {
    }
}