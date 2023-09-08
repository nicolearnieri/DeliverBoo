package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.controller.RecapItemController;
import uid.project.deliverboo.model.Food;

import java.io.IOException;

public class RecapItem extends StackPane {

    public RecapItem(Food food, LocalizationManager localizationManager, int quantity) throws IOException {
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/RecapItem.fxml"));

        HBox root= loader.load();
        RecapItemController controller=loader.getController();
        controller.init(food, localizationManager, quantity);
        this.getChildren().add(root);


    }
}

