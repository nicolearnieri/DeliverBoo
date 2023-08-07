package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uid.project.deliverboo.controller.HomeController;
import uid.project.deliverboo.controller.LocalizationManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneHandler {
    private final static String RESOURCE_PATH = "/src/main/resources/";
    private final static String CSS_PATH = "/css/";

    //private final static String FONTS_PATH = RESOURCE_PATH + "fonts/";
    private final static String FXML_PATH = "/SceneBuilder/";
    private Scene scene;
    private Stage stage;
    private String theme = "minimalist"; //da rivedere
    private static SceneHandler instance = null;

    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che può generare eccezione
        if(stage != null)
            return;
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 700, 600); //v:larghezza, v1:altezza
        //loadFonts();

        LocalizationManager localizationManager= new LocalizationManager();
        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }
    public static SceneHandler getInstance() {
        if(instance == null)
            instance = new SceneHandler();
        return instance;
    }
    public void setHomeInterface() throws Exception {
        setCurrentRoot("HomeInterface.fxml");
        stage.hide();
        stage.setWidth(700);
        stage.setHeight(600);
        stage.show();
    }
    public void setSearchRestaurants() throws Exception {
        setCurrentRoot("SearchRestaurants.fxml");
        stage.hide();
        stage.setWidth(700);
        stage.setHeight(600);
        stage.show();
    }
    public void setLogIn() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "LogIn.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);// nuova finestra con cui interagire obbligatoriamente
        Scene scene = new Scene(loader.load(), 300, 500);
        setCSSForScene(scene);
        stage.setScene(scene);
        //stage.showAndWait();// a fine operazione non si chiude, lo deve fare l'utente
    }
    public void setSignUp() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(loader.load(), 300, 500);
        setCSSForScene(scene);
        stage.setScene(scene);
        //stage.showAndWait();
    }
    private String loadCSS() { //ERRORE SUL PATH
        try{
            String style= CSS_PATH + theme + ".css";
            return Objects.requireNonNull(SceneHandler.class.getResource(style)).toExternalForm();}
        catch(NullPointerException e){
            e.printStackTrace();
            return "";}

    }
    private void setCSSForScene(Scene scene) { //in base a theme setta i css per la scena
        Objects.requireNonNull(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(loadCSS());
    }
    private void setCurrentRoot(String filename) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + filename));
        scene.setRoot(loader.load());
    }
    public void changeTheme(String newTheme) {
        theme=newTheme;
        setCSSForScene(scene);
    }

}