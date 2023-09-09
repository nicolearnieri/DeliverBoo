package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import uid.project.deliverboo.model.Food;

import java.util.Locale;

public class RecapItemController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantityLabel;


    public void init(Food food, LocalizationManager localizationManager, int quantity){
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            nameLabel.setText(food.getItName());
            quantityLabel.setText("Quantità: "+quantity);
        }else{
            nameLabel.setText(food.getEngName());
            quantityLabel.setText("Quantity: "+quantity);
        }

        priceLabel.setText(food.getPrice()+"€");

    }

}

