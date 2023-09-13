package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.Food;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class MenuItemController{

    @FXML
    private Separator bottomSeparator;

    @FXML
    private Button minusButton;

    @FXML
    private Button plusButton;

    @FXML
    private Label price;


    @FXML
    private Label productDescription;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label quantityOfProduct;

    @FXML
    private Separator topSeparator;

    private int cont=0;

    private Food food;

    private RestaurantHomeController controller;



    public void init(Food food, LocalizationManager localizationManager, RestaurantHomeController controller){
        this.food=food;
        this.controller=controller;

        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            productName.setText(food.getItName());
            productDescription.setText(food.getItDescr());
            price.setText("Prezzo: "+food.getPrice()+"€");
        }else{
            productName.setText(food.getEngName());
            productDescription.setText(food.getEngDescr());
            price.setText("Price: "+food.getPrice()+"€");
        }

        productImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(food.getPath())).toExternalForm()));

        quantityOfProduct.setText("0");

    }


    public void ownAddFood() throws IOException {
        cont+=1;
        quantityOfProduct.setText(Integer.toString(cont));
        controller.ownAddFoodInCart(food);
    }

    public void ownDeductFood() throws IOException {
        if(cont>0){
            cont-=1;
            quantityOfProduct.setText(Integer.toString(cont));
            controller.ownDeductFoodInCart(food, cont);

        }
    }

    public void addFood(Food f, int contCart){
        if(food.equals(f)){
            quantityOfProduct.setText(Integer.toString(contCart));
            cont=contCart;
        }
    }

    public void deductFood(Food f, int contCart){
        if(food.equals(f)){
            quantityOfProduct.setText(Integer.toString(contCart));
            cont=contCart;
            if(contCart==0) cont=0;

        }

    }


}
