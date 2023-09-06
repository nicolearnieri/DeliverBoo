package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.controller.MenuItemController;
import uid.project.deliverboo.model.Food;

public class MenuItem extends StackPane {
    public MenuItem(Food food, LocalizationManager localizationManager){
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/ProductOrder.fxml"));
        try {
            BorderPane root= loader.load();
            MenuItemController controller=loader.getController();
            controller.init(food, localizationManager);
            this.getChildren().add(root);

        }catch (Exception ignoredExeption){}
    }
}
