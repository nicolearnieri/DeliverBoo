package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.PropertySheet;
import uid.project.deliverboo.model.Food;
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





    public void init(Food food, LocalizationManager localizationManager){
        this.food=food;
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


    public void addFood(){
        cont+=1;
        quantityOfProduct.setText(Integer.toString(cont));
        //CartController.addProduct(food);
    }

    public void deductFood(){
        if(cont>0){
            cont-=1;
            quantityOfProduct.setText(Integer.toString(cont));
        }
    }

}
