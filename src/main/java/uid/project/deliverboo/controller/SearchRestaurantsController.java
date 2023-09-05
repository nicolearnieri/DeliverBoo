package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uid.project.deliverboo.model.*;
import uid.project.deliverboo.view.RestaurantsList;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class SearchRestaurantsController {

    @FXML
    private Button bars;

    @FXML
    private Button breadType;

    @FXML
    private Button cafes;

    @FXML
    private Button cart;

    @FXML
    private AnchorPane restaurantsListPane;

    @FXML
    private Button pizzeria;

    @FXML
    private Button gourmet;

    @FXML
    private Button homeButton;

    @FXML
    private Button japanese;

    @FXML
    private Button mexican;

    @FXML
    private Button patisserie;

    @FXML
    private Button poke;

    @FXML
    private Button pub;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private Button userLogged;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem FAQbutton;

    @FXML
    private Menu menuLanguage;

    @FXML
    private Menu menuTheme;


    @FXML
    private ToggleGroup languageGroup;

    @FXML
    private ToggleGroup themeGroup;

    @FXML
    private RadioMenuItem englishButton;

    @FXML
    private RadioMenuItem italianButton;

    @FXML
    private RadioMenuItem darkButton;

    @FXML
    private RadioMenuItem deliverBooThemeButton;

    @FXML
    private RadioMenuItem light2Button;

    @FXML
    private RadioMenuItem lightButton;

    @FXML
    private RadioMenuItem minimalistButton;
    @FXML
    private ToggleGroup fontGroup;
    @FXML
    private RadioMenuItem fontBase;
    @FXML
    private RadioMenuItem fontDyslexia;

    private LocalizationManager localizationManager;

    private RestaurantsList restaurantsList;

    private HomeController homeController= new HomeController(); //questo non ci deve essere

    private ExecutorService executor = ExecutorProvider.getExecutor();



    private List<Integer> queryResults= new ArrayList<Integer>();



    public void loadRestaurantsList() throws IOException {
        //Query
        System.out.println("Qua arrivo 1");
        Callable<List<Integer>> verifyCallable =  TaskCreator.ReturnAddressTask(AddressVerifier.getFormattedAddress());
        System.out.println("Qua arrivo 2");
        Future<List<Integer>> result = executor.submit(verifyCallable);//oggetto prodotto da un'operazione asincrona
        System.out.println("Qua arrivo 3");
        try {
            System.out.println("Qua arrivo 4");
            queryResults = result.get();
            System.out.println("Qua arrivo 5");
        } catch (InterruptedException e) {
            System.out.println("Qua arrivo 6");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("Qua arrivo 7");
            throw new RuntimeException(e);
        }

        System.out.println("Qua arrivo 8");

        restaurantsList=new RestaurantsList();
        System.out.println("Qua arrivo 9");
        restaurantsList.loadRestaurantsList(restaurantsListPane, queryResults);
        System.out.println("Qua arrivo 10");


    }

    @FXML
    void searchBar(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Bar", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchBread(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Forno", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchCoffee(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Caffetteria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchPizza(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Pizzeria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchGourmet(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Ristorante Gourmet", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchJapanese(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Ristorante Giapponese", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchMexican(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Ristorante Messicano", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchPastries(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Pasticceria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchPoke(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Poke", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void searchPub(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = TaskCreator.createSearchByType("Paninoteca", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results);
    }

    @FXML
    void openCart(ActionEvent event) {

    }


    @FXML
    public void openFAQ(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setFaq();
    }

    public void openHome(ActionEvent event) throws Exception{
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        currentStage.close();

        SceneHandler.getInstance().setHomeInterface();
    }


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }

    @FXML
    private void handleItalian(){
        localizationManager.setLanguage(Locale.ITALIAN);
        languageGroup.selectToggle(italianButton);
        updateTexts();
    }

    @FXML
    private  void handleEnglish(){
        localizationManager.setLanguage(Locale.ENGLISH);
        languageGroup.selectToggle(englishButton);
        updateTexts();
    }



    @FXML
    public void setFontDyslexia() {SceneHandler.getInstance().changeFont("FontDyslexic");
        fontGroup.selectToggle(fontDyslexia);
    }
    public void setFontMontserrat() {SceneHandler.getInstance().changeFont("FontMontserrat");
        fontGroup.selectToggle(fontBase);
    }

    @FXML
    void menuProfile(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setProfile();
    }
    public void openLogInOrProfile() throws Exception{
        if(CurrentUser.getInstance().getAccess()){SceneHandler.getInstance().setProfile();;}
        else{SceneHandler.getInstance().setLogIn();;}
    }
    private void updateTexts(){
        bars.setText(localizationManager.getLocalizedString("button.bar"));
        breadType.setText(localizationManager.getLocalizedString("button.breadType"));
        cafes.setText(localizationManager.getLocalizedString("button.cafe"));
        cart.setText(localizationManager.getLocalizedString("button.cart"));
        pizzeria.setText(localizationManager.getLocalizedString("button.pizzeria"));
        gourmet.setText(localizationManager.getLocalizedString("button.gourmet"));
        japanese.setText(localizationManager.getLocalizedString("button.japanese"));
        mexican.setText(localizationManager.getLocalizedString("button.mexican"));
        patisserie.setText(localizationManager.getLocalizedString("button.patisserie"));
        poke.setText(localizationManager.getLocalizedString("button.poke"));
        pub.setText(localizationManager.getLocalizedString("button.pub"));
        searchBar.setPromptText(localizationManager.getLocalizedString("textfield.searchBar"));
        menu.setText(localizationManager.getLocalizedString("menubutton.menu"));
        FAQbutton.setText(localizationManager.getLocalizedString("menuitem.faq"));
        menuLanguage.setText(localizationManager.getLocalizedString("menu.menuLanguage"));
        menuTheme.setText(localizationManager.getLocalizedString("menu.menuTheme"));
        englishButton.setText(localizationManager.getLocalizedString("radiomenuitem.english"));
        italianButton.setText(localizationManager.getLocalizedString("radiomenuitem.italian"));
        darkButton.setText(localizationManager.getLocalizedString("radiomenuitem.dark"));
        lightButton.setText(localizationManager.getLocalizedString("radiomenuitem.light"));
        light2Button.setText(localizationManager.getLocalizedString("radionemuitem.light2"));
        deliverBooThemeButton.setText(localizationManager.getLocalizedString("radiomenuitem.deliverbooTheme"));
        minimalistButton.setText(localizationManager.getLocalizedString("radiomenuitem.minimalistTheme"));
        searchButton.setText(localizationManager.getLocalizedString("button.searchButton"));

        //qua va messo l'if
        userLogged.setText(localizationManager.getLocalizedString("button.accessButton"));



    }

    public void setLightMode() {
        SceneHandler.getInstance().changeTheme("LightTheme");
        themeGroup.selectToggle(lightButton);
        RestaurantItemController.setLightMode();
    }

    public void setParadiseTheme() {SceneHandler.getInstance().changeTheme("ParadiseTheme");
        themeGroup.selectToggle(light2Button);
        RestaurantItemController.setParadiseTheme();
    }

    public void setDeliverBooTheme() {SceneHandler.getInstance().changeTheme("DeliverBooTheme");
        themeGroup.selectToggle(deliverBooThemeButton);
        RestaurantItemController.setDeliverBooTheme();
    }

    public void setDarkTheme() {SceneHandler.getInstance().changeTheme("DarkTheme");
        themeGroup.selectToggle(darkButton);
        RestaurantItemController.setDarkTheme();
    }

    public void setObsidianTheme() {SceneHandler.getInstance().changeTheme("ObsidianTheme");
        themeGroup.selectToggle(minimalistButton);
        RestaurantItemController.setObsidianTheme();
    }

    public void changeLabel(boolean profile){
        if(profile){userLogged.setText(localizationManager.getLocalizedString("button.profileButton"));}
        else{userLogged.setText(localizationManager.getLocalizedString("button.accessButton"));}
    }




}




