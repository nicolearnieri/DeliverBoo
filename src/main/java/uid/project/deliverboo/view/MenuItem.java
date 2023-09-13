package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.controller.MenuItemController;
import uid.project.deliverboo.controller.RestaurantHomeController;
import uid.project.deliverboo.model.Food;

public class MenuItem extends StackPane {

    private MenuItemController controller;
    public MenuItem(Food food, LocalizationManager localizationManager, RestaurantHomeController rController){
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/MenuItem.fxml"));
        try {
            BorderPane root= loader.load();
            controller=loader.getController();
            controller.init(food, localizationManager, rController);
            this.getChildren().add(root);

        }catch (Exception ignoredExeption){}
    }

    public MenuItemController getController(){
        return controller;
    }
}
