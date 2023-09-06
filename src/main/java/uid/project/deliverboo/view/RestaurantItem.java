package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantItemController;
import uid.project.deliverboo.model.Restaurant;

import java.io.IOException;


public class RestaurantItem extends StackPane {

    private Restaurant restaurant;



    public RestaurantItem(Restaurant restaurant) throws IOException {
        this.restaurant=restaurant;
        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantItem.fxml"));
        //try{
            HBox root= loader.load();
            RestaurantItemController controller= loader.getController();
            controller.init(restaurant);
            this.getChildren().add(root);
            root.prefWidthProperty().bind(this.widthProperty());
        //}catch (Exception ignoredException){System.out.println("Catch di restaurant item");}
    }

    public  Restaurant getRestaurant(){
        return restaurant;
    }


}
