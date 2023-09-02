package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantItemController;


public class RestaurantItem extends StackPane {


    public RestaurantItem(){

        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/restaurantItem.fxml"));
        try{
            AnchorPane root= loader.load();
            RestaurantItemController controller= loader.getController();
            this.getChildren().add(root);
            root.prefWidthProperty().bind(this.widthProperty());
        }catch (Exception ignoredException){}
    }


}
