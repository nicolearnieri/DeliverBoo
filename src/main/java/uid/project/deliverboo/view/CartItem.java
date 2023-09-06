package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.CartItemController;
import uid.project.deliverboo.controller.MenuItemController;

public class CartItem extends StackPane {
    public CartItem() {
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/CartItem.fxml"));
        try {
            HBox root= loader.load();
            CartItemController controller=loader.getController();
            controller.init();
            this.getChildren().add(root);

        }catch (Exception ignoredExeption){}
    }
}
