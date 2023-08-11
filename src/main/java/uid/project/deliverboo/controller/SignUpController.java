package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uid.project.deliverboo.model.DataBaseManager;
import uid.project.deliverboo.model.EmailSender;
import uid.project.deliverboo.model.QueryUsers;
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


    private void showUsernameError() {
        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di registrazione");
        alert.setHeaderText("Nome utente non disponibile");
        alert.setContentText("Il nome utente inserito esiste già. Si prega di scegliere un nome utente diverso.");
        alert.showAndWait();
    }

    private void showPasswordError (){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Errore di registrazione");
        alert.setHeaderText("Le password non corrispondono");
        alert.setContentText("Le due password inserite non corrispondono. Si prega di reinserirle.");
        alert.showAndWait();

    }

    private void regSuccess(){

        // Mostra un messaggio di errore all'utente
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registrazione");
        alert.setHeaderText("Registrazione terminata con successo");
        alert.setContentText("La registrazione è stata effettuata. Riceverai a breve un'email di conferma all'indirizzo specificato");
        alert.showAndWait();

    }
    private LocalizationManager localizationManager;


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
        boolean usernameOk = false;

        try {
            //controllo username valido: non dev'essere ripetuto nelle query
            if (QueryUsers.usernameNotExists(username))
            {
                usernameOk = true;
            }
            else {showUsernameError();}

            if (password.equals(repPassword) ) {eqPasswords = true;}
            else {showPasswordError();}

            if (eqPasswords && usernameOk)
            {
                //CODIFICA PASSWORD SALE
                String passwordEncoded= BCrypt.hashpw(password, BCrypt.gensalt(12));
                if (QueryUsers.insertUser(username, "","", email, passwordEncoded, "", "" ))
                { //messaggio avviso che la registrazione è andata a buon fine e il profilo può essere completato da impostazioni
                    EmailSender.sendEmail(email, "Registrazione completata","Registrazione avvenuta con successo! Ti ringraziamo di esserti iscritto a DeliverBoo.");
                    regSuccess();
                }
            }

        }
        catch (Exception exc) {
            exc.printStackTrace(); }

    }

    private void updateTexts(){
        buttonSend.setText(localizationManager.getLocalizedString("button.buttonSend"));
        labelEmail.setText(localizationManager.getLocalizedString("label.labelEmail"));
        emailField.setPromptText(localizationManager.getLocalizedString("textfield.emailField"));
        labelPassword.setText(localizationManager.getLocalizedString("label.labelPassword"));
        labelRPassword.setText(localizationManager.getLocalizedString("label.labelRPassword"));
        labelUsername.setText(localizationManager.getLocalizedString("label.labelUsername"));
        messageLabel.setText(localizationManager.getLocalizedString("label.massageLabelSU"));


    }

    public void setLightMode(){
        SceneHandler.getInstance().changeTheme("LightTheme");}
    public void setDarkTheme(){SceneHandler.getInstance().changeTheme("DarkTheme");}
    public void setParadiseTheme(){SceneHandler.getInstance().changeTheme("ParadiseTheme");}
    public void setDeliverBooTheme(){SceneHandler.getInstance().changeTheme("DeliverBooTheme");}

    public void setObsidianTheme(){SceneHandler.getInstance().changeTheme("ObsidianTheme");}
}
