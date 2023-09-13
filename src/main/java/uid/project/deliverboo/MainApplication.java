package uid.project.deliverboo;

import javafx.application.Application;
import javafx.stage.Stage;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.view.SceneHandler;
import java.util.concurrent.ExecutorService;


public class MainApplication extends Application {

    private ExecutorService executor = ExecutorProvider.getExecutor();
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

