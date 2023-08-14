package uid.project.deliverboo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/SceneBuilder/HomeInterface.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("DeliverBoo");
        String css = getClass().getResource("/css/DarkTheme.css").toExternalForm();

        String font = getClass().getResource("/css/FontDyslexic.css").toExternalForm();

        scene.getStylesheets().add(css);
        scene.getStylesheets().add(font);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}