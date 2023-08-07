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
    @Autowired
    private ArticleController articleController;

    private final PasswordEncoder passwordEncoder;

    private final ArticleUnitRepository articleUnitRepository;
    @PostMapping()
    public String createTestData(){

        List<User> users = createUsers();
        createUnits();
        createArticles();
        List<ArticleUnit> articleUnits = articleUnitService.getAll();
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

        String[] unitNames = {"kg","g","mg","l","ml","cl","mespunt","snufje","theelepels","stuks","vellen",
        "eetlepels","kopjes","borrelgas","wijnglas"};
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
        addCalendarItem(LocalDateTime.parse("2023-09-25 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-01 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-08 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");
        addCalendarItem(LocalDateTime.parse("2023-08-15 14:00",formatter),"Vegetarisch koken","Een online workshop vegetarisch koken, onder leiding van Trientje Hupsakee");

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
        recipe.setKitchenType(kitchenTypes.get(3));
        recipe.setKitchenRegion(kitchenRegions.get(15));
        recipe.setPersons(4);
        recipe.setPrepTime(15);
        recipe.setUser(users.get(1));
        recipes.add(recipeService.saveRecipe(recipe));

        addIngredientToRecipe(recipes.get(0),articleUnits.get(0),4);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(1),300);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(2),50);
        addIngredientToRecipe(recipes.get(0),articleUnits.get(4),1);

        addCategoryToRecipe(recipes.get(0), 0,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 9,kitchenCategories);
        addCategoryToRecipe(recipes.get(0), 8,kitchenCategories);

        addPreparationToRecipe(recipes.get(0),"Burger bakken",1);
        addPreparationToRecipe(recipes.get(0),"Brood",2);
        addPreparationToRecipe(recipes.get(0),"SAus suaucua c jfjen kjsbvkjvjej sk sv skvj bkjcb ksb ksv  svbkej jv dfghjk dfghjk dfghjk sdfghj dfghj dfghj dfghj dfgh.",3);

        Recipe recipe2 = new Recipe();
        recipe2.setTitle("SushiRolls");
        recipe2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath2 = "src\\main\\resources\\images\\SushiRolls.jpg";
        Image image2 = uploadImageByPath(imagePath2,"SushiRolls.jpg");
        recipe2.setImage(image2);
        recipe2.setSlug("SushiRolls");
        recipe2.setKitchenType(kitchenTypes.get(1));
        recipe2.setKitchenRegion(kitchenRegions.get(12));
        recipe2.setPersons(4);
        recipe2.setPrepTime(20);
        recipe2.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe2));

        addIngredientToRecipe(recipes.get(1),articleUnits.get(7),0.4);
        addIngredientToRecipe(recipes.get(1),articleUnits.get(8),8);
        addIngredientToRecipe(recipes.get(1),articleUnits.get(10),200);

        addCategoryToRecipe(recipes.get(1), 0,kitchenCategories);
        addCategoryToRecipe(recipes.get(1), 4,kitchenCategories);
        addCategoryToRecipe(recipes.get(1), 9,kitchenCategories);
        addCategoryToRecipe(recipes.get(1), 16,kitchenCategories);


        Recipe recipe3 = new Recipe();
        recipe3.setTitle("SushiRolls");
        recipe3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath3 = "src\\main\\resources\\images\\SushiRolls2.jpg";
        Image image3 = uploadImageByPath(imagePath3,"SushiRolls2.jpg");
        recipe3.setImage(image3);
        recipe3.setSlug("SushiRolls-2");
        recipe3.setKitchenType(kitchenTypes.get(1));
        recipe3.setKitchenRegion(kitchenRegions.get(12));
        recipe3.setPersons(4);
        recipe3.setPrepTime(20);
        recipe3.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe3));

        addCategoryToRecipe(recipes.get(2), 0,kitchenCategories);
        addCategoryToRecipe(recipes.get(2), 4,kitchenCategories);
        addCategoryToRecipe(recipes.get(2), 9,kitchenCategories);
        addCategoryToRecipe(recipes.get(2), 16,kitchenCategories);

        Recipe recipe4 = new Recipe();
        recipe4.setTitle("Burritos");
        recipe4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath4 = "src\\main\\resources\\images\\Burrito.JPG";
        Image image4 = uploadImageByPath(imagePath4,"Burrito.JPG");
        recipe4.setImage(image4);
        recipe4.setSlug("Burritos");
        recipe4.setKitchenType(kitchenTypes.get(0));
        recipe4.setKitchenRegion(kitchenRegions.get(14));
        recipe4.setPersons(4);
        recipe4.setPrepTime(30);
        recipe4.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe4));
        addCategoryToRecipe(recipes.get(3), 3,kitchenCategories);

        Recipe recipe5 = new Recipe();
        recipe5.setTitle("Tomatensoep");
        recipe5.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath5 = "src\\main\\resources\\images\\tomaatsoup.jpg";
        Image image5 = uploadImageByPath(imagePath5,"tomaatsoup.jpg");
        recipe5.setImage(image5);
        recipe5.setSlug("Tomatensoep");
        recipe5.setKitchenType(kitchenTypes.get(2));
        recipe5.setKitchenRegion(kitchenRegions.get(6));
        recipe5.setPersons(4);
        recipe5.setPrepTime(30);
        recipe5.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe5));
        addCategoryToRecipe(recipes.get(4), 0,kitchenCategories);

        Recipe recipe6 = new Recipe();
        recipe6.setTitle("Lasagna");
        recipe6.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath6 = "src\\main\\resources\\images\\Lasagna.jpg";
        Image image6 = uploadImageByPath(imagePath6,"Lasagna.jpg");
        recipe6.setImage(image6);
        recipe6.setSlug("Lasagna");
        recipe6.setKitchenType(kitchenTypes.get(0));
        recipe6.setKitchenRegion(kitchenRegions.get(11));
        recipe6.setPersons(8);
        recipe6.setPrepTime(70);
        recipe6.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe6));
        addCategoryToRecipe(recipes.get(5), 3,kitchenCategories);
        addCategoryToRecipe(recipes.get(5), 20,kitchenCategories);
        addCategoryToRecipe(recipes.get(5), 12,kitchenCategories);

        Recipe recipe7 = new Recipe();
        recipe7.setTitle("Caesar Salade");
        recipe7.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit. Fusce porttitor feugiat lectus, sit amet gravida elit egestas ac.\n\n Sed convallis sapien quis justo elementum consectetur. Maecenas tempus, turpis sed consectetur pellentesque, orci tortor consectetur nisl, sed posuere enim sem mattis diam. Sed leo magna, commodo et accumsan gravida, lobortis a diam. Curabitur dignissim finibus nunc in facilisis. Praesent at porta augue. Integer lacinia ipsum tellus, ut posuere risus consectetur in. Nullam ut elit nec eros rhoncus facilisis non a mauris.");
        String imagePath7 = "src\\main\\resources\\images\\Caesar-Salad.jpg";
        Image image7 = uploadImageByPath(imagePath7,"Caesar-Salad.jpg");
        recipe7.setImage(image7);
        recipe7.setSlug("Caesar-Salade");
        recipe7.setKitchenType(kitchenTypes.get(2));
        recipe7.setKitchenRegion(kitchenRegions.get(11));
        recipe7.setPersons(8);
        recipe7.setPrepTime(5);
        recipe7.setUser(users.get(0));
        recipes.add(recipeService.saveRecipe(recipe7));
        addCategoryToRecipe(recipes.get(6), 1,kitchenCategories);
        addCategoryToRecipe(recipes.get(6), 11,kitchenCategories);
        addCategoryToRecipe(recipes.get(6), 18,kitchenCategories);


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
        String imagePath1 = "src\\main\\resources\\images\\hamb.jpg";
        Image image = uploadImageByPath(imagePath1,"hamb.jpg");
        article1.setImage(image);
        article1.setCalories(250);
        article1.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article1.setPrice(120);
        article1.setAmount(1);
        article1.setAvailable(true);
        article1.setUser(user);
        article1 = articleService.saveArticle(article1);
        articles.add(article1);
        Unit defUnit1 = unitService.getByName("stuks");
        addArticleUnit(article1,defUnit1,1.0,defUnit1);


        Article article2 = new Article();
        article2.setName("Vegan Burger");
        String imagePath2 = "src\\main\\resources\\images\\VeganBurgerI.jpg";
        Image image2 = uploadImageByPath(imagePath2,"VeganBurgerI.jpg");
        article2.setImage(image2);
        article2.setCalories(469);
        article2.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article2.setPrice(540);
        article2.setAmount(400);
        article2.setAvailable(true);
        article2.setUser(user);
        article2 = articleService.saveArticle(article2);
        articles.add(article2);
        Unit defUnit2 = unitService.getByName("g");
        addArticleUnit(article2,defUnit2,1.0,defUnit2);


        Article article3 = new Article();
        article3.setName("Vegan Burger Sauce");
        String imagePath3 = "src\\main\\resources\\images\\VeganSauce.jpg";
        Image image3 = uploadImageByPath(imagePath3,"VeganSauce.jpg");
        article3.setImage(image3);
        article3.setCalories(150);
        article3.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article3.setPrice(520);
        article3.setAmount(50);
        article3.setAvailable(true);
        article3.setUser(user);
        article3 = articleService.saveArticle(article3);
        articles.add(article3);
        Unit defUnit3 = unitService.getByName("ml");
        addArticleUnit(article3,defUnit3,1.0,defUnit3);
        addArticleUnit(article3,unitService.getByName("theelepels"),5.0,defUnit3);

        Article article4 = new Article();
        article4.setName("Avocado");
        String imagePath4 = "src\\main\\resources\\images\\avocado.jpg";
        Image image4 = uploadImageByPath(imagePath4,"avocado.jpg");
        article4.setImage(image4);
        article4.setCalories(300);
        article4.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article4.setPrice(100);
        article4.setAmount(1);
        article4.setAvailable(false);
        article4.setUser(user);
        article4 = articleService.saveArticle(article4);
        articles.add(article4);
        Unit defUnit4 = unitService.getByName("stuks");
        addArticleUnit(article4,defUnit4,1.0,defUnit4);
        addArticleUnit(article4,unitService.getByName("g"),0.01,defUnit4);

        Article article5 = new Article();
        article5.setName("Sushi rijst");
        String imagePath5 = "src\\main\\resources\\images\\sushi_rijst.jpg";
        Image image5 = uploadImageByPath(imagePath5,"sushi_rijst.jpg");
        article5.setImage(image5);
        article5.setCalories(1500);
        article5.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article5.setPrice(370);
        article5.setAmount(500);
        article5.setAvailable(true);
        article5.setUser(user);
        article5 = articleService.saveArticle(article5);
        articles.add(article5);
        Unit defUnit5 = unitService.getByName("g");
        addArticleUnit(article5,defUnit5,1.0,defUnit5);
        addArticleUnit(article5,unitService.getByName("kg"),1000,defUnit5);

        Article article6 = new Article();
        article6.setName("Sushi zeewier");
        String imagePath6 = "src\\main\\resources\\images\\Nori.jpg";
        Image image6 = uploadImageByPath(imagePath6,"Nori.jpg");
        article6.setImage(image6);
        article6.setCalories(91);
        article6.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article6.setPrice(500);
        article6.setAmount(10);
        article6.setAvailable(true);
        article6.setUser(user);
        article6 = articleService.saveArticle(article6);
        articles.add(article6);
        Unit defUnit6 = unitService.getByName("vellen");
        addArticleUnit(article6,defUnit6,1.0,defUnit6);
        addArticleUnit(article6,unitService.getByName("g"),0.4,defUnit6);


        Article article7 = new Article();
        article7.setName("Zalmfilet");
        String imagePath7 = "src\\main\\resources\\images\\zalm.jpg";
        Image image7 = uploadImageByPath(imagePath7,"zalm.jpg");
        article7.setImage(image7);
        article7.setCalories(624);
        article7.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article7.setPrice(950);
        article7.setAmount(300);
        article7.setAvailable(false);
        article7.setUser(user);
        article7 = articleService.saveArticle(article7);
        articles.add(article7);
        Unit defUnit7 = unitService.getByName("g");
        addArticleUnit(article7,defUnit7,1.0,defUnit7);

        Article article8 = new Article();
        article8.setName("Rucola Sla");
        String imagePath8 = "src\\main\\resources\\images\\RucolaSla.jpg";
        Image image8 = uploadImageByPath(imagePath8,"RucolaSla.jpg");
        article8.setImage(image8);
        article8.setCalories(72);
        article8.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article8.setPrice(110);
        article8.setAmount(85);
        article8.setAvailable(true);
        article8.setUser(user);
        article8 = articleService.saveArticle(article8);
        articles.add(article8);
        Unit defUnit8 = unitService.getByName("g");
        addArticleUnit(article8,defUnit8,1.0,defUnit8);

        Article article9 = new Article();
        article9.setName("Tomatenpuree");
        String imagePath9 = "src\\main\\resources\\images\\tomatenpuree.jpg";
        Image image9 = uploadImageByPath(imagePath9,"tomatenpuree.jpg");
        article9.setImage(image9);
        article9.setCalories(72);
        article9.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article9.setPrice(80);
        article9.setAmount(140);
        article9.setAvailable(true);
        article9.setUser(user);
        article9 = articleService.saveArticle(article9);
        articles.add(article9);
        Unit defUnit9 = unitService.getByName("g");
        addArticleUnit(article9,defUnit9,1.0,defUnit9);
        addArticleUnit(article9,unitService.getByName("eetlepels"),12,defUnit9);

        Article article10 = new Article();
        article10.setName("Mozzarella balletjes");
        String imagePath10 = "src\\main\\resources\\images\\balletjes_mozzarella.jpg";
        Image image10 = uploadImageByPath(imagePath10,"balletjes_mozzarella.jpg");
        article10.setImage(image10);
        article10.setCalories(306);
        article10.setDescription("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eu metus sem. Sed lobortis tempor arcu. Nulla id nulla in nibh dictum feugiat. Donec sed accumsan est, at accumsan velit.");
        article10.setPrice(140);
        article10.setAmount(125);
        article10.setAvailable(true);
        article10.setUser(user);
        article10 = articleService.saveArticle(article10);
        articles.add(article10);
        Unit defUnit10 = unitService.getByName("g");
        addArticleUnit(article10,defUnit10,1.0,defUnit10);


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

    public void addIngredientToRecipe(Recipe recipe, ArticleUnit aU, double amount){
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
