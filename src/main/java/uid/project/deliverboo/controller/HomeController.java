package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private Button accessButton;

    @FXML
    private TextField addressField;

    @FXML
    private RadioMenuItem darkButton;

    @FXML
    private RadioMenuItem englishButton;

    @FXML
    private Button homeButton;

    @FXML
    private RadioMenuItem italianButton;

    @FXML
    private RadioMenuItem lightButton;

    @FXML
    private MenuButton menu;

    @FXML
    private MenuItem menuFAQ;

    @FXML
    private Menu menuLanguage;

    @FXML
    private Menu menuTheme;

    @FXML
    private Button searchButton;

    private  LocalizationManager localizationManager;

    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }

    @FXML
    private void handleItalian(){
        localizationManager.setLanguage(Locale.ITALIAN);
        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }

    @FXML
    private  void handleEnglish(){
        localizationManager.setLanguage(Locale.ENGLISH);
        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }



    private void updateTexts(String resourceName){
        accessButton.setText(localizationManager.getLocalizedString("button.accessButton"));
        addressField.setPromptText("textfield.addressField");
        darkButton.setText("radiomenuitem.dark");
        lightButton.setText("radiomenuitem.light");
        englishButton.setText("radiomenuitem.english");
        italianButton.setText("radiomenuitem.italian");
        menuFAQ.setText("menuitem.faq");
        menuLanguage.setText("menu.menuLanguage");
        menu.setText("menubutton.menu");
        menuTheme.setText("menu.menuTheme");
        searchButton.setText("button.searchButton");

    }

}
