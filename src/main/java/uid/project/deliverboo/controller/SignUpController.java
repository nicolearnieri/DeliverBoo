package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uid.project.deliverboo.model.QueryUsers;
import uid.project.deliverboo.view.SceneHandler;

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






    private LocalizationManager localizationManager;


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }

    @FXML
    private void registration()
    {
        String email= GetFromTextField(emailField);
        String username = GetFromTextField(usernameField);
        String password = GetPassword(passwordField);
        String repPassword = GetPassword(rPasswordField);
        boolean eqPasswords = false;
        boolean usernameOk = false;

        //controllo username valido: non dev'essere ripetuto nelle query
        if (QueryUsers.usernameNotExists(username))
        {
            usernameOk = true;
        }
        else {
            //dai un errore che dica che non va bene
        }

        if (password.equals(repPassword) )
        {
            eqPasswords = true;
        }
        else {//raise un alert che dice che le password non corrispondono lol
             }

        if (eqPasswords && usernameOk)
        {
            //CODIFICA PASSWORD SALE
            String passwordEncoded="";
            QueryUsers.insertUser(username, "","", email, passwordEncoded, "", "" );
                    //messaggio avviso che la registrazione è andata a buon fine e il profilo può essere completato da impostazioni
        }
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
