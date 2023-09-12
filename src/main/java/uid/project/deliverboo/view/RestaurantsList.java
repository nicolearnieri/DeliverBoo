package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.controller.RestaurantsListController;

import java.io.IOException;
import java.util.List;


public class RestaurantsList extends AnchorPane {

    private RestaurantsListController controller;

    public RestaurantsList(){}


    public void loadRestaurantsList(AnchorPane restaurantsListPane, List<Integer> queryResults, LocalizationManager localizationManager) throws IOException {

        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantsList.fxml"));


            Parent root= loader.load();

            controller= loader.getController();

            controller.clearRestaurants();

            controller.createList(queryResults, localizationManager, restaurantsListPane);


            restaurantsListPane.getChildren().clear();

            restaurantsListPane.getChildren().add(root);



    }

    public RestaurantsListController getController(){
        return controller;
    }

}
