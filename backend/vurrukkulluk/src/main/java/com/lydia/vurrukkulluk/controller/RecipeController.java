package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.*;
import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.service.*;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
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
  private ArticleService articleService;
  @Autowired
  private FavoriteService favoriteService;
  @Autowired
  private KitchenTypeService kitchenTypeService;
  @Autowired
  private KitchenRegionService kitchenRegionService;
  @Autowired
  private KitchenCategoriesLinkService kitchenCategoriesLinkService;
  @Autowired
  private KitchenCategoryService kitchenCategoryService;
  @Autowired
  private UserService userService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private SecurityUtil securityUtil;

  @PostMapping()
  public String add(@RequestBody RecipeCreateDto recipeCreateDto) {
    User user = userService.getUserById(recipeCreateDto.getUserId());
    if (!securityUtil.isIdOfAuthorizedUser(user.getId())){
      return "not authorized";
    }
    //recipe creation
    Recipe recipe = new Recipe();
    recipe.setUser(user);
    recipe.setTitle(recipeCreateDto.getTitle());
    recipe.setDescription(recipeCreateDto.getDescription());
    recipe.setSlug(recipeCreateDto.getSlug());
    KitchenType kitchenType = kitchenTypeService.getById(recipeCreateDto.getKitchenTypeId());
    recipe.setKitchenType(kitchenType);
    KitchenRegion kitchenRegion = kitchenRegionService.getById(recipeCreateDto.getKitchenRegionId());
    recipe.setKitchenRegion(kitchenRegion);
    User user = userService.getUserById(recipeCreateDto.getUserId());
    recipe.setUser(user);
    recipeService.saveRecipe(recipe);

    //ingredient creation
    recipeCreateDto.getIngredients().forEach((ingredientCreateDto) ->{
      Ingredient ingredient = new Ingredient();
      ingredient.setAmount(ingredientCreateDto.getAmount());
      Article article = new Article();
      article.setId(ingredientCreateDto.getArticleId());
      ingredient.setArticle(article);
      ingredient.setRecipe(recipe);
      ingredientService.saveIngredient(ingredient);
    });

    //category link creation
    recipeCreateDto.getCategoryIds().forEach((categoryId) ->{
      KitchenCategoriesLink link = new KitchenCategoriesLink();
      KitchenCategory kitchenCategory = new KitchenCategory();
      kitchenCategory.setId(categoryId);
      link.setKitchenCategory(kitchenCategory);
      link.setRecipe(recipe);
      kitchenCategoriesLinkService.saveKCLink(link);
    });

    return "New recipe is added";
  }

  @GetMapping()
  public List<RecipeDto> get() {
    List<Recipe> allRecipes = recipeService.getAllRecipes();
    List<RecipeDto> allRecipesShort = allRecipes.stream().map(this::convertRecipeToDto).collect(Collectors.toList());

    allRecipesShort.forEach(this::fillRecipeDto);

    return allRecipesShort;
  }

  @GetMapping("/{slug}")
  public RecipeDto getTitle(@PathVariable String slug){
    Recipe recipe = recipeService.getRecipeBySlug(slug);
    RecipeDto recipeDto = convertRecipeToDto(recipe);

    fillRecipeDto(recipeDto);

    return recipeDto;
  }

  @PutMapping("/{id}")
  public String update(@RequestBody Recipe recipe, @PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(recipeService.getRecipeById(id).getUser().getId())) {
      return "not authorized";
    }
    recipeService.updateRecipe(recipe);
    return "Recipe is updated";

  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable int id) {

    if (!securityUtil.isAuthorizedUserOrAdmin(recipeService.getRecipeById(id).getUser().getId())) {
      return "not authorized";
    }
    ingredientService.getIngredientsRecipeId(id).stream().forEach(ingredient -> ingredientService.deleteById(ingredient.getId()));
    commentService.getAllCommentsOfRecipe(id).stream().forEach(comment -> commentService.deleteCommentById(comment.getId()));
    kitchenCategoriesLinkService.getKCLinkByRecipeId(id).stream().forEach(kitchenCategoriesLink -> kitchenCategoriesLinkService.deleteById(kitchenCategoriesLink.getId()));
    preparationService.getAllPreparationsRecipe(id).stream().forEach(preparation -> preparationService.deleteById(preparation.getId()));
    ratingService.getAllRatingsRecipe(id).stream().forEach(rating -> ratingService.deleteById(rating.getId()));
    Image image = recipeService.getRecipeById(id).getImage();
    imageService.deleteImage(image.getId());
    recipeService.deleteById(id);
    return "Recipe is deleted";

  }

  public int calculateCurrentPrice(List<IngredientDto> ingredients){

    return ingredients.stream().map(ingredientDto -> {
        int amount = ingredientDto.getAmount();
        int articlePrice = ingredientDto.getArticle().getPrice();
        int articleAmount = ingredientDto.getArticle().getAmount();
        return (amount * articlePrice)/ articleAmount;})
            .reduce(0, (a, b) -> a+b);
  }

  public int calculateCalories(List<IngredientDto> ingredients) {

    return ingredients.stream().map(ingredientDto -> {
              int amount = ingredientDto.getAmount();
              int articleCalories = ingredientDto.getArticle().getCalories();
              int articleAmount = ingredientDto.getArticle().getAmount();
              return (amount * articleCalories)/ articleAmount;})
            .reduce(0, (a, b) -> a+b);
  }

  public RecipeDto fillRecipeDto(RecipeDto recipeDto){

    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipeDto.getId());
    List<CommentDto> commentsDto= comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
    recipeDto.setComments(commentsDto);

    List<Ingredient> ingredients = ingredientService.getIngredientsRecipeId(recipeDto.getId());
    List<IngredientDto> ingredientsDto = ingredients.stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    recipeDto.setIngredients(ingredientsDto);
    recipeDto.setPrice(calculateCurrentPrice(ingredientsDto));
    recipeDto.setCalories(calculateCalories(ingredientsDto));

    List<KitchenCategory> kitchenCategories = new ArrayList<>();
    List<KitchenCategoriesLink> kitchenCategoriesLinks = kitchenCategoriesLinkService.getKCLinkByRecipeId(recipeDto.getId());
    kitchenCategoriesLinks.forEach((link) -> {
      kitchenCategories.add(link.getKitchenCategory());
    });
    recipeDto.setCategories(kitchenCategories);

    List<Preparation> preparationsSteps = preparationService.getAllPreparationsRecipe(recipeDto.getId());
    List<PreparationDto> preparationsStepsDto = preparationsSteps.stream().map(this::convertPreparationToDto).collect(Collectors.toList());
    recipeDto.setPreparation(preparationsStepsDto);

    recipeDto.setAvgRating(ratingService.getAvgRatingOfRecipe(recipeDto.getId()));

    return recipeDto;
  }

  public RecipeDto convertRecipeToDto(Recipe recipe){
    return modelMapper.map(recipe, RecipeDto.class);
  }
  public CommentDto convertCommentToDto(Comment comment){
    return modelMapper.map(comment,CommentDto.class);
  }

  public RecipeShortDto covertRecipeToShortDto(Recipe recipe){
    return modelMapper.map(recipe, RecipeShortDto.class);
  }

  public IngredientDto convertIngredientToDto(Ingredient ingredient){
    return modelMapper.map(ingredient,IngredientDto.class);
  }

  public PreparationDto convertPreparationToDto(Preparation preparation){
    return modelMapper.map(preparation,PreparationDto.class);
  }

}
