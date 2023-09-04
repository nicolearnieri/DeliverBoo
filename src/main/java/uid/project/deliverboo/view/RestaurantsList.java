package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantsListController;

import java.io.IOException;
import java.util.List;


public class RestaurantsList extends AnchorPane {

    public RestaurantsList(){}


    public void loadRestaurantsList(AnchorPane restaurantsListPane, List<Integer> queryResults) throws IOException {
        System.out.println("Altra roba 1");
        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantsList.fxml"));
        System.out.println("Altra roba 2");
        //try{
            System.out.println("Altra roba 3");
            Parent root= loader.load();
            System.out.println("Altra roba 4");
            RestaurantsListController controller= loader.getController();
            System.out.println("Altra roba 5");
            controller.createList(queryResults);
            System.out.println("Altra roba 6");

            restaurantsListPane.getChildren().clear();
            System.out.println("Altra roba 7");
            restaurantsListPane.getChildren().add(root);
            System.out.println("Altra roba 8");

        //}catch (Exception ignoredException){System.out.println("Altra roba 9");}
    }

}
