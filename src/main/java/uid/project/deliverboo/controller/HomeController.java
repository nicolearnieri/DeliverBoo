package uid.project.deliverboo.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uid.project.deliverboo.model.AddressVerifier;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.view.SceneHandler;


import java.util.*;



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
    private Menu menuFont;

    @FXML
    private RadioMenuItem fontBase;

    @FXML
    private RadioMenuItem fontDyslexia;

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

    @FXML
    private ToggleGroup languageGroup;

    @FXML
    private ToggleGroup themeGroup;
    @FXML
    private ToggleGroup fontGroup;



    @FXML
    private Label punLine;




    private  LocalizationManager localizationManager;



    public void openLogInOrProfile() throws Exception{
        if(CurrentUser.getInstance().getAccess()){menuProfile();}
        else{openLogIn();}
    }

    public void openLogIn() throws Exception{
        SceneHandler.getInstance().setLogIn();

    }


    public void openFAQ() throws Exception {
        SceneHandler.getInstance().setFaq();
    }


    public void menuProfile() throws Exception {
        SceneHandler.getInstance().setProfile();
    }


    public void openSearchRestaurants(Event event) throws Exception{

            if (AddressVerifier.getInstance().userValidAddress(addressField.getText(),localizationManager)) {

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();


                // Chiudi la finestra corrente
                currentStage.close();
                SceneHandler.getInstance().setSearchingProgress();


                // Apri la schermata di ricerca dei ristoranti
                SceneHandler.getInstance().setSearchRestaurants();





            } else {
                SceneHandler.getInstance().showError(localizationManager.getLocalizedString("address.errorMessage"), localizationManager.getLocalizedString("address.errorTitle"));
            }

    }


    @FXML
    void sendKeyPress(KeyEvent event) throws Exception {
        if (event.getCode() == KeyCode.ENTER)
        {
                openSearchRestaurants(event);
        }
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



    private void updateTexts(){
        accessButton.setText(localizationManager.getLocalizedString("button.userLogged"));
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
        menuFont.setText(localizationManager.getLocalizedString("menu.menuFont"));
        fontBase.setText(localizationManager.getLocalizedString("radiomenuitem.fontBase"));
        fontDyslexia.setText(localizationManager.getLocalizedString("radiomenuitem.fontDislexya"));
        punLine.setText(localizationManager.getLocalizedString("punLine.title"));


    }



    public void setLightMode() {SceneHandler.getInstance().changeTheme("LightTheme");
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
    public void setFontDyslexia() {SceneHandler.getInstance().changeFont("FontDyslexic");
        fontGroup.selectToggle(fontDyslexia);
    }
    public void setFontMontserrat() {SceneHandler.getInstance().changeFont("FontMontserrat");
        fontGroup.selectToggle(fontBase);
    }


}
