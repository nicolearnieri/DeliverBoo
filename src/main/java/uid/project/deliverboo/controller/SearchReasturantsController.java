package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Locale;

public class SearchReasturantsController {

    @FXML
    private Button bars;

    @FXML
    private Button breadType;

    @FXML
    private Button cafes;

    @FXML
    private Button cart;

    @FXML
    private ListView<?> centerListView;

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
    private Menu menuLanguage;

    @FXML
    private Menu menuTheme;

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


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }

    @FXML
    private void handleItalian(){
        localizationManager.setLanguage(Locale.ITALIAN);
        updateTexts();
    }

    @FXML
    private  void handleEnglish(){
        localizationManager.setLanguage(Locale.ENGLISH);
        updateTexts();
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
        menuLanguage.setText(localizationManager.getLocalizedString("menu.menuLanguage"));
        menuTheme.setText(localizationManager.getLocalizedString("menu.menuTheme"));
        englishButton.setText(localizationManager.getLocalizedString("radiomenuitem.english"));
        italianButton.setText(localizationManager.getLocalizedString("radiomenuitem.italian"));
        darkButton.setText(localizationManager.getLocalizedString("radiomenuitem.dark"));
        lightButton.setText(localizationManager.getLocalizedString("radiomenuitem.light"));
        light2Button.setText(localizationManager.getLocalizedString("radionemuitem.light2"));
        deliverBooThemeButton.setText(localizationManager.getLocalizedString("radiomenuitem.deliverbooTheme"));
        minimalistButton.setText(localizationManager.getLocalizedString("radiomenuitem.minimalistTheme"));



    }

}
