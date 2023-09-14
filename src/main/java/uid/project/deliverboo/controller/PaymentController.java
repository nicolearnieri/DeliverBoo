package uid.project.deliverboo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import uid.project.deliverboo.model.EmailSender;
import uid.project.deliverboo.model.ValidatorUtility;
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

    private double tot;

    public void initialize(Stage stage, Stage ownStage, LocalizationManager localizationManager, double tot) {
        previousStage=stage;
        this.ownStage=ownStage;
        this.tot=tot;
        this.localizationManager =localizationManager;
        updateTexts();
        securityCodeBox.setManaged(false);
        securityCodeBox.setVisible(false);

        //popUp per il CVV
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

        boolean monthOk = controlOnMonth();
        boolean cardOk = ValidatorUtility.isValidNumber(cardNumberField.getText());
        boolean nameOk = ValidatorUtility.isValidString(cardOwnerField.getText());
        boolean codeOk = false;

        if (ValidatorUtility.isValidNumber(securityCodeField.getText()) && (securityCodeField.getText().length()>2 && securityCodeField.getText().length()<5) )
            codeOk= true;

        if (!monthOk) {SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongMonth.message"), localizationManager.getLocalizedString("wrongMonth.title"));}
        if (!nameOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongName.message"), localizationManager.getLocalizedString("wrongName.title"));}
        if (!codeOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongCode.message"), localizationManager.getLocalizedString("wrongCode.title"));}
        if (!cardOk){SceneHandler.getInstance().showError(localizationManager.getLocalizedString("wrongCard.message"), localizationManager.getLocalizedString("wrongCard.title"));}

        if (cardOk && nameOk && monthOk && codeOk) {
            String message = localizationManager.getLocalizedString("orderConfirmed.body1");
            String value = String.valueOf(tot);
            String message2 = localizationManager.getLocalizedString("orderConfirmed.body2");
            String concatenated = message + value + message2;
            EmailSender.sendEmail(OrderDetailsController.email, localizationManager.getLocalizedString("orderConfirmed.subject"), concatenated);
            SceneHandler.getInstance().setOrderConfirmed();
        }

    }


    @FXML
    void goBack(ActionEvent event) {
        SceneHandler.getInstance().setStageMaximized(previousStage, ownStage.isMaximized());
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
