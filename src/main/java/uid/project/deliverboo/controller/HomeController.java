package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class HomeController {

    @FXML
    private MenuButton Menu;

    @FXML
    private Button accessButton;

    @FXML
    private TextField addressField;

    @FXML
    private MenuItem darkButton;

    @FXML
    private MenuItem englishButton;

    @FXML
    private Button homeButton;

    @FXML
    private MenuItem italianButton;

    @FXML
    private MenuItem lightButton;

    @FXML
    private MenuItem menuFAQ;

    @FXML
    private Button searchButton;

    private  LocalizationManager localizationManager;

    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }

    private void handleItalian(){
        localizationManager.setLanguage(Locale.ITALIAN);
        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }

    private  void handleEnglish(){
        localizationManager.setLanguage(Locale.ENGLISH);
        updateTexts("strings_"+localizationManager.getCurrentLocale().getLanguage());
    }



    private void updateTexts(String resourceName){


    }

}
