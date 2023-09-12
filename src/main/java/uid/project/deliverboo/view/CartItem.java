package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.CartItemController;
import uid.project.deliverboo.controller.LocalizationManager;

import uid.project.deliverboo.controller.RestaurantHomeController;
import uid.project.deliverboo.model.Food;

import java.io.IOException;

public class CartItem extends StackPane {

    private Food food;

    private CartItemController controller;
    public CartItem (Food food, LocalizationManager localizationManager, RestaurantHomeController restaurantHomeController) throws IOException {
        this.food=food;
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/CartItem.fxml"));
        //try {
            HBox root= loader.load();
            controller=loader.getController();
            controller.init(food, localizationManager, restaurantHomeController, this);
            this.getChildren().add(root);

       // }catch (Exception ignoredExeption){}
    }

    public CartItemController returnCotroller(){
        return controller;
    }

    public Food getFood(){
        return food;
    }

    public int getQuantity(){
        return controller.getQuantity();
    }

}
