package uid.project.deliverboo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;

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
    private Label expiryDateLabel;

    @FXML
    private Label messagePaymentLabel;

    @FXML
    private ComboBox<Integer> monthBox;

    @FXML
    private ImageView questionMark;

    @FXML
    private HBox securityCodeBox;

    @FXML
    private TextField securityCodeField;

    @FXML
    private Label securityCodeLabel;

    @FXML
    private ComboBox<Integer> yearBox;

    @FXML
    private Label securityLabel;

    private LocalizationManager localizationManager;

    private Stage previousStage;

    private Stage ownStage;

    ObservableList<Integer> months = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
    ObservableList<Integer> years = FXCollections.observableArrayList(2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030, 2031, 2032, 2033, 2034, 2035, 2036, 2037, 2038, 2039, 2040);

    Month currentMonth = LocalDate.now().getMonth();
    int monthNumber = currentMonth.getValue();

    int yearNumber = LocalDate.now().getYear();

    public void initialize(Stage stage, Stage ownStage, LocalizationManager localizationManager) {
        this.previousStage=stage;
        this.ownStage=ownStage;
        this.localizationManager =localizationManager;
        updateTexts();
        securityCodeBox.setManaged(false);
        securityCodeBox.setVisible(false);

        questionMark.setOnMouseEntered(e -> {
            securityCodeBox.setVisible(true);
            securityCodeBox.setManaged(true);
        });

        questionMark.setOnMouseExited(e -> {
            securityCodeBox.setVisible(false);
            securityCodeBox.setManaged(false);
        });

        monthBox.setItems(months);
        yearBox.setItems(years);
    }

    @FXML
    void confirmPayment(ActionEvent event) throws IOException {
        if (controlOnMonth())
            SceneHandler.getInstance().setOrderConfirmed();
        else
        {//errore amio
             }
    }

    @FXML
    void goBack(ActionEvent event) {
        SceneHandler.getInstance().showStage(previousStage);
        SceneHandler.getInstance().closeStage(ownStage);
    }

    boolean controlOnMonth()
    {
        if (yearBox.getValue() == yearNumber ) {
            if (monthBox.getValue()<= monthNumber)
                return false;
        }
        return true;
    }

    public void updateTexts(){
        messagePaymentLabel.setText(localizationManager.getLocalizedString("label.messagePayment"));
        cardOwnerLabel.setText(localizationManager.getLocalizedString("label.cardOwner"));
        cardNumberLabel.setText(localizationManager.getLocalizedString("label.cardNumber"));
        expiryDateLabel.setText(localizationManager.getLocalizedString("label.expiryDate"));
        securityCodeLabel.setText(localizationManager.getLocalizedString("label.securityCode"));
        securityLabel.setText(localizationManager.getLocalizedString("label.securityLabel"));
        cancelButton.setText(localizationManager.getLocalizedString("button.cancel"));
        confirmationButton.setText(localizationManager.getLocalizedString("button.confirm"));
    }



}
