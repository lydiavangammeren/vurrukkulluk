package com.lydia.vurrukkulluk.controller;

import com.lydia.vurrukkulluk.dto.*;
import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.service.*;
import com.lydia.vurrukkulluk.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("api/recipes")
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
  private KitchenCategoriesLinkService kitchenCategoriesLinkService;
  @Autowired
  private UserService userService;
  @Autowired
  private ImageService imageService;
  @Autowired
  private ModelMapper modelMapper;
  @Autowired
  private SecurityUtil securityUtil;

  @PostMapping()
  public ResponseEntity<String> add(@RequestBody RecipeCreateDto recipeCreateDto) {
    if (!securityUtil.isIdOfAuthorizedUser(recipeCreateDto.getUserId())){
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    //recipe creation
    Recipe recipe = revertCreateRecipeDto(recipeCreateDto);
    recipe.setId(0);
    final Recipe finalRecipe = recipeService.saveRecipe(recipe);

    //ingredient creation
    recipeCreateDto.getIngredients().forEach(ingredientInRecipeDto ->{
      Ingredient ingredient = revertIngredientInRecipeDto(ingredientInRecipeDto,finalRecipe.getId());
      ingredientService.saveIngredient(ingredient);
    });

    //preparation creation
    recipeCreateDto.getPreparations().forEach(preparationInRecipeDto -> {
      Preparation preparation = revertPreparationInRecipeDto(preparationInRecipeDto,finalRecipe.getId());
      preparationService.savePreparation(preparation);
    });

    //category link creation
    recipeCreateDto.getCategoryIds().forEach((categoryId) ->{
      KitchenCategoriesLink link = revertCategoryLinkInRecipeDto(categoryId,finalRecipe.getId());
      kitchenCategoriesLinkService.saveKCLink(link);
    });

    return ResponseEntity.status(HttpStatus.OK).body("New recipe is added");
  }



  @GetMapping()
  public List<RecipeDto> get() {
    List<Recipe> allRecipes = recipeService.getAllRecipes();
    return allRecipes.stream().map(this::fillRecipeDto).collect(Collectors.toList());
  }

  @GetMapping("/{slug}")
  public ResponseEntity<RecipeDto> getSlug(@PathVariable String slug){
    Recipe recipe = recipeService.getRecipeBySlug(slug);
    if (recipe==null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return ResponseEntity.status(HttpStatus.OK).body(fillRecipeDto(recipe));
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> update(@RequestBody Recipe recipe, @PathVariable int id) {
    if (!securityUtil.isAuthorizedUserOrAdmin(recipeService.getRecipeById(id).getUser().getId())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    recipeService.updateRecipe(recipe);
    return ResponseEntity.status(HttpStatus.OK).body("Recipe is updated");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {

    if (!securityUtil.isAuthorizedUserOrAdmin(recipeService.getRecipeById(id).getUser().getId())) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body("not authorized");
    }
    ingredientService.getIngredientsRecipeId(id).stream().forEach(ingredient -> ingredientService.deleteById(ingredient.getId()));
    commentService.getAllCommentsOfRecipe(id).stream().forEach(comment -> commentService.deleteCommentById(comment.getId()));
    kitchenCategoriesLinkService.getKCLinkByRecipeId(id).stream().forEach(kitchenCategoriesLink -> kitchenCategoriesLinkService.deleteById(kitchenCategoriesLink.getId()));
    preparationService.getAllPreparationsRecipe(id).stream().forEach(preparation -> preparationService.deleteById(preparation.getId()));
    ratingService.getAllRatingsRecipe(id).stream().forEach(rating -> ratingService.deleteById(rating.getId()));
    Image image = recipeService.getRecipeById(id).getImage();
    imageService.deleteImage(image.getId());
    recipeService.deleteById(id);
    return ResponseEntity.status(HttpStatus.OK).body("Recipe is deleted");

  }

  public int calculateCurrentPrice(List<IngredientDto> ingredients) throws NullPointerException{

    return ingredients.stream().map(ingredientDto -> {

        double amount = amountInDefaultValue(ingredientDto);

        int articlePrice = ingredientDto.getArticleunit().getArticle().getPrice();
        int articleAmount = ingredientDto.getArticleunit().getArticle().getAmount();
        return ((int) (amount * articlePrice))/ articleAmount;})
            .reduce(0, (a, b) -> a+b);
  }

  public int calculateCalories(List<IngredientDto> ingredients) throws NullPointerException {

    return ingredients.stream().map(ingredientDto -> {
              double amount = amountInDefaultValue(ingredientDto);
              int articleCalories = ingredientDto.getArticleunit().getArticle().getCalories();
              int articleAmount = ingredientDto.getArticleunit().getArticle().getAmount();
              return ((int) (amount * articleCalories))/ articleAmount;})
            .reduce(0, (a, b) -> a+b);
  }

  private double amountInDefaultValue(IngredientDto ingredientDto) {
    double amount = ingredientDto.getAmount();
    amount = amount * ingredientDto.getArticleunit().getAmount();
    return amount;
  }

  private RecipeDto fillRecipeDto(Recipe recipe){
    RecipeDto recipeDto = convertRecipeToDto(recipe);
    fillRecipeDtoComments(recipeDto);
    fillRecipeDtoIngredients(recipeDto);
    fillRecipeDtoPreparations(recipeDto);
    fillRecipeDtoKitchenCategories(recipeDto);
    fillRecipeDtoAvgRating(recipeDto);

    return recipeDto;
  }

  private void fillRecipeDtoAvgRating(RecipeDto recipeDto) {
    recipeDto.setAvgRating(ratingService.getAvgRatingOfRecipe(recipeDto.getId()));
  }

  private void fillRecipeDtoPreparations(RecipeDto recipeDto) {
    List<Preparation> preparationsSteps = preparationService.getAllPreparationsRecipe(recipeDto.getId());
    List<PreparationDto> preparationsStepsDto = preparationsSteps.stream().map(this::convertPreparationToDto).collect(Collectors.toList());
    recipeDto.setPreparation(preparationsStepsDto);
  }

  private void fillRecipeDtoKitchenCategories(RecipeDto recipeDto) {
    List<KitchenCategoryDto> kitchenCategories = new ArrayList<>();
    List<KitchenCategoriesLink> kitchenCategoriesLinks = kitchenCategoriesLinkService.getKCLinkByRecipeId(recipeDto.getId());
    kitchenCategoriesLinks.forEach((link) -> {
      kitchenCategories.add(convertCategoryToDto(link.getKitchenCategory()));
    });
    recipeDto.setCategories(kitchenCategories);
  }

  private void fillRecipeDtoIngredients(RecipeDto recipeDto){
    List<Ingredient> ingredients = ingredientService.getIngredientsRecipeId(recipeDto.getId());
    List<IngredientDto> ingredientDtos = ingredients.stream().map(this::convertIngredientToDto).collect(Collectors.toList());
    recipeDto.setIngredients(ingredientDtos);
    try {
      recipeDto.setPrice(calculateCurrentPrice(ingredientDtos));
    } catch (NullPointerException ignored){}
    try {
      recipeDto.setCalories(calculateCalories(ingredientDtos));
    } catch (NullPointerException ignored){}}

  private void fillRecipeDtoComments(RecipeDto recipeDto) {
    List<Comment> comments = commentService.getAllCommentsOfRecipe(recipeDto.getId());
    List<CommentDto> commentsDto= comments.stream().map(this::convertCommentToDto).collect(Collectors.toList());
    recipeDto.setComments(commentsDto);
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

  public PreparationDto convertPreparationToDto(Preparation preparation){
    return modelMapper.map(preparation,PreparationDto.class);
  }

  public KitchenCategoryDto convertCategoryToDto(KitchenCategory kitchenCategory){
    return modelMapper.map(kitchenCategory,KitchenCategoryDto.class);
  }

  public Recipe revertCreateRecipeDto(RecipeCreateDto recipeCreateDto){
    return modelMapper.map(recipeCreateDto,Recipe.class);
  }

  public Ingredient revertIngredientInRecipeDto(IngredientInRecipeDto inRecipeDto,int recipeID){
    Ingredient ingredient = modelMapper.map(inRecipeDto,Ingredient.class);
    ingredient.getRecipe().setTimeAdded(null);
    ingredient.getRecipe().setId(recipeID);
    ingredient.setId(0);
    return ingredient;
  }

  public Preparation revertPreparationInRecipeDto(PreparationInRecipeDto preparationInRecipeDto, int recipeID){
    Preparation preparation = modelMapper.map(preparationInRecipeDto,Preparation.class);
    preparation.setRecipe(new Recipe());
    preparation.getRecipe().setTimeAdded(null);
    preparation.getRecipe().setId(recipeID);
    preparation.setId(0);
    return preparation;
  }

  public KitchenCategoriesLink revertCategoryLinkInRecipeDto(CategoryLinkInRecipeDto linkDto,int recipeId){
    KitchenCategoriesLink link = modelMapper.map(linkDto,KitchenCategoriesLink.class);
    link.setId(0);
    link.setRecipe(new Recipe());
    link.getRecipe().setTimeAdded(null);
    link.getRecipe().setId(recipeId);
    return link;
  }

  public void setModelMapper(ModelMapper modelMapper) {
    this.modelMapper = modelMapper;
  }
}
