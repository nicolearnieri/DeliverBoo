package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.model.ValidatorUtility;
import uid.project.deliverboo.view.SceneHandler;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class ProfileController {

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLabel;

    @FXML
    private Button logOutButton;

    @FXML
    private Label logOutLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private Label otherwiseLabel;

    @FXML
    private TextField phoneField;

    @FXML
    private Label phoneLabel;

    @FXML
    private Button saveButton;

    @FXML
    private TextField surnameField;

    @FXML
    private Label surnameLabel;

    @FXML
    private TextField usernameField;

    @FXML
    private Label usernameLabel;

    private ExecutorService executor = ExecutorProvider.getExecutor();
    private LocalizationManager localizationManager;


    public void initialize ()
    {
        usernameField.setText(CurrentUser.getInstance().getNomeUtente());
        emailField.setText(CurrentUser.getInstance().getEmail());
        nameField.setText(CurrentUser.getInstance().getName());
        surnameField.setText(CurrentUser.getInstance().getSurname());
        phoneField.setText(CurrentUser.getInstance().getPhoneNumber());
    }

    @FXML
    void deleteAccount(ActionEvent event) throws Exception {

        SceneHandler.getInstance().setPasswordConfirmation();

    }

    @FXML
    void saveChanges(ActionEvent event) throws ExecutionException, InterruptedException {
        String newName = nameField.getText();
        String newSurname = surnameField.getText();
        String newPhone = phoneField.getText();

        boolean nameOk = ValidatorUtility.isValidString(newName);
        boolean surnameOk = ValidatorUtility.isValidString(newSurname);
        boolean phoneOk = ValidatorUtility.isValidPhoneNumber(newPhone);


        if (!nameOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongName.message"), localizationManager.getLocalizedString("wrongName.title"));}
        if (!surnameOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongSurname.message"), localizationManager.getLocalizedString("wrongSurname.title"));}
        if (!phoneOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongPhone.message"), localizationManager.getLocalizedString("wrongPhone.title"));}

        if (nameOk && surnameOk && phoneOk)
        {
            Callable<Boolean> update = TaskCreator.createUpdateOnUser(CurrentUser.getInstance().getNomeUtente(), newName, newSurname, newPhone);
            Future<Boolean> exec = executor.submit(update);
            if (exec.get()) {

                SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("saveInfo.text"), localizationManager.getLocalizedString("saveInfo.title"));
                CurrentUser.getInstance().setName(newName);
                CurrentUser.getInstance().setSurname(newSurname);
                CurrentUser.getInstance().setPhoneNumber(newPhone);
            }
        }
        else
        {
            SceneHandler.getInstance().showError(localizationManager.getLocalizedString("phoneError.text"), localizationManager.getLocalizedString("phoneError.title"));
        }

    }

    @FXML
    void logOut(ActionEvent event) {

        CurrentUser.getInstance().logOut();
        SceneHandler.getInstance().closeStage(SceneHandler.getInstance().returnClientStage());

    }

    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;

        updateTexts();
    }

    public void updateTexts() {
        usernameLabel.setText(localizationManager.getLocalizedString("label.usernameLabel"));
        nameLabel.setText(localizationManager.getLocalizedString("label.nameLabel"));
        surnameLabel.setText(localizationManager.getLocalizedString("label.surnameLabel"));
        phoneLabel.setText(localizationManager.getLocalizedString("label.phoneLabel"));
        emailLabel.setText(localizationManager.getLocalizedString("label.emailLabel"));
        saveButton.setText(localizationManager.getLocalizedString("button.saveButton"));
        otherwiseLabel.setText(localizationManager.getLocalizedString("label.otherwiseLabel"));
        deleteLabel.setText(localizationManager.getLocalizedString("label.deleteLabel"));
        deleteButton.setText(localizationManager.getLocalizedString("button.deleteButton"));
        logOutButton.setText(localizationManager.getLocalizedString("button.logOut"));
        logOutLabel.setText(localizationManager.getLocalizedString("label.logOut"));
    }
}
