package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import uid.project.deliverboo.controller.MenuItemController;

public class MenuItem extends StackPane {
    public MenuItem(){
        FXMLLoader loader=new FXMLLoader(MenuItem.class.getResource("/SceneBuilder/MenuItem.fxml"));
        try {
            ListView root= loader.load();
            MenuItemController controller=loader.getController();
            this.getChildren().add(root);

        }catch (Exception ignoredExeption){}
    }
}
