package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uid.project.deliverboo.model.DataBaseManager;
import uid.project.deliverboo.model.EmailSender;
import uid.project.deliverboo.model.QueryUsers;
import uid.project.deliverboo.model.ValidatorUtility;
import uid.project.deliverboo.view.SceneHandler;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Locale;

public class SignUpController {

    @FXML
    private Button buttonSend;

    @FXML
    private TextField emailField;

    @FXML
    private Label labelEmail;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelRPassword;

    @FXML
    private Label labelUsername;

    @FXML
    private ImageView logo;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField rPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label passwordSuggestions;

    @FXML
    private HBox passwordSuggestionBox;

    @FXML
    private Button logInButton;

    private LocalizationManager localizationManager;

    public void initialize() {

        passwordSuggestionBox.setManaged(false);

        passwordField.setOnMouseEntered(e -> {
            passwordSuggestionBox.setVisible(true);
            passwordSuggestionBox.setManaged(true);
        });

        passwordField.setOnMouseExited(e -> {
            passwordSuggestionBox.setVisible(false);
            passwordSuggestionBox.setManaged(false);
        });
    }



    @FXML
    private String GetFromTextField(TextField t)
    {
         return t.getText();
    }

    @FXML
    private String GetPassword(PasswordField f) //metodo generico per prendere le password
    {
        return f.getText();
    }

    public void openLogIn() throws Exception {
        SceneHandler.getInstance().setLogIn();
    }


    private void showUsernameError() {
        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(localizationManager.getLocalizedString("title.errorSignUp"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.errorUser"));
        alert.setContentText(localizationManager.getLocalizedString("content.errorUser"));
        alert.showAndWait();
    }

    private void showEmailError(String content) {
        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(localizationManager.getLocalizedString("title.errorSignUp"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.errorEmail"));
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void showPasswordError (){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(localizationManager.getLocalizedString("title.errorSignUp"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.errorPassword"));
        alert.setContentText(localizationManager.getLocalizedString("content.errorPassword"));
        alert.showAndWait();

    }

    private void genericError (){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(localizationManager.getLocalizedString("title.error"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.error"));
        alert.setContentText(localizationManager.getLocalizedString("content.error"));
        alert.showAndWait();

    }
    private void regSuccess(){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(localizationManager.getLocalizedString("title.singUp"));
        alert.setHeaderText(localizationManager.getLocalizedString("header.signUp"));
        alert.setContentText(localizationManager.getLocalizedString("content.signUp"));
        alert.showAndWait();

    }



    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }

    @FXML
    private void registration(ActionEvent event)
    {
        String email= GetFromTextField(emailField);
        String username = GetFromTextField(usernameField);
        String password = GetPassword(passwordField);
        String repPassword = GetPassword(rPasswordField);
        boolean eqPasswords = false;
        boolean passwordOk = false;
        boolean emailOk = false;
        boolean usernameOk = false;

        try {
            //controllo username valido: non dev'essere ripetuto nelle query
            if (QueryUsers.usernameNotExists(username)) { usernameOk = true; }
            else {showUsernameError();}

            if ( ValidatorUtility.isValidEmail(email)){
                if (QueryUsers.emailNotExists(email)) {emailOk = true;}
                else { showEmailError(localizationManager.getLocalizedString("error.emailExist")); }
            }
            else { showEmailError(localizationManager.getLocalizedString("error.emailInvalid")); }

            if (ValidatorUtility.isValidPassword(password)) passwordOk= true;

            if (password.equals(repPassword) ) {eqPasswords = true;}
            else {showPasswordError();}

            if (eqPasswords && usernameOk && emailOk && passwordOk)
            {
                //CODIFICA PASSWORD SALE
                String passwordEncoded= BCrypt.hashpw(password, BCrypt.gensalt(12));
                if (QueryUsers.insertUser(username, "","", email, passwordEncoded, "", "" ))
                { //messaggio avviso che la registrazione è andata a buon fine e il profilo può essere completato da impostazioni
                    EmailSender.sendEmail(email, localizationManager.getLocalizedString("email.subject"),localizationManager.getLocalizedString("email.body"));
                    regSuccess();
                }
            }
            else {genericError();}

        }
        catch (Exception exc) {
            exc.printStackTrace(); }

    }

    @FXML
    void openLogIn(ActionEvent event) throws Exception {
        SceneHandler.getInstance().setLogIn();
    }


    private void updateTexts(){
        buttonSend.setText(localizationManager.getLocalizedString("button.buttonSend"));
        labelEmail.setText(localizationManager.getLocalizedString("label.labelEmail"));
        emailField.setPromptText(localizationManager.getLocalizedString("textfield.emailField"));
        labelPassword.setText(localizationManager.getLocalizedString("label.labelPassword"));
        labelRPassword.setText(localizationManager.getLocalizedString("label.labelRPassword"));
        labelUsername.setText(localizationManager.getLocalizedString("label.labelUsername"));
        messageLabel.setText(localizationManager.getLocalizedString("label.massageLabelSU"));
        logInButton.setText(localizationManager.getLocalizedString("button.accessButtonAc"));


    }

}
