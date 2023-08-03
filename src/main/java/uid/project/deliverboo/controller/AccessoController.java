package  uid.project.deliverboo.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class AccessoController {

    @FXML
    private Button accessButton;

    @FXML
    private Label labelPassword;

    @FXML
    private Label labelUserOrEmail;

    @FXML
    private ImageView logo;

    @FXML
    private Label messageLabel;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userOrEmailField;

    @FXML
    void GestioneAccesso(ActionEvent event) {
        //chiamati le query di ricerca accesso
    }

}
