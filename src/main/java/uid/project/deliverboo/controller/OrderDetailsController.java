package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uid.project.deliverboo.model.CurrentUser;

public class OrderDetailsController {

    @FXML
    private Label addressLabel;

    @FXML
    private Button backButton;

    @FXML
    private TextField civicNumberField;

    @FXML
    private TextField emailField;

    @FXML
    private Label emailLabel;

    @FXML
    private Label houseNumberLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label nameLabel;

    @FXML
    private TextArea notesArea;

    @FXML
    private Label numberLabel;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField surnameField;

    @FXML
    private Label surnameLabel;
    private LocalizationManager localizationManager;

    public void initialize ()
    {
        emailField.setText(CurrentUser.getInstance().getEmail());
        nameField.setText(CurrentUser.getInstance().getName());
        surnameField.setText(CurrentUser.getInstance().getSurname());
        phoneNumberField.setText(CurrentUser.getInstance().getPhoneNumber());
    }

    public void updateTexts() {
        nameLabel.setText(localizationManager.getLocalizedString("label.nameLabel"));
        surnameLabel.setText(localizationManager.getLocalizedString("label.surnameLabel"));
        numberLabel.setText(localizationManager.getLocalizedString("label.phoneLabel"));
        emailLabel.setText(localizationManager.getLocalizedString("label.emailLabel"));
        houseNumberLabel.setText(localizationManager.getLocalizedString("label.houseNumber"));
    }
}
