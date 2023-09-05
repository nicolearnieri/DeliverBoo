package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.view.Cart;
import uid.project.deliverboo.view.MenuItem;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Objects;


public class RestaurantHomeController {

    @FXML
    private ImageView backButton;

    @FXML
    private AnchorPane cartSpace;

    @FXML
    private ImageView imageRestaurant;

    @FXML
    private ListView<?> menuList;

    @FXML
    private Label restaurantNameLabel;

    private Cart cart;

    public void initialize(Restaurant restaurant){
        cart=new Cart();
        cart.loadCart();
        /*for(qua va fatta scorrere la lista della degli oggetti del menu){
            //qua vanno creati new MenuItem
        }*/
        restaurantNameLabel.setText(restaurant.getName());
        imageRestaurant.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath2())).toExternalForm()));





    }

}


