package uid.project.deliverboo.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import uid.project.deliverboo.view.SceneHandler;

public class PaymentController {

    @FXML
    private Label SecurityLabel;

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

    private LocalizationManager localizationManager;


    public void initialize() {

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
