package com.lydia.vurrukkulluk.controller;


import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/testdata")
@CrossOrigin
public class TestDataController {

    @Autowired
    private CalendarItemService calendarItemService;
    @Autowired
    private ImageRepository imageRepository;
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
    private ModelMapper modelMapper;

    @PostMapping()
    public String createTestData(){

        List<User> users = createUsers();
        List<Article> articles = createArticles();
        List<KitchenRegion> kitchenRegions = createKitchenRegion();
        List<KitchenType> kitchenTypes = createKitchenType();
        List<KitchenCategory> kitchenCategories = createKitchenCategory();
        List<Recipe> recipes = createRecipe(users, articles, kitchenRegions, kitchenTypes, kitchenCategories);
        createComments(users, recipes);
        createFavorites(users, recipes);
        createRatings(users, recipes);
        createCalendar();


        return "testdata made";
    }

    private void createCalendar() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        addCalendarItem(LocalDateTime.parse("2023-06-07 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-06-14 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-06-21 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-06-28 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-02 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-09 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-16 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-25 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");

    }

    private void createRatings(List<User> users, List<Recipe> recipes) {
        addRating(recipes.get(0),users.get(0),3);
        addRating(recipes.get(0),users.get(1),2);
    }

    private void createFavorites(List<User> users, List<Recipe> recipes) {
        addFavorite(recipes.get(0),users.get(0));
    }

    private void createComments(List<User> users, List<Recipe> recipes) {

        addComment(recipes.get(0),"Lekker!!!!!", users.get(0));
        addComment(recipes.get(0),"bah!!!!!", users.get(1));
        addComment(recipes.get(0),"Lekker!!!!!", users.get(0));
        addComment(recipes.get(0),"Nou nee, niet echt ... :(", users.get(1));
    }

    private List<Recipe> createRecipe(List<User> users, List<Article> articles, List<KitchenRegion> kitchenRegions, List<KitchenType> kitchenTypes, List<KitchenCategory> kitchenCategories) {

        List<Recipe> recipes = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setTitle("Vegan Burger");
        recipe.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath = "src\\main\\resources\\images\\VeganBurger.jpg";
        Image image = uploadImageByPath(imagePath);
        recipe.setImage(image);
        recipe.setSlug("vegan-burger");
        recipe.setKitchenType(kitchenTypes.get(2));
        recipe.setKitchenRegion(kitchenRegions.get(14));
        recipe.setPersons(4);
        recipe.setUser(users.get(1));
        recipes.add(recipeService.saveRecipe(recipe));

        addIngredientToRecipe(recipes.get(0),0, articles,2);
        addIngredientToRecipe(recipes.get(0),1, articles,320);
        addIngredientToRecipe(recipes.get(0),2, articles,30);
        addIngredientToRecipe(recipes.get(0),3, articles,20);

        addCategoryToRecipe(recipes.get(0), 0,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 7,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 14,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 17,kitchenCategories);

        addPreparationToRecipe(recipes.get(0),"Burger verbranden",1);
        addPreparationToRecipe(recipes.get(0),"Brood",2);
        addPreparationToRecipe(recipes.get(0),"SAus suaucua c jfjen kjsbvkjvjej sk sv skvj bkjcb ksb ksv  svbkej jv dfghjk dfghjk dfghjk sdfghj dfghj dfghj dfghj dfgh.",3);

        return recipes;
    }

    private List<KitchenCategory> createKitchenCategory() {
        List<KitchenCategory> kitchenCategories = new ArrayList<>();

        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Biologisch");
        categoryNames.add("Glutenvrij");
        categoryNames.add("Lactosevrij");
        categoryNames.add("Bevat vlees");
        categoryNames.add("Bevat vis");
        categoryNames.add("Bevat schaaldieren");
        categoryNames.add("Bevat mosterd");
        categoryNames.add("Bevat ei");
        categoryNames.add("Vegan");
        categoryNames.add("Eenvoudig");
        categoryNames.add("Snel");
        categoryNames.add("Goedkoop");
        categoryNames.add("Keto");
        categoryNames.add("Diët");
        categoryNames.add("Koolhydraatarm");
        categoryNames.add("Feestelijk");
        categoryNames.add("Bakken");
        categoryNames.add("Koekjes");
        categoryNames.add("Salade");
        categoryNames.add("Ovenschotel");
        categoryNames.add("Wokgerecht");

        for (String name : categoryNames ) {
            KitchenCategory kitchenCategory = new KitchenCategory();
            kitchenCategory.setName(name);
            kitchenCategories.add(kitchenCategoryService.save(kitchenCategory));
        }

        return kitchenCategories;
    }

    private List<KitchenType> createKitchenType() {
        List<KitchenType> kitchenTypes = new ArrayList<>();

        List<String> typeNames = new ArrayList<>();
        typeNames.add("Vlees");
        typeNames.add("Vis");
        typeNames.add("Vegatarisch");
        typeNames.add("Vegan");

        for (String name : typeNames ) {
            KitchenType kitchenType = new KitchenType();
            kitchenType.setName(name);
            kitchenTypes.add(kitchenTypeService.saveKitchenType(kitchenType));
        }

        return kitchenTypes;
    }

    private List<KitchenRegion> createKitchenRegion() {

        List<KitchenRegion> kitchenRegions = new ArrayList<>();

        List<String> regionNames = new ArrayList<>();
        regionNames.add("Afrikaans");
        regionNames.add("Amerikaans");
        regionNames.add("Argentijns");
        regionNames.add("Australisch");
        regionNames.add("Aziatisch");
        regionNames.add("Belgisch");
        regionNames.add("Chinees");
        regionNames.add("Duits");
        regionNames.add("Engels");
        regionNames.add("Filipijns");
        regionNames.add("Frans");
        regionNames.add("Italiaans");
        regionNames.add("Japans");
        regionNames.add("Marokkaans");
        regionNames.add("Mexicaans");
        regionNames.add("Nederlands");
        regionNames.add("Pools");
        regionNames.add("Scandinavisch");
        regionNames.add("Spaans");
        regionNames.add("Thais");
        regionNames.add("Vietnamees");

        for (String name : regionNames ) {
            KitchenRegion kitchenRegion = new KitchenRegion();
            kitchenRegion.setName(name);
            kitchenRegions.add(kitchenRegionService.saveKitchenRegion(kitchenRegion));
        }

        return kitchenRegions;
    }

    private List<Article> createArticles() {
        List<Article> articles = new ArrayList<>();

        Article article1 = new Article();
        article1.setName("Vegan Burger Bun");
        String imagePath = "src\\main\\resources\\images\\hamb.jpg";
        Image image = uploadImageByPath(imagePath);
        article1.setImage(image);
        article1.setUnit("stuks");
        article1.setCalories(250);
        article1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article1.setPrice(120);
        article1.setAmount(1);
        articles.add(articleService.saveArticle(article1));

        Article article2 = new Article();
        article2.setName("Vegan Burger");
        String imagePath2 = "src\\main\\resources\\images\\VeganBurgerI.jpg";
        Image image2 = uploadImageByPath(imagePath);
        article2.setImage(image2);
        article2.setUnit("g");
        article2.setCalories(469);
        article2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article2.setPrice(540);
        article2.setAmount(400);
        articles.add(articleService.saveArticle(article2));

        Article article3 = new Article();
        article3.setName("Vegan Burger Sauce");
        String imagePath3 = "src\\main\\resources\\images\\VeganSauce.jpg";
        Image image3 = uploadImageByPath(imagePath);
        article3.setImage(image3);
        article3.setUnit("ml");
        article3.setCalories(750);
        article3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article3.setPrice(520);
        article3.setAmount(250);
        articles.add(articleService.saveArticle(article3));

        Article article4 = new Article();
        article4.setName("Adocado");
        String imagePath4 = "src\\main\\resources\\images\\VeganBurgerI.jpg";
        Image image4 = uploadImageByPath(imagePath);
        article4.setImage(image4);
        article4.setUnit("stuks");
        article4.setCalories(300);
        article4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article4.setPrice(200);
        article4.setAmount(1);
        articles.add(articleService.saveArticle(article4));

        return articles;
    }
    private List<User> createUsers() {

        String imagePath = "src\\main\\resources\\images\\IMG_4420.JPG";
        Image image = uploadImageByPath(imagePath);

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setName("Tess Ter");
        user1.setImage(image);
        user1.setPassword("test123");
        userService.saveUser(user1);
        users.add(user1);

        User user2 = new User();
        user2.setEmail("tt@tt.com");
        user2.setName("To van Test");
        user2.setImage(image);
        user2.setPassword("test1234");
        userService.saveUser(user2);
        users.add(user2);

        return users;

    }

    public Image uploadImageByPath(String imagePath){
        Image image = new Image();
        try {
            image.setType("file");
            image.setName("IMG_4420.JPG");
            image.setImageData(Files.readAllBytes(Paths.get(imagePath)));
            image = imageRepository.save(image);
        } catch ( java.io.IOException e) {
            System.out.println(e);
            return new Image();
        }
        return image;
    }

    public void addIngredientToRecipe(Recipe recipe, int articleId, List<Article> articles, int amount){
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.setArticle(articles.get(articleId));
        ingredient.setAmount(amount);
        ingredientService.saveIngredient(ingredient);
    }
    public void addCategoryToRecipe(Recipe recipe, int categoryId, List<KitchenCategory> kitchenCategories){

        KitchenCategoriesLink kitchenCategoriesLink = new KitchenCategoriesLink();
        kitchenCategoriesLink.setRecipe(recipe);
        kitchenCategoriesLink.setKitchenCategory(kitchenCategories.get(categoryId));
        kitchenCategoriesLinkService.saveKCLink(kitchenCategoriesLink);
    }

    public void addPreparationToRecipe(Recipe recipe, String instructions, int step){
        Preparation preparation = new Preparation();
        preparation.setRecipe(recipe);
        preparation.setInstructions(instructions);
        preparation.setStep(step);
        preparationService.savePreparation(preparation);
    }

    public void addComment(Recipe recipe, String text, User user){
        Comment comment = new Comment();
        comment.setCommentText(text);
        comment.setRecipe(recipe);
        comment.setUser(user);
        commentService.saveComment(comment);
    }

    public void addFavorite(Recipe recipe, User user){
        Favorite favorite = new Favorite();
        favorite.setRecipe(recipe);
        favorite.setUser(user);
        favoriteService.saveFavorite(favorite);
    }

   public void addRating(Recipe recipe, User user, int score){
        Rating rating = new Rating();
        rating.setUser(user);
        rating.setRecipe(recipe);
        rating.setRating(score);
        ratingService.saveRating(rating);
   }

   public void addCalendarItem(LocalDateTime dateTime, String description, String title){
        CalendarItem calendarItem = new CalendarItem();
        calendarItem.setDate(dateTime);
        calendarItem.setDescription(description);
        calendarItem.setTitle(title);
        calendarItemService.save(calendarItem);
   }
}