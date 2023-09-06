package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import uid.project.deliverboo.view.CartItem;
import uid.project.deliverboo.view.MenuItem;

public class CartController {

    @FXML
    private ListView<CartItem> cartList;

    @FXML
    private Button paymentButton;

    public void init(){

    }

}
