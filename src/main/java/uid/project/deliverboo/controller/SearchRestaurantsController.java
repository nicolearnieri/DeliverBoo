package uid.project.deliverboo.controller;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Button gelateria;

    @FXML
    private Button breadType;

    @FXML
    private Button cafes;

    @FXML
    private Button fullList;

    @FXML
    private AnchorPane restaurantsListPane;

    @FXML
    private Button pizzeria;

    @FXML
    private Button gourmet;

    @FXML
    private Button homeButton;

    @FXML
    private Button asian;

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

    private Stage stage;

    private Number newWidth;

    private Number newHeight;




    public void init(LocalizationManager localizationManager, Stage stage) throws Exception {
            this.stage=stage;
            this.localizationManager=localizationManager;
            updateTexts();

            Callable<List<Integer>> verifyCallable = QueryCreator.createReturnAddressCallable(AddressVerifier.getFormattedAddress());
            Future<List<Integer>> result = executor.submit(verifyCallable);//oggetto prodotto da un'operazione asincrona

            try {
                queryResults = result.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }


            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, queryResults, localizationManager);

        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            RestaurantsListController restaurantsListController = restaurantsList.getController();
            restaurantsListController.refershWidth((Double) newVal);

            this.newWidth=newVal;


        });

        stage.heightProperty().addListener((obs, oldVal, newVal) -> {
            RestaurantsListController restaurantsListController = restaurantsList.getController();
            restaurantsListController.refershHeight((Double) newVal);

            this.newHeight=newVal;


        });



        }







    @FXML
    void searchByName(Event event) throws ExecutionException, InterruptedException, IOException {
        String name = searchBar.getText();
        Callable<List<Integer>> sBN = QueryCreator.createSearchByName(name, queryResults );
        Future<List<Integer>> executeSBN = executor.submit(sBN);
        List<Integer> results = executeSBN.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);
        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);




    }

    @FXML
    void sendByKey(KeyEvent event) throws IOException, ExecutionException, InterruptedException {
        if (event.getCode() == KeyCode.ENTER)
        {
            searchByName(event);
        }
    }
    @FXML
    void searchGelateria(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Gelateria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

        restaurantsList = new RestaurantsList();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);
        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);




    }



    @FXML
    void searchBread(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Forno", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

        restaurantsList = new RestaurantsList();
        restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);
        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);



    }

    @FXML
    void searchCoffee(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Caffetteria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchPizza(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Pizzeria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);

    }

    @FXML
    void searchGourmet(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Ristorante Gourmet", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchAsian(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Ristorante Asiatico", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchMexican(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Ristorante Messicano", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results,localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchPastries(ActionEvent event) throws IOException, ExecutionException, InterruptedException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Pasticceria", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchPoke(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Poke", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void searchPub(ActionEvent event) throws ExecutionException, InterruptedException, IOException {
        Callable<List<Integer>> sBT = QueryCreator.createSearchByType("Paninoteca", queryResults );
        Future<List<Integer>> executeSBT = executor.submit(sBT);
        List<Integer> results = executeSBT.get();

            restaurantsList = new RestaurantsList();
            restaurantsList.loadRestaurantsList(restaurantsListPane, results, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);


    }

    @FXML
    void openFullList(ActionEvent event) throws IOException {
        restaurantsList = new RestaurantsList();
        restaurantsList.loadRestaurantsList(restaurantsListPane, queryResults, localizationManager);

        RestaurantsListController restaurantsListController = restaurantsList.getController();
        restaurantsListController.refershWidth((Double) newWidth);
        restaurantsListController.refershHeight((Double) newHeight);
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

    public void openLogInOrProfile() throws Exception{
        if(CurrentUser.getInstance().getAccess()){SceneHandler.getInstance().setProfile();;}
        else{SceneHandler.getInstance().setLogIn();;}
    }
    private void updateTexts(){
        gelateria.setText(localizationManager.getLocalizedString("button.gelateria"));
        breadType.setText(localizationManager.getLocalizedString("button.breadType"));
        cafes.setText(localizationManager.getLocalizedString("button.cafe"));
        fullList.setText(localizationManager.getLocalizedString("button.list"));
        pizzeria.setText(localizationManager.getLocalizedString("button.pizzeria"));
        gourmet.setText(localizationManager.getLocalizedString("button.gourmet"));
        asian.setText(localizationManager.getLocalizedString("button.asian"));
        mexican.setText(localizationManager.getLocalizedString("button.mexican"));
        patisserie.setText(localizationManager.getLocalizedString("button.patisserie"));
        poke.setText(localizationManager.getLocalizedString("button.poke"));
        pub.setText(localizationManager.getLocalizedString("button.pub"));
        searchBar.setPromptText(localizationManager.getLocalizedString("textfield.searchBar"));
        menu.setText(localizationManager.getLocalizedString("menubutton.menu"));
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
        userLogged.setText(localizationManager.getLocalizedString("button.userLogged"));


    }

    public void setLightMode() {
        SceneHandler.getInstance().changeTheme("LightTheme");
        themeGroup.selectToggle(lightButton);
    }

    public void setParadiseTheme() {SceneHandler.getInstance().changeTheme("ParadiseTheme");
        themeGroup.selectToggle(light2Button);
    }

    public void setDeliverBooTheme() {SceneHandler.getInstance().changeTheme("DeliverBooTheme");
        themeGroup.selectToggle(deliverBooThemeButton);
    }

    public void setDarkTheme() {SceneHandler.getInstance().changeTheme("DarkTheme");
        themeGroup.selectToggle(darkButton);
    }

    public void setObsidianTheme() {SceneHandler.getInstance().changeTheme("ObsidianTheme");
        themeGroup.selectToggle(minimalistButton);
    }




}




