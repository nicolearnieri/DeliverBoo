package uid.project.deliverboo.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import uid.project.deliverboo.controller.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.kordamp.ikonli.javafx.FontIcon;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.action.Action;

import javafx.scene.control.ButtonType;
import uid.project.deliverboo.model.CurrentUser;

public class SceneHandler {
    private final static String RESOURCE_PATH = "/src/main/resources/";
    private final static String CSS_PATH = "/css/";

    private final static String FONTS_PATH = CSS_PATH + "fonts/";
    private final static String FXML_PATH = "/SceneBuilder/";
    private final Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alertError = new Alert(Alert.AlertType.ERROR);
    private final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private Scene scene;
    private Stage stage;

    private Stage logInOrSignUpStage;

    private String theme = "DarkTheme";
    private String font = "FontMontserrat";
    private static SceneHandler instance = null;

    private LocalizationManager localizationManager;


    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che può generare eccezione
        if(stage != null)
            return;
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza

        localizationManager= new LocalizationManager();
        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme();
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


        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);



        changedTheme();
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }

    public void setSearchRestaurants() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SearchRestaurants.fxml"));
        scene = new Scene(loader.load(), 1080, 700); //v:larghezza, v1:altezza

        SearchRestaurantsController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);



        changedTheme();
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

        changedTheme();
        logInOrSignUpStage.setTitle("DeliverBoo");
        logInOrSignUpStage.setScene(scene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }



    public void setSignUp() throws Exception {
        if(logInOrSignUpStage!=null){logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));
        scene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL);


        SignUpController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme();
        logInOrSignUpStage.setTitle("DeliverBoo");
        logInOrSignUpStage.setScene(scene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }

    public void setProfile() throws Exception {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Profile.fxml"));
        scene = new Scene(loader.load(), 500, 700); //v:larghezza, v1:altezza


        ProfileController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme();
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }

    public void setFaq() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "FAQ.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza


        FAQController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme();
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }
    private List<String> loadCSS() {
        try {
            List<String> resources = new ArrayList<>();
            for (String style : List.of(CSS_PATH + theme + ".css", CSS_PATH + font + ".css")) {
                String resource = Objects.requireNonNull(SceneHandler.class.getResource(style)).toExternalForm();
                resources.add(resource);
            }
            return resources;
        }
        catch(NullPointerException e){
            e.printStackTrace();
            return new ArrayList<>();}

    }
    private void loadMontserrat() {
        Font.loadFont(getClass().getResourceAsStream("/css/fonts/Montserrat-Regular.ttf"), 12);
        //System.out.println(myfont.getFamily());
    }
    private void loadOpenDyslexic() {
        Font.loadFont(getClass().getResourceAsStream("/css/fonts/OpenDyslexic-Regular.otf"), 12);
    }

    private void setCSSForScene(Scene scene) { //in base a theme setta i css per la scena
        Objects.requireNonNull(scene);
        List<String> resources = loadCSS();
        scene.getStylesheets().clear();
        for(String resource : resources)
            scene.getStylesheets().add(resource);

        if (font.equals("FontMontserrat")) {
            loadMontserrat();
        } else {
            loadOpenDyslexic();
        }
    }

    private void setCSSForAlert(Alert alert) {
        Objects.requireNonNull(alert, "Alert cannot be null");
        List<String> resources = loadCSS();
        alert.getDialogPane().getStylesheets().clear();
        for (String resource : resources)
            alert.getDialogPane().getStylesheets().add(resource);
    }

    private void changedTheme() {
        setCSSForScene(scene);
        setCSSForAlert(alertError);
        setCSSForAlert(alertInfo);
        setCSSForAlert(alertConfirmation);
    }

    private void setCurrentRoot(String filename) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + filename));
        scene.setRoot(loader.load());
    }

    public void changeTheme(String newTheme) {
        theme=newTheme;
        changedTheme();
    }

    public void changeFont(String newFont) {
        font= newFont;
        changedTheme();
    }
    public void showError(String message, String title) {
        FontIcon icon = new FontIcon("mdi2a-alert");
        icon.getStyleClass().add("icons-color");//da aggiungere nei css
        icon.setIconSize(40);
        alertError.setGraphic(icon);
        alertError.setTitle(title);
        alertError.setHeaderText("");
        alertError.setContentText(message);
        alertError.getDialogPane().setPrefWidth(500);
        alertError.showAndWait();
    }

    public void showInfo(String message, String title) {
        FontIcon icon = new FontIcon("mdi2i-information-outline");
        icon.getStyleClass().add("icons-color"); //da aggiungere nei css
        icon.setIconSize(40);
        alertInfo.setGraphic(icon);
        alertInfo.setTitle(title);
        alertInfo.setHeaderText("");
        alertInfo.setContentText(message);
        alertInfo.getDialogPane().setPrefWidth(500);
        alertInfo.show();
    }
    public boolean showConfirmation(String message, String title) {
        FontIcon icon = new FontIcon("mdi2h-home-map-marker");
        icon.getStyleClass().add("icons-color");
        icon.setIconSize(40);
        alertConfirmation.setGraphic(icon);
        alertConfirmation.setTitle(title);
        alertConfirmation.setHeaderText("");
        alertConfirmation.setContentText(message);
        alertConfirmation.getDialogPane().setPrefWidth(600);

        ButtonType buttonTypeYes = new ButtonType(localizationManager.getLocalizedString("address.yes"));
        ButtonType buttonTypeNo = new ButtonType(localizationManager.getLocalizedString("address.no"));

        alertConfirmation.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        ButtonType response = alertConfirmation.showAndWait().orElse(buttonTypeYes); //default se l'utente non preme ne su SI ne su NO

        return response == buttonTypeYes;
    }
}
