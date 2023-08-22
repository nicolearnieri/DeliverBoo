package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.RestaurantItemController;
import uid.project.deliverboo.model.QuerySearch;

public class RestaurantItem extends StackPane {

    private QuerySearch querySearch;


    public RestaurantItem(QuerySearch querySearch){
        this.querySearch=querySearch;
        FXMLLoader loader= new FXMLLoader(RestaurantsList.class.getResource("/SceneBuilder/RestaurantItem.fxml"));
        try{
            AnchorPane root= loader.load();
            RestaurantItemController controller= loader.getController();
            this.getChildren().add(root);
            root.prefWidthProperty().bind(this.widthProperty());
        }catch (Exception ignoredException){}
    }


}
