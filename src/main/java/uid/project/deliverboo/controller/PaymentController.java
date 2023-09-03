package uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    void confirmPayment(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {

    }

}
