package uid.project.deliverboo.controller;

import uid.project.deliverboo.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.Locale;

public class CartItemController {

    @FXML
    private Label descriptionLabel;

    @FXML
    private Button minusButton;

    @FXML
    private Label nameLabel;

    @FXML
    private Button plusButton;

    @FXML
    private Label priceLabel;

    @FXML
    private Label quantitylabel;

    private Food food;

    private int cont=1;

    public void init(Food food, LocalizationManager localizationManager){
        this.food=food;
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            nameLabel.setText(food.getItName());
            descriptionLabel.setText(food.getItDescr());

        }else{
            nameLabel.setText(food.getEngName());
            descriptionLabel.setText(food.getEngDescr());
        }

        priceLabel.setText(food.getPrice()+"€");
        quantitylabel.setText(Integer.toString(cont));

    }

    public void addFood(Food f){
        if(food.equals(f)){
            cont+=1;
            quantitylabel.setText(Integer.toString(cont));

        }

    }

    public int deductFood(Food f){
        if(food.equals(f)){
            cont-=1;
            quantitylabel.setText(Integer.toString(cont));

            return cont;
        }
        return -1;
    }




}
