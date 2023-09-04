package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.RestaurantsList;
import uid.project.deliverboo.view.SceneHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;

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
    private Button fastFood;

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


    private LocalizationManager localizationManager;
    private RestaurantsList restaurantsList;

    private HomeController homeController= new HomeController(); //questo non ci deve essere

    private List<Integer> queryResults= new ArrayList<Integer>();



    public void loadRestaurantsList(){
        queryResults=homeController.getQueryResults(); //query results da home controller va messo qua
        restaurantsList=new RestaurantsList();
        restaurantsList.loadRestaurantsList(restaurantsListPane, queryResults);


    }

    @FXML
    void searchBar(ActionEvent event) {

    }

    @FXML
    void searchBread(ActionEvent event) {

    }

    @FXML
    void searchCoffee(ActionEvent event) {

    }

    @FXML
    void searchFastFood(ActionEvent event) {

    }

    @FXML
    void searchGourmet(ActionEvent event) {

    }

    @FXML
    void searchJapanese(ActionEvent event) {

    }

    @FXML
    void searchMexican(ActionEvent event) {

    }

    @FXML
    void searchPastries(ActionEvent event) {

    }

    @FXML
    void searchPoke(ActionEvent event) {

    }

    @FXML
    void searchPub(ActionEvent event) {

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
    void setFontDyslexia(ActionEvent event) {

    }

    @FXML
    void setFontMontserrat(ActionEvent event) {

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
        fastFood.setText(localizationManager.getLocalizedString("button.fastfood"));
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

    public void changeLabel(boolean profile){
        if(profile){userLogged.setText(localizationManager.getLocalizedString("button.profileButton"));}
        else{userLogged.setText(localizationManager.getLocalizedString("button.accessButton"));}
    }




}




