package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantsListController;

public class RestaurantsList extends StackPane {

    public RestaurantsList(){
        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantsList.fxml"));
        try{
            AnchorPane root= loader.load();
            RestaurantsListController controller= loader.getController();
            this.getChildren().add(root);
            root.prefWidthProperty().bind(this.widthProperty());
        }catch (Exception ignoredException){}
    }

}
