package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.controller.RestaurantsListController;

import java.io.IOException;
import java.util.List;


public class RestaurantsList extends AnchorPane {

    public RestaurantsList(){}


    public void loadRestaurantsList(AnchorPane restaurantsListPane, List<Integer> queryResults) throws IOException {

        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantsList.fxml"));



            Parent root= loader.load();

            RestaurantsListController controller= loader.getController();

            controller.createList(queryResults);


            restaurantsListPane.getChildren().clear();

            restaurantsListPane.getChildren().add(root);



    }

}
