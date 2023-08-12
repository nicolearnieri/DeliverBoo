package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import org.mindrot.jbcrypt.BCrypt;
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

    private void signUpError (String message){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore");
        alert.setHeaderText("Errore nell'accesso");
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void logSuccess() {

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Accesso");
        alert.setHeaderText("Accesso completato");
        alert.setContentText("L'accesso al tuo account è stato effettuato con successo.");
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
                signUpError("Non esiste nessun utente associato a quest'indirizzo email.");
            else userExists = true;
        } else {
            if (QueryUsers.usernameNotExists(user))
                signUpError("Il nome utente da te inserito non è associato a nessun account esistente.");
            else userExists = true;
        }

        if (userExists)
        {
            boolean check = BCrypt.checkpw(QueryUsers.getPassword(user), password);
            if (check) {
                //facimm ancuna cos tu rimember de iuser
                logSuccess();
            }
            else signUpError("La password da te inserita potrebbe essere errata.");
        }

    }

    private void updateTexts(){
        accessButton.setText(localizationManager.getLocalizedString("button.accessButtonAc"));
        userOrEmailLabel.setText(localizationManager.getLocalizedString("label.labelUserOrEmail"));
        messageLabel.setText(localizationManager.getLocalizedString("label.messageLabelAc"));
        passwordLabel.setText(localizationManager.getLocalizedString("label.labelPassword"));
    }

}
