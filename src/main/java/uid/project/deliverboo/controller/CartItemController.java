package uid.project.deliverboo.controller;

import uid.project.deliverboo.model.Food;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import uid.project.deliverboo.view.CartItem;

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

    private RestaurantHomeController restaurantHomeController;

    private CartItem cartItem;


    public void init(Food food, LocalizationManager localizationManager, RestaurantHomeController restaurantHomeController, CartItem cartItem){
        this.food=food;
        this.cartItem=cartItem;
        this.restaurantHomeController=restaurantHomeController;
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

    public void deductFood(Food f, int cont){
        if(food.equals(f)){
            quantitylabel.setText(Integer.toString(cont));

        }

    }

    public void ownAddFood(){
        cont+=1;
        quantitylabel.setText(Integer.toString(cont));
        restaurantHomeController.addFood(food, cont);
    }

    public void ownDeductFood(){
        cont-=1;
        quantitylabel.setText(Integer.toString(cont));
        restaurantHomeController.deductFood(food, cont, cartItem);

    }








}
