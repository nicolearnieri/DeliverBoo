package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Locale;

public class FAQController {

    @FXML
    private TextArea textArea;

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
        updateFAQTexts();
    }

    @FXML
    private void handleItalian(){
        localizationManager.setLanguage(Locale.ITALIAN);
        languageGroup.selectToggle(italianButton);
        updateTexts();
        updateFAQTexts();
    }

    @FXML
    private  void handleEnglish(){
        localizationManager.setLanguage(Locale.ENGLISH);
        languageGroup.selectToggle(englishButton);
        updateTexts();
        updateFAQTexts();
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

    public void updateFAQTexts(){
        String q1=localizationManager.getLocalizedString("faq.q1");
        String a1=localizationManager.getLocalizedString("faq.a1");
        String q2=localizationManager.getLocalizedString("faq.q2");
        String a2=localizationManager.getLocalizedString("faq.a2");
        String q3=localizationManager.getLocalizedString("faq.q3");
        String a3=localizationManager.getLocalizedString("faq.a3");
        String q4=localizationManager.getLocalizedString("faq.q4");
        String a4=localizationManager.getLocalizedString("faq.a4");
        String q5=localizationManager.getLocalizedString("faq.q5");
        String a5=localizationManager.getLocalizedString("faq.a5");
        String q6=localizationManager.getLocalizedString("faq.q6");
        String a6=localizationManager.getLocalizedString("faq.a6");
        String q7=localizationManager.getLocalizedString("faq.q7");
        String a7=localizationManager.getLocalizedString("faq.a7");
        String q8=localizationManager.getLocalizedString("faq.q8");
        String a8=localizationManager.getLocalizedString("faq.a8");
        String q9=localizationManager.getLocalizedString("faq.q9");
        String a9=localizationManager.getLocalizedString("faq.a9");

        String faqText=q1+"\n\n"+a1+"\n\n\n"+
                q2+"\n\n"+a2+"\n\n\n"+
                q3+"\n\n"+a3+"\n\n\n"+
                q4+"\n\n"+a4+"\n\n\n"+
                q5+"\n\n"+a5+"\n\n\n"+
                q6+"\n\n"+a6+"\n\n\n"+
                q7+"\n\n"+a7+"\n\n\n"+
                q8+"\n\n"+a8+"\n\n\n"+
                q9+"\n\n"+a9+"\n\n\n";

        textArea.setText(faqText);
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






