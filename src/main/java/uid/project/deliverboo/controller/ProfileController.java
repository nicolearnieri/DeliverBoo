package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.CurrentUser;

public class ProfileController {

    @FXML
    private TextField addressField;

    @FXML
    private Label addressLabel;

    @FXML
    private Button deleteButton;

    @FXML
    private Label deleteLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLabel;

    @FXML
    private ImageView logo;

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

    public void initialize ()
    {
        usernameField.setText(CurrentUser.getInstance().getNomeUtente());
        emailField.setText(CurrentUser.getInstance().getEmail());
    }
    @FXML
    void deleteAccount(ActionEvent event) {

    }

    @FXML
    void saveChanges(ActionEvent event) {

    }

    public void setLocalizationManager(LocalizationManager localizationManager) {
        usernameLabel.setText(localizationManager.getLocalizedString("label.usernameLabel"));
        nameLabel.setText(localizationManager.getLocalizedString("label.nameLabel"));
        surnameLabel.setText(localizationManager.getLocalizedString("label.surnameLabel"));
        addressLabel.setText(localizationManager.getLocalizedString("label.addressLabel"));
        phoneLabel.setText(localizationManager.getLocalizedString("label.phoneLabel"));
        emailLabel.setText(localizationManager.getLocalizedString("label.emailLabel"));
        saveButton.setText(localizationManager.getLocalizedString("button.saveButton"));
        otherwiseLabel.setText(localizationManager.getLocalizedString("label.otherwiseLabel"));
        deleteLabel.setText(localizationManager.getLocalizedString("label.deleteLabel"));
        deleteButton.setText(localizationManager.getLocalizedString("button.deleteButton"));
    }
}
