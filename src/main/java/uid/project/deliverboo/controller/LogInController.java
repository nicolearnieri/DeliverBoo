package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.QueryUsers;
import uid.project.deliverboo.model.ValidatorUtility;
import uid.project.deliverboo.view.SceneHandler;

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

    @FXML
    private Button signUpButton;

    private  LocalizationManager localizationManager;


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }


    public  void openSignUp() throws Exception {
        SceneHandler.getInstance().setSignUp();
    }

    private void logInError (String message){
        // Mostra un messaggio di errore all'utente
        SceneHandler.getInstance().showError(message, localizationManager.getLocalizedString("title.error"));
    }

    private void logSuccess() {
        SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("content.logIn"),localizationManager.getLocalizedString("title.logIn"));
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
        signUpButton.setText(localizationManager.getLocalizedString("button.signUp"));
    }

}
