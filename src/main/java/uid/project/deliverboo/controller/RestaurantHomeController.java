package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import uid.project.deliverboo.view.Cart;


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

    public void initialize(){
        cart=new Cart();
        cart.loadCart();

    }

}


