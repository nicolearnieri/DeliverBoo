package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Locale;

public class FAQController {

    @FXML
    private RadioMenuItem darkButton;

    @FXML
    private RadioMenuItem deliverbooButton;

    @FXML
    private Button homeButton;

    @FXML
    private RadioMenuItem light2Button;

    @FXML
    private RadioMenuItem lightButton;

    @FXML
    private MenuButton menuButton;

    @FXML
    private ToggleGroup languageGroup;

    @FXML
    private ToggleGroup themeGroup;

    @FXML
    private Menu menuLanguage;

    @FXML
    private RadioMenuItem englishButton;

    @FXML
    private RadioMenuItem italianButton;

    @FXML
    private RadioMenuItem minimalistButton;

    @FXML
    private Menu menuTheme;


    private  LocalizationManager localizationManager;


    public void openHome(ActionEvent event) throws Exception{
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

    public void updateTexts(){
            menuButton.setText(localizationManager.getLocalizedString("menubutton.menu"));
            menuLanguage.setText(localizationManager.getLocalizedString("menu.menuLanguage"));
            menuTheme.setText(localizationManager.getLocalizedString("menu.menuTheme"));
            darkButton.setText(localizationManager.getLocalizedString("radiomenuitem.dark"));
            lightButton.setText(localizationManager.getLocalizedString("radiomenuitem.light"));
            light2Button.setText(localizationManager.getLocalizedString("radionemuitem.light2"));
            deliverbooButton.setText(localizationManager.getLocalizedString("radiomenuitem.deliverbooTheme"));
            minimalistButton.setText(localizationManager.getLocalizedString("radiomenuitem.minimalistTheme"));
            englishButton.setText(localizationManager.getLocalizedString("radiomenuitem.english"));
            italianButton.setText(localizationManager.getLocalizedString("radiomenuitem.italian"));

        }

    public void setLightMode() {SceneHandler.getInstance().changeTheme("LightTheme");
        themeGroup.selectToggle(lightButton);
    }

    public void setParadiseTheme() {SceneHandler.getInstance().changeTheme("ParadiseTheme");
        themeGroup.selectToggle(light2Button);
    }

    public void setDeliverBooTheme() {SceneHandler.getInstance().changeTheme("DeliverBooTheme");
        themeGroup.selectToggle(deliverbooButton);
    }

    public void setDarkTheme() {SceneHandler.getInstance().changeTheme("DarkTheme");
        themeGroup.selectToggle(darkButton);
    }

    public void setObsidianTheme() {SceneHandler.getInstance().changeTheme("ObsidianTheme");
        themeGroup.selectToggle(minimalistButton);
    }

    }






