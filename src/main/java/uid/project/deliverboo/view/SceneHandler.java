package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uid.project.deliverboo.controller.*;

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


    private Stage logInOrSignUpStage;

    private String theme = "ParadiseTheme";
    private static SceneHandler instance = null;

    private LocalizationManager localizationManager;


    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che può generare eccezione
        if(stage != null)
            return;
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        //loadFonts();

        localizationManager= new LocalizationManager();
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
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        //loadFonts();


        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }

    public void setSearchRestaurants() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SearchRestaurants.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        //loadFonts();


        SearchReasturantsController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }

    public void setLogIn() throws Exception {
        if(logInOrSignUpStage!=null){logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "LogIn.fxml"));
        scene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza


        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL);


        LogInController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        logInOrSignUpStage.setTitle("DeliverBoo");
        logInOrSignUpStage.setScene(scene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.showAndWait();
    }



    public void setSignUp() throws Exception {
        if(logInOrSignUpStage!=null){logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));
        scene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL);


        SignUpController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        logInOrSignUpStage.setTitle("DeliverBoo");
        logInOrSignUpStage.setScene(scene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.showAndWait();
    }


    public void setFaq() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "FAQ.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza
        //loadFonts();


        FAQController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        setCSSForScene(scene);
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }
    private String loadCSS() {
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