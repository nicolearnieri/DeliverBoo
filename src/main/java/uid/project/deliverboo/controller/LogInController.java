package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.Locale;

public class LogInController {


    @FXML
    private Label userOrEmailLabel;

    @FXML
    private Button accessButton;

    @FXML
    private ImageView logo;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordFieldSU;

    @FXML
    private Label passwordLabel;

    @FXML
    private TextField userEmailField;

    private  LocalizationManager localizationManager;


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }


    private void updateTexts(){
        accessButton.setText(localizationManager.getLocalizedString("button.accessButtonAc"));
        userOrEmailLabel.setText((localizationManager.getLocalizedString(("label.labelUserOrEmail"))));
        messageLabel.setText((localizationManager.getLocalizedString("label.messageLabelAc")));
        passwordLabel.setText(localizationManager.getLocalizedString("label.labelPassword"));
    }

}
