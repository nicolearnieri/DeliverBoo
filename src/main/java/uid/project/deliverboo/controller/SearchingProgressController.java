package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class SearchingProgressController {

    @FXML
    public ProgressIndicator progress;

    @FXML
    private Label researchLabel;

    private LocalizationManager localizationManager;

    public void setLocalizationManager(LocalizationManager localizationManager){
        this.localizationManager = localizationManager;
        updateTexts();
    }

    private void updateTexts(){
        researchLabel.setText(localizationManager.getLocalizedString("wait.label"));


    }
}
