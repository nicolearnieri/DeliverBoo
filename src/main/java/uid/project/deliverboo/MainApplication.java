package uid.project.deliverboo;

import javafx.application.Application;
import javafx.stage.Stage;
import uid.project.deliverboo.view.SceneHandler;

import static javafx.application.Application.launch;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            SceneHandler.getInstance().init(primaryStage);
        } catch(Exception e) {
            e.printStackTrace(); //stampa il tracciamento delle chiamate che ha portato all'errore
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}

