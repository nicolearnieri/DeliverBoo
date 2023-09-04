package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uid.project.deliverboo.view.SceneHandler;

public class PaymentController {

    @FXML
    private Button cancelButton;

    @FXML
    private TextField cardNumberField;

    @FXML
    private Label cardNumberLabel;

    @FXML
    private TextField cardOwnerField;

    @FXML
    private Label cardOwnerLabel;

    @FXML
    private Button confirmationButton;

    @FXML
    private Label endDateLabel;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Label messagePaymentLabel;

    @FXML
    private TextField securityCodeField;

    @FXML
    private Label securityCodeLabel;


    @FXML
    private Label securityLabel;

    @FXML
    private ImageView questionMark;

    @FXML
    private HBox securityCodeBox;

    private LocalizationManager localizationManager;

    public void initialize() {

        securityCodeBox.setManaged(false);

        questionMark.setOnMouseEntered(e -> {
            securityCodeBox.setVisible(true);
            securityCodeBox.setManaged(true);
        });

        questionMark.setOnMouseExited(e -> {
            securityCodeBox.setVisible(false);
            securityCodeBox.setManaged(false);
        });
    }

    @FXML
    void confirmPayment(ActionEvent event) {
        SceneHandler.getInstance().showConfirmation(localizationManager.getLocalizedString("content.PaymentOk"), localizationManager.getLocalizedString("title.PaymentOk"));
    }

    @FXML
    void goBack(ActionEvent event) {

    }


    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;
        //updateTexts();
    }



}
