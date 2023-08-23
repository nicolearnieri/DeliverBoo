package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.CartController;

public class Cart extends AnchorPane {

    public Cart(){}
    public void loadCart(){
        FXMLLoader loader=new FXMLLoader(Cart.class.getResource("/SceneBuilder/Cart.fxml"));
        try {
            AnchorPane root=loader.load();
            CartController controller=loader.getController();
            this.getChildren().add(root);
        }catch (Exception ignoredException){}
    }
}
