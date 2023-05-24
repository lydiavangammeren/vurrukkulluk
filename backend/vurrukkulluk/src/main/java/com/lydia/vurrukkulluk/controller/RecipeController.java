package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.CommentDto;
import com.lydia.vurrukkulluk.dto.IngredientDto;
import com.lydia.vurrukkulluk.dto.RecipeCreateDto;
import com.lydia.vurrukkulluk.dto.RecipeDto;
import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recipes")
@CrossOrigin
public class RecipeController {
  @Autowired
  private RecipeService recipeService;
  @Autowired
  private PreparationService preparationService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private RatingService ratingService;
  @Autowired
  private IngredientService ingredientService;
  @Autowired
  private FavoriteService favoriteService;
  @Autowired
  private KitchenTypeService kitchenTypeService;
  @Autowired
  private KitchenRegionService kitchenRegionService;
  @Autowired
  private UserService userService;
  @Autowired
  private ModelMapper modelMapper;

  @PostMapping()
  public Recipe add(@RequestBody RecipeCreateDto recipeCreateDto) {
    Recipe recipe = new Recipe();
    recipe.setTitle(recipeCreateDto.getTitle());
    recipe.setDescription(recipeCreateDto.getDescription());
    recipe.setSlug(recipeCreateDto.getSlug());
    recipe.setImage(recipeCreateDto.getImage());
    KitchenType kitchenType = kitchenTypeService.getById(recipeCreateDto.getKitchenTypeId());
    recipe.setKitchenType(kitchenType);
    KitchenRegion kitchenRegion = kitchenRegionService.getById(recipeCreateDto.getKitchenRegionId());
    recipe.setKitchenRegion(kitchenRegion);
    User user = userService.getUserById(recipeCreateDto.getUserId());
    recipe.setUser(user);
    recipeService.saveRecipe(recipe);

    /*
    recipeCreateDto.getCategoryIds().forEach((categoryId) ->{
      KitchenCategoriesLinkList link = new KitchenCategoriesLinkList();
      link.setRecipe();
      kitchenCategoriesLinkListService.save();
    });*/


    return recipe; //"New recipe is added";
  }

  @GetMapping()
  public List<Recipe> get() {
    return recipeService.getAllRecipes();
  }

  @GetMapping("/{slug}")
  public RecipeDto getTitle(@PathVariable String slug){
    Recipe recipe = recipeService.getRecipeBySlug(slug);
    RecipeDto recipeDto = convertRecipeToDto(recipe);

    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipe.getId());
    List<CommentDto> commentsDto= comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
    recipeDto.setComments(commentsDto);

    List<Ingredient> ingredients = ingredientService.getIngredientsRecipeId(recipeDto.getId());
    List<IngredientDto> ingredientsDto = ingredients.stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    recipeDto.setIngredients(ingredientsDto);

    recipeDto.setAvgRating(0);

    return recipeDto;
  }

  @PutMapping()
  public String update(@RequestBody Recipe recipe) {
    recipeService.updateRecipe(recipe);
    return "Recipe is updated";
  }

  @DeleteMapping()
  public String delete(@RequestBody Recipe recipe) {
    recipeService.deleteRecipe(recipe);
    return "Recipe is deleted";
  }

  public RecipeDto convertRecipeToDto(Recipe recipe){
    return modelMapper.map(recipe, RecipeDto.class);
  }
  public CommentDto convertCommentToDto(Comment comment){
    return modelMapper.map(comment,CommentDto.class);
  }

  public IngredientDto convertIngredientToDto(Ingredient ingredient){
    return modelMapper.map(ingredient,IngredientDto.class);
  }

}
