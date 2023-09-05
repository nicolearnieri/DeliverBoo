package uid.project.deliverboo.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import uid.project.deliverboo.model.*;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

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
    private Label questionAccountLabel;

    @FXML
    private TextField userEmailField;

    @FXML
    private Button signUpButton;

    private  LocalizationManager localizationManager;
    private ExecutorService executor = ExecutorProvider.getExecutor();


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }


   @FXML
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
    public void logInUser () throws ExecutionException, InterruptedException {
        String user = userEmailField.getText();
        String password = passwordFieldSU.getText();
        boolean email = ValidatorUtility.isValidEmail(user);
        boolean userExists = false;
        boolean logInSucceded=false;

        if (email) {
            Callable<Boolean> emailCallable = TaskCreator.createEmailNotExists(user);
            Future<Boolean> result = executor.submit(emailCallable);
            Boolean res = result.get();
            if (res)
                logInError(localizationManager.getLocalizedString("error.email"));
            else userExists = true;
        } else {
            Callable<Boolean> userCallable = TaskCreator.createUsernameNotExists(user);
            Future<Boolean> result = executor.submit(userCallable);
            Boolean res = result.get();
            if (res)
                logInError(localizationManager.getLocalizedString("error.user"));
            else userExists = true;
        }

        if (userExists)
        {
            Callable<String> getPwCallable = TaskCreator.createGetPassword(user);
            Future<String> resultP = executor.submit(getPwCallable);
            String resP = resultP.get();
            boolean check = BCrypt.checkpw(password, resP);

            if (check) {
                logInSucceded=true;
                //facimm ancuna cos tu rimember de iuser
                CurrentUser cU = CurrentUser.getInstance();
                if (email) {
                    cU.setEmail(user);
                    Callable<String> getUserCallable = TaskCreator.createGetUsername(user);
                    Future<String> result = executor.submit(getUserCallable);
                    String resS = result.get();
                    cU.setNomeUtente(resS);
                }
                else {
                    cU.setNomeUtente(user);
                    Callable<String> getEmailCallable = TaskCreator.createGetEmail(user);
                    Future<String> result = executor.submit(getEmailCallable);
                    String resE= result.get();
                    cU.setEmail(resE);
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
        questionAccountLabel.setText(localizationManager.getLocalizedString("label.questionAccount2"));
    }

}
