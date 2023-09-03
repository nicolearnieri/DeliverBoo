package uid.project.deliverboo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/SceneBuilder/Payment.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 700);
        stage.setTitle("DeliverBoo");
        String css = getClass().getResource("/css/LightTheme.css").toExternalForm();
        //String font = getClass().getResource("/css/FontMontserrat.css").toExternalForm();

        scene.getStylesheets().add(css);
        //scene.getStylesheets().add(font);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}