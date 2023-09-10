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
    private ComboBox<String> monthBox;

    @FXML
    private ImageView questionMark;

    @FXML
    private HBox securityCodeBox;

    @FXML
    private TextField securityCodeField;

    @FXML
    private Label securityCodeLabel;

    @FXML
    private ComboBox<String> yearBox;

    @FXML
    private Label securityLabel;

    private LocalizationManager localizationManager;

    private Stage previousStage;

    private Stage ownStage;


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

        ObservableList<String> months = FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08","09","10", "11", "12");
        ObservableList<String> years = FXCollections.observableArrayList("2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030","2031","2032", "2033", "2034","2035","2036","2037","2038","2039","2040");
        monthBox.setItems(months);
        yearBox.setItems(years);
    }

    @FXML
    void confirmPayment(ActionEvent event) throws IOException {
        SceneHandler.getInstance().setOrderConfirmed();
    }

    @FXML
    void goBack(ActionEvent event) {
        SceneHandler.getInstance().showStage(previousStage);
        SceneHandler.getInstance().closeStage(ownStage);
    }


    public void updateTexts(){
        messagePaymentLabel.setText(localizationManager.getLocalizedString("label.messagePayment"));
        cardOwnerLabel.setText(localizationManager.getLocalizedString("label.cardOwner"));
        cardNumberLabel.setText(localizationManager.getLocalizedString("label.cardNumber"));
        expiryDateLabel.setText(localizationManager.getLocalizedString("label.expiryDate"));
        securityCodeLabel.setText(localizationManager.getLocalizedString("label.securityCode"));
        securityLabel.setText(localizationManager.getLocalizedString("label.securityLabel"));
    }



}
