package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.QueryUsers;
import uid.project.deliverboo.model.ValidatorUtility;

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

    private void logInError (String message){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(localizationManager.getLocalizedString("title.error"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.errorLogIn"));
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void logSuccess() {

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(localizationManager.getLocalizedString("title.logIn"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.logIn"));
        alert.setContentText(localizationManager.getLocalizedString("content.logIn"));
        alert.showAndWait();
    }

    @FXML
    public void logInUser () {
        String user = userEmailField.getText();
        String password = passwordFieldSU.getText();
        boolean email = ValidatorUtility.isValidEmail(user);
        boolean userExists = false;

        if (email) {
            if (QueryUsers.emailNotExists(user))
                logInError(localizationManager.getLocalizedString("error.email"));
            else userExists = true;
        } else {
            if (QueryUsers.usernameNotExists(user))
                logInError(localizationManager.getLocalizedString("error.user"));
            else userExists = true;
        }

        if (userExists)
        {
            boolean check = BCrypt.checkpw(QueryUsers.getPassword(user), password);
            if (check) {
                //facimm ancuna cos tu rimember de iuser
                CurrentUser cU = CurrentUser.getInstance();
                if (email) {
                    cU.setEmail(user);
                    cU.setNomeUtente(QueryUsers.getUsername(user));
                }
                else {
                    cU.setNomeUtente(user);
                    cU.setEmail(QueryUsers.getEmail(user));
                }
                logSuccess();
            }
            else logInError(localizationManager.getLocalizedString("error.password"));
        }

    }

    private void updateTexts(){
        accessButton.setText(localizationManager.getLocalizedString("button.accessButtonAc"));
        userOrEmailLabel.setText(localizationManager.getLocalizedString("label.labelUserOrEmail"));
        messageLabel.setText(localizationManager.getLocalizedString("label.messageLabelAc"));
        passwordLabel.setText(localizationManager.getLocalizedString("label.labelPassword"));
    }

}
