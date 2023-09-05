package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantItemController;
import uid.project.deliverboo.model.Restaurant;

import java.io.IOException;


public class RestaurantItem extends StackPane {



    public RestaurantItem(Restaurant restaurant) throws IOException {

        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/restaurantItem.fxml"));
        //try{
            AnchorPane root= loader.load();
            RestaurantItemController controller= loader.getController();
            controller.init(restaurant);
            this.getChildren().add(root);
            root.prefWidthProperty().bind(this.widthProperty());
        //}catch (Exception ignoredException){System.out.println("Catch di restaurant item");}
    }


}
