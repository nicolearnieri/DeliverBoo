package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uid.project.deliverboo.model.CurrentUser;
import uid.project.deliverboo.model.ValidatorUtility;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;

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

    @FXML
    private Button proceedButton;
    private LocalizationManager localizationManager;

    private Stage homeRStage;

    private Stage stage;

    public void init(LocalizationManager localizationManager,Stage ownStage, Stage homeRStage) {
        this.stage=ownStage;
        this.homeRStage=homeRStage;
        emailField.setText(CurrentUser.getInstance().getEmail());
        nameField.setText(CurrentUser.getInstance().getName());
        surnameField.setText(CurrentUser.getInstance().getSurname());
        phoneNumberField.setText(CurrentUser.getInstance().getPhoneNumber());

        this.localizationManager=localizationManager;
        updateTexts();

    }

    public void updateTexts() {
        nameLabel.setText(localizationManager.getLocalizedString("label.nameLabel"));
        surnameLabel.setText(localizationManager.getLocalizedString("label.surnameLabel"));
        numberLabel.setText(localizationManager.getLocalizedString("label.phoneLabel"));
        emailLabel.setText(localizationManager.getLocalizedString("label.emailLabel"));
        houseNumberLabel.setText(localizationManager.getLocalizedString("label.houseNumber"));
        addressLabel.setText(localizationManager.getLocalizedString("label.infoAddress"));
        proceedButton.setText(localizationManager.getLocalizedString("button.proceedButton"));
    }

    public void goBack(){
        SceneHandler.getInstance().closeStage(stage);
        SceneHandler.getInstance().showStage(homeRStage);
    }

    public void proceed() throws IOException {
        String email=new String();
        String number=new String();
        if(nameField!=null && surnameField!=null && phoneNumberField!=null && emailField!=null && civicNumberField!=null){
            String name = (nameField.getText() != null) ? nameField.getText().trim() : "";
            String surname = (surnameField.getText() != null) ? surnameField.getText().trim() : "";
            number = (phoneNumberField.getText() != null) ? phoneNumberField.getText().trim() : "";
            email = (emailField.getText() != null) ? emailField.getText().trim() : "";
            String cNumber = (civicNumberField.getText() != null) ? civicNumberField.getText().trim() : "";
            if( name.isEmpty()|| surname.isEmpty() || number.isEmpty() || email.isEmpty() || cNumber.isEmpty()){
                SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("infoField.text"), "infoField.title");
            }else{
                if(ValidatorUtility.isValidEmail(email)==false && ValidatorUtility.isValidPhoneNumber(number)==false){
                    System.out.println("email e numero");
                    SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("infoEmail&Number.text"), ("phoneError.title"));
                }else if(ValidatorUtility.isValidEmail(email)==false){
                    System.out.println("email");
                    SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("error.emailInvalid"), ("phoneError.title"));

                }else if(ValidatorUtility.isValidPhoneNumber(number)==false){
                    System.out.println("numero");
                    SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("phoneError.text"), ("phoneError.title"));
                }else{
                    SceneHandler.getInstance().setPayment();
                }

            }
        }else{
            SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("infoField.text"), "infoField.title");

        }



    }
}
