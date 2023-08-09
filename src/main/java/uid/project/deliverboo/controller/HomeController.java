package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import uid.project.deliverboo.view.SceneHandler;
//import uid.project.deliverboo.view.SceneHandler;

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
    private RadioMenuItem deliverBooThemeButton;

    @FXML
    private RadioMenuItem light2Button;

    @FXML
    private RadioMenuItem lightButton;

    @FXML
    private RadioMenuItem minimalistButton;

    @FXML
    private RadioMenuItem englishButton;

    @FXML
    private Button homeButton;

    @FXML
    private RadioMenuItem italianButton;



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
        accessButton.setText(localizationManager.getLocalizedString("button.accessButton"));
        addressField.setPromptText(localizationManager.getLocalizedString("textfield.addressField"));
        darkButton.setText(localizationManager.getLocalizedString("radiomenuitem.dark"));
        lightButton.setText(localizationManager.getLocalizedString("radiomenuitem.light"));
        light2Button.setText(localizationManager.getLocalizedString("radionemuitem.light2"));
        deliverBooThemeButton.setText(localizationManager.getLocalizedString("radiomenuitem.deliverbooTheme"));
        minimalistButton.setText(localizationManager.getLocalizedString("radiomenuitem.minimalistTheme"));
        englishButton.setText(localizationManager.getLocalizedString("radiomenuitem.english"));
        italianButton.setText(localizationManager.getLocalizedString("radiomenuitem.italian"));
        menuFAQ.setText(localizationManager.getLocalizedString("menuitem.faq"));
        menuLanguage.setText(localizationManager.getLocalizedString("menu.menuLanguage"));
        menu.setText(localizationManager.getLocalizedString("menubutton.menu"));
        menuTheme.setText(localizationManager.getLocalizedString("menu.menuTheme"));
        searchButton.setText(localizationManager.getLocalizedString("button.searchButton"));


    }
    public void setLightMode(){
        SceneHandler.getInstance().changeTheme("LightTheme");}
    public void setDarkTheme(){SceneHandler.getInstance().changeTheme("DarkTheme");}
    public void setParadiseTheme(){SceneHandler.getInstance().changeTheme("ParadiseTheme");}
    public void setDeliverBooTheme(){SceneHandler.getInstance().changeTheme("DeliverBooTheme");}

    public void setObsidianTheme(){SceneHandler.getInstance().changeTheme("ObsidianTheme");}

}
