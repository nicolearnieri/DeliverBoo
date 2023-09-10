package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import uid.project.deliverboo.view.SceneHandler;

public class OrderConfirmedController {
    @FXML
    private Button backToHomeButton;

    @FXML
    private Label orderConfrimedLabel;

    @FXML
    private Label seeYouAgainLabel;

    public void init(LocalizationManager localizationManager){
        orderConfrimedLabel.setText(localizationManager.getLocalizedString("label.orderConfirmed"));
        seeYouAgainLabel.setText(localizationManager.getLocalizedString("label.seeYouAgain"));
        backToHomeButton.setText(localizationManager.getLocalizedString("button.backToHome"));
    }

    public void backToHome() throws Exception {
        SceneHandler.getInstance().setHomeInterface();
    }
}
