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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    @Mock CategoryLinkInRecipeDto categoryLinkInRecipeDto;
    @Mock RecipeCreateDto recipeCreateDto;
    @Mock Comment comment;
    @Mock CommentDto commentDto;
    @Mock Ingredient ingredient;
    @Mock IngredientInRecipeDto ingredientInRecipeDto;
    @Mock IngredientDto ingredientDto;
    @Mock KitchenCategoriesLink kitchenCategoriesLink;
    @Mock KitchenCategory kitchenCategory;
    @Mock KitchenCategoryDto kitchenCategoryDto;
    @Mock Preparation preparation;
    @Mock PreparationDto preparationDto;
    @Mock PreparationInRecipeDto preparationInRecipeDto;
    @Mock User user;
    @Mock Rating rating;
    @Mock Image image;
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

        UnitDto unit1 = new UnitDto();
        unit1.setName("g");
        unit1.setId(1);
        UnitDto unit2 = new UnitDto();
        unit2.setName("kg");
        unit2.setId(2);
        UnitDto unit3 = new UnitDto();
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
    void addAuthorized() {
        when(recipeCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(true);


        when(modelMapper.map(recipeCreateDto,Recipe.class)).thenReturn(recipe);
        when(recipeService.saveRecipe(recipe)).thenReturn(recipe);
        when(recipe.getId()).thenReturn(1);
        List<CategoryLinkInRecipeDto> categoryIds = new ArrayList<>();
        categoryIds.add(categoryLinkInRecipeDto);
        categoryIds.add(categoryLinkInRecipeDto);
        List<IngredientInRecipeDto> ingredients = new ArrayList<>();
        ingredients.add(ingredientInRecipeDto);
        ingredients.add(ingredientInRecipeDto);
        ingredients.add(ingredientInRecipeDto);
        List<PreparationInRecipeDto> preparations = new ArrayList<>();
        preparations.add(preparationInRecipeDto);
        preparations.add(preparationInRecipeDto);

        when(recipeCreateDto.getIngredients()).thenReturn(ingredients);
        when(modelMapper.map(ingredientInRecipeDto,Ingredient.class)).thenReturn(ingredient);
        when(ingredient.getRecipe()).thenReturn(recipe);
        when(recipeCreateDto.getPreparations()).thenReturn(preparations);
        when(modelMapper.map(preparationInRecipeDto,Preparation.class)).thenReturn(preparation);
        when(preparation.getRecipe()).thenReturn(recipe);
        when(recipeCreateDto.getCategoryIds()).thenReturn(categoryIds);
        when(modelMapper.map(categoryLinkInRecipeDto,KitchenCategoriesLink.class)).thenReturn(kitchenCategoriesLink);
        when(kitchenCategoriesLink.getRecipe()).thenReturn(recipe);


        assertEquals(ResponseEntity.status(HttpStatus.OK).body("1"),controller.add(recipeCreateDto));

        verify(recipe).setId(0);
        verify(recipeService).saveRecipe(recipe);
        verify(ingredientService,times(3)).saveIngredient(ingredient);
        verify(preparationService,times(2)).savePreparation(preparation);
        verify(kitchenCategoriesLinkService,times(2)).saveKCLink(kitchenCategoriesLink);

    }
    @Test
    void addNotAuthorized() {
        when(recipeCreateDto.getUserId()).thenReturn(1);
        when(securityUtil.isIdOfAuthorizedUser(1)).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.add(recipeCreateDto));
        verifyNoInteractions(recipeService);


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
    void getSlug() {
        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);

        List<KitchenCategoriesLink> kitchenCategoriesLinks = new ArrayList<>();
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);

        when(recipeService.getRecipeBySlug("slug")).thenReturn(recipe);
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

        assertEquals(ResponseEntity.status(HttpStatus.OK).body(recipeDto),controller.getSlug("slug"));

    }

    @Test
    void update() {
    }

    @Test
    void deleteAuthorized() {
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(2);
        when(securityUtil.isAuthorizedUserOrAdmin(2)).thenReturn(true);

        List<Comment> comments = new ArrayList<>();
        comments.add(comment);
        comments.add(comment);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);
        ingredients.add(ingredient);

        List<Preparation> preparations = new ArrayList<>();
        preparations.add(preparation);
        preparations.add(preparation);

        List<KitchenCategoriesLink> kitchenCategoriesLinks = new ArrayList<>();
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);
        kitchenCategoriesLinks.add(kitchenCategoriesLink);

        List<Rating> ratings = new ArrayList<>();
        ratings.add(rating);
        ratings.add(rating);
        ratings.add(rating);

        when(ingredientService.getIngredientsRecipeId(1)).thenReturn(ingredients);
        when(ingredient.getId()).thenReturn(3);
        when(commentService.getAllCommentsOfRecipe(1)).thenReturn(comments);
        when(comment.getId()).thenReturn(4);
        when(kitchenCategoriesLinkService.getKCLinkByRecipeId(1)).thenReturn(kitchenCategoriesLinks);
        when(kitchenCategoriesLink.getId()).thenReturn(5);
        when(preparationService.getAllPreparationsRecipe(1)).thenReturn(preparations);
        when(preparation.getId()).thenReturn(6);
        when(ratingService.getAllRatingsRecipe(1)).thenReturn(ratings);
        when(rating.getId()).thenReturn(7);
        when(recipe.getImage()).thenReturn(image);
        when(image.getId()).thenReturn(8);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body("Recipe is deleted"), controller.delete(1));
        verify(ingredientService,times(2)).deleteById(3);
        verify(commentService,times(2)).deleteCommentById(4);
        verify(kitchenCategoriesLinkService,times(3)).deleteById(5);
        verify(preparationService,times(2)).deleteById(6);
        verify(ratingService,times(3)).deleteById(7);
        verify(imageService,times(1)).deleteImage(8);
        verify(recipeService).deleteById(1);



    }
    @Test
    void deleteNotAuthorized() {
        when(recipeService.getRecipeById(1)).thenReturn(recipe);
        when(recipe.getUser()).thenReturn(user);
        when(user.getId()).thenReturn(2);
        when(securityUtil.isAuthorizedUserOrAdmin(2)).thenReturn(false);

        assertEquals(ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized"),controller.delete(1));
        verify(recipeService).getRecipeById(1);
        verifyNoMoreInteractions(recipeService);

    }

    @Test
    void convertRecipeToDto() {
        controller.setModelMapper(new ModelMapper());

        KitchenType kitchenType = new KitchenType(2,"kitchentype");
        KitchenRegion kitchenRegion = new KitchenRegion(3,"kitchenregion");
        User user1 = new User();
        user1.setId(4);
        Image image1 = new Image();
        image1.setId(5);
        Recipe recipe1 = new Recipe(1,kitchenType,kitchenRegion,user1,"title"
        ,"slug","description",null,image1,4);
        RecipeDto recipeDto1 = new RecipeDto(1,"title","slug","description",4,0,0,kitchenType,
                kitchenRegion,null,null,null,null,0.0f,5);

        assertEquals(recipeDto1,controller.convertRecipeToDto(recipe1));
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
    void convertPreparationToDto() {
        controller.setModelMapper(new ModelMapper());
        Preparation preparation1 = new Preparation(1,new Recipe(),3,"instructions");
        preparation1.getRecipe().setId(2);
        PreparationDto preparationDto1 = new PreparationDto(1,3,"instructions",2);
        assertEquals(preparationDto1,controller.convertPreparationToDto(preparation1));
    }

    @Test
    void convertCategoryToDto() {
        controller.setModelMapper(new ModelMapper());
        KitchenCategory kitchenCategory1 = new KitchenCategory(123,"name");
        KitchenCategoryDto kitchenCategoryDto1 = new KitchenCategoryDto("name");
        assertEquals(kitchenCategoryDto1,controller.convertCategoryToDto(kitchenCategory1));
    }

    @Test
    void revertCreateRecipeDto() {
        controller.setModelMapper(new ModelMapper());

        List<CategoryLinkInRecipeDto> categoryIds = new ArrayList<>();
        categoryIds.add(new CategoryLinkInRecipeDto(1));
        categoryIds.add(new CategoryLinkInRecipeDto(2));
        List<IngredientInRecipeDto> ingredients = new ArrayList<>();
        ingredients.add(new IngredientInRecipeDto(1,15));
        ingredients.add(new IngredientInRecipeDto(2,15));
        ingredients.add(new IngredientInRecipeDto(3,15));
        List<PreparationInRecipeDto> preparations = new ArrayList<>();
        preparations.add(new PreparationInRecipeDto(2,"instruction"));

        RecipeCreateDto recipeCreateDto1 = new RecipeCreateDto("title",2,4,"slug","description",
                3,5,6,categoryIds,ingredients,preparations,1);
        Recipe recipe1 = new Recipe(1,new KitchenType(),new KitchenRegion(),new User(),"title",
                "slug","description",null,new Image(),4);
        recipe1.getUser().setId(6);
        recipe1.getImage().setId(2);
        recipe1.getKitchenRegion().setId(5);
        recipe1.getKitchenType().setId(3);
        Recipe recipe2 = controller.revertCreateRecipeDto(recipeCreateDto1);
        recipe2.setTimeAdded(null);
        assertEquals(recipe1,recipe2);
    }

    @Test
    void revertIngredientInRecipeDto() {
        controller.setModelMapper(new ModelMapper());
        IngredientInRecipeDto inRecipeDto = new IngredientInRecipeDto(3,15.6d);
        Ingredient ingredient1 = new Ingredient(0,15.6d,new ArticleUnit(),new Recipe());
        ingredient1.getRecipe().setTimeAdded(null);
        ingredient1.getRecipe().setId(5);
        ingredient1.getArticleunit().setId(3);

        assertEquals(ingredient1,controller.revertIngredientInRecipeDto(inRecipeDto,5));

    }
    @Test
    void revertPreparationInRecipeDto(){
        controller.setModelMapper(new ModelMapper());
        PreparationInRecipeDto inRecipeDto = new PreparationInRecipeDto(2,"instructions");
        Preparation preparation1 = new Preparation(0,new Recipe(),2,"instructions");
        preparation1.getRecipe().setId(5);
        preparation1.getRecipe().setTimeAdded(null);

        assertEquals(preparation1,controller.revertPreparationInRecipeDto(inRecipeDto,5));


    }

    @Test
    void revertCategoryLinkInRecipeDto() {
        controller.setModelMapper(new ModelMapper());

        CategoryLinkInRecipeDto linkDto = new CategoryLinkInRecipeDto(3);
        KitchenCategoriesLink link =
                new KitchenCategoriesLink(0,new KitchenCategory(),new Recipe());

        link.getRecipe().setId(5);
        link.getRecipe().setTimeAdded(null);
        link.getKitchenCategory().setId(3);

        assertEquals(link,controller.revertCategoryLinkInRecipeDto(linkDto,5));

    }
}