package com.lydia.vurrukkulluk.controller;


import com.lydia.vurrukkulluk.model.*;
import com.lydia.vurrukkulluk.repository.ArticleUnitRepository;
import com.lydia.vurrukkulluk.repository.ImageRepository;
import com.lydia.vurrukkulluk.repository.RatingRepository;
import com.lydia.vurrukkulluk.repository.UnitRepository;
import com.lydia.vurrukkulluk.service.*;
import com.lydia.vurrukkulluk.util.Role;
import com.lydia.vurrukkulluk.util.UserImageUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/testdata")
@CrossOrigin
@RequiredArgsConstructor
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
    private UnitService unitService;
    @Autowired
    private ArticleUnitService articleUnitService;

    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    private final ArticleUnitRepository articleUnitRepository;
    @PostMapping()
    public String createTestData(){

        List<User> users = createUsers();
        List<Unit> units = createUnits();
        List<Article> articles = createArticles();
        List<ArticleUnit> articleUnits = createArticleUnits(units,articles);
        List<KitchenRegion> kitchenRegions = createKitchenRegion();
        List<KitchenType> kitchenTypes = createKitchenType();
        List<KitchenCategory> kitchenCategories = createKitchenCategory();
        List<Recipe> recipes = createRecipe(users, articleUnits, kitchenRegions, kitchenTypes, kitchenCategories);
        createComments(users, recipes);
        createFavorites(users, recipes);
        createRatings(users, recipes);
        createCalendar();

        return "testdata made";
    }

    private List<ArticleUnit> createArticleUnits(List<Unit> units, List<Article> articles) {

        List<ArticleUnit> articleUnits = new ArrayList<>();
        articleUnits.add(addArticleUnit(articles.get(0),units.get(1),1.0,units.get(1)));
        articleUnits.add(addArticleUnit(articles.get(0),units.get(0),1000.0,units.get(1)));
        articleUnits.add(addArticleUnit(articles.get(0),units.get(2),0.001,units.get(1)));
        articleUnits.add(addArticleUnit(articles.get(0),units.get(6),0.4,units.get(1)));
        articleUnits.add(addArticleUnit(articles.get(1),units.get(4),1000,units.get(4)));
        articleUnits.add(addArticleUnit(articles.get(1),units.get(3),1,units.get(4)));
        articleUnits.add(addArticleUnit(articles.get(1),units.get(5),10,units.get(4)));
        articleUnits.add(addArticleUnit(articles.get(2),units.get(4),1,units.get(4)));
        articleUnits.add(addArticleUnit(articles.get(3),units.get(4),1,units.get(4)));

        return articleUnits;
    }

    private ArticleUnit addArticleUnit(Article article, Unit unit, double i, Unit defUnit) {
        ArticleUnit articleUnit= new ArticleUnit();
        articleUnit.setArticle(article);
        articleUnit.setUnit(unit);
        articleUnit.setAmount(i);
        articleUnit.setDefUnit(defUnit);
        articleUnitService.save(articleUnit);
        return articleUnit;
    }

    private List<Unit> createUnits() {

        String[] unitNames = {"kg","g","mg","l","ml","cl","mespunt","snufje","theelepel","stuks"};
        List<Unit> units = new ArrayList<>();

        for (String name:unitNames) {
            units.add(addUnit(name));
        }
        return units;
    }

    private Unit addUnit(String unitName) {
        Unit unit = new Unit();
        unit.setName(unitName);
        unitService.save(unit);
        return unit;
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
        addComment(recipes.get(1),"Jammie!", users.get(0));
        addComment(recipes.get(1),"Duurt veel te lang om te maken", users.get(1));
        addComment(recipes.get(1),"Valt wel mee, toch?", users.get(0));
        addComment(recipes.get(1),"Ik heb 3 uur in de keuken gestaan!", users.get(1));
    }

    private List<Recipe> createRecipe(List<User> users, List<ArticleUnit> articleUnits, List<KitchenRegion> kitchenRegions, List<KitchenType> kitchenTypes, List<KitchenCategory> kitchenCategories) {

        List<Recipe> recipes = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setTitle("Vegan Burger");
        recipe.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath = "src\\main\\resources\\images\\VeganBurger.jpg";
        Image image = uploadImageByPath(imagePath,"VeganBurger.jpg");
        recipe.setImage(image);
        recipe.setSlug("vegan-burger");
        recipe.setKitchenType(kitchenTypes.get(2));
        recipe.setKitchenRegion(kitchenRegions.get(14));
        recipe.setPersons(4);
        recipe.setUser(users.get(1));
        recipes.add(recipeService.saveRecipe(recipe));

        addIngredientToRecipe(recipes.get(0),articleUnits.get(0),7);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(6),320);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(7),13);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(8),304);

        addCategoryToRecipe(recipes.get(0), 0,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 7,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 14,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 17,kitchenCategories);

        addPreparationToRecipe(recipes.get(0),"Burger verbranden",1);
        addPreparationToRecipe(recipes.get(0),"Brood",2);
        addPreparationToRecipe(recipes.get(0),"SAus suaucua c jfjen kjsbvkjvjej sk sv skvj bkjcb ksb ksv  svbkej jv dfghjk dfghjk dfghjk sdfghj dfghj dfghj dfghj dfgh.",3);

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("Vegan Burger N2");
        recipe2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath2 = "src\\main\\resources\\images\\VeganBurger.jpg";
        Image image2 = uploadImageByPath(imagePath,"VeganBurger.jpg");
        recipe2.setImage(image2);
        recipe2.setSlug("vegan-burger-2");
        recipe2.setKitchenType(kitchenTypes.get(2));
        recipe2.setKitchenRegion(kitchenRegions.get(12));
        recipe2.setPersons(6);
        recipe2.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe2));

        addIngredientToRecipe(recipes.get(1),articleUnits.get(0),5);
        addIngredientToRecipe(recipes.get(1),articleUnits.get(1),220);
        addIngredientToRecipe(recipes.get(1),articleUnits.get(2),10);
        addIngredientToRecipe(recipes.get(1),articleUnits.get(3),30);

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

        User user = new User();
        user.setId(1);

        Article article1 = new Article();
        article1.setName("Vegan Burger Bun");
        String imagePath = "src\\main\\resources\\images\\hamb.jpg";
        Image image = uploadImageByPath(imagePath,"hamb.jpg");
        article1.setImage(image);
        article1.setCalories(250);
        article1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article1.setPrice(120);
        article1.setAmount(1);
        article1.setAvailable(true);
        article1.setUser(user);
        articles.add(articleService.saveArticle(article1));

        Article article2 = new Article();
        article2.setName("Vegan Burger");
        String imagePath2 = "src\\main\\resources\\images\\VeganBurgerI.jpg";
        Image image2 = uploadImageByPath(imagePath,"VeganBurgerI.jpg");
        article2.setImage(image2);
        article2.setCalories(469);
        article2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article2.setPrice(540);
        article2.setAmount(400);
        article2.setAvailable(true);
        article2.setUser(user);
        articles.add(articleService.saveArticle(article2));

        Article article3 = new Article();
        article3.setName("Vegan Burger Sauce");
        String imagePath3 = "src\\main\\resources\\images\\VeganSauce.jpg";
        Image image3 = uploadImageByPath(imagePath,"VeganSauce.jpg");
        article3.setImage(image3);
        article3.setCalories(750);
        article3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article3.setPrice(520);
        article3.setAmount(250);
        article3.setAvailable(true);
        article3.setUser(user);
        articles.add(articleService.saveArticle(article3));

        Article article4 = new Article();
        article4.setName("Adocado");
        String imagePath4 = "src\\main\\resources\\images\\VeganBurgerI.jpg";
        Image image4 = uploadImageByPath(imagePath,"VeganBurgerI.jpg");
        article4.setImage(image4);
        article4.setCalories(300);
        article4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article4.setPrice(200);
        article4.setAmount(1);
        article4.setAvailable(false);
        article4.setUser(user);
        articles.add(articleService.saveArticle(article4));

        return articles;
    }
    private List<User> createUsers() {

        String imagePath = "src\\main\\resources\\images\\IMG_4420.JPG";
        Image image = uploadImageByPath(imagePath,"IMG_4420.JPG");

        List<User> users = new ArrayList<>();

        User user1 = new User();
        user1.setEmail("test@test.com");
        user1.setName("Tess Ter");
        user1.setImage(image);
        user1.setPassword(passwordEncoder.encode("test123"));
        user1.setRole(Role.ADMIN);
        userService.saveUser(user1);
        users.add(user1);

        User user2 = new User();
        user2.setEmail("tt@tt.com");
        user2.setName("To van Test");
        user2.setImage(image);
        user2.setPassword(passwordEncoder.encode("test1234"));
        user2.setRole(Role.USER);
        userService.saveUser(user2);
        users.add(user2);

        return users;

    }

    public Image uploadImageByPath(String imagePath, String name){
        Image image = new Image();
        try {
            image.setType("file");
            image.setName(name);
            image.setImageData(UserImageUtil.compressImage(Files.readAllBytes(Paths.get(imagePath))));
            image = imageRepository.save(image);
        } catch ( java.io.IOException e) {
            System.out.println(e);
            return new Image();
        }
        return image;
    }

    public void addIngredientToRecipe(Recipe recipe, ArticleUnit aU, int amount){
        Ingredient ingredient = new Ingredient();
        ingredient.setRecipe(recipe);
        ingredient.setArticleunit(aU);
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

   public void addCalendarItem(LocalDateTime dateTime, String title, String description ){
        CalendarItem calendarItem = new CalendarItem();
        calendarItem.setDate(dateTime);
        calendarItem.setDescription(description);
        calendarItem.setTitle(title);
        calendarItemService.save(calendarItem);
   }
}
