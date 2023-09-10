package uid.project.deliverboo.view;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import uid.project.deliverboo.controller.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ExecutorService;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.control.ButtonType;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.Food;
import uid.project.deliverboo.model.Restaurant;

public class SceneHandler {
    private final static String RESOURCE_PATH = "/src/main/resources/";
    private final static String CSS_PATH = "/css/";

    private final static String FONTS_PATH = CSS_PATH + "fonts/";
    private final static String FXML_PATH = "/SceneBuilder/";
    private final Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alertError = new Alert(Alert.AlertType.ERROR);
    private final Alert alertConfirmation = new Alert(Alert.AlertType.CONFIRMATION);
    private Scene scene;

    private Scene logInScene;
    private Stage stage;

    private Stage logInOrSignUpStage;
    private Stage chargingStage;

    private Stage secondStage;

    private Scene searchScene;

    private String theme = "DarkTheme";
    private String font = "FontMontserrat";
    private static SceneHandler instance = null;

    public static boolean isLoadingVisible() {
        return loadingVisible;
    }

    private static boolean loadingVisible = false;

    private LocalizationManager localizationManager;

    private ExecutorService executor = ExecutorProvider.getExecutor();


    private SceneHandler() {}

    public void init(Stage primaryStage) throws Exception { //metodo che puÃ² generare eccezione
        if(stage != null)
            return;
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza

        localizationManager= new LocalizationManager();
        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(scene);
        stage.setTitle("DeliverBoo");
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));// Carica l'immagine dell'icona dall'URL relativo nel tuo progetto
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setScene(scene);
        stage.show();

    }
    public static SceneHandler getInstance() {
        if(instance == null)
            instance = new SceneHandler();
        return instance;
    }

    private void terminateExec(Stage stage)
    {
        stage.setOnCloseRequest(e -> {
            executor.shutdownNow();
            Platform.exit();
            System.exit(0);
        });
    }

    public void setHomeInterface() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "HomeInterface.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza


        HomeController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(scene);
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();


        terminateExec(stage);
    }

    public void setSearchRestaurants() throws Exception {
        if(stage!=null) {stage.close();}

        // Carica la schermata di "Searching Progress" e visualizzala
        Platform.runLater(() -> {
            stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SearchRestaurants.fxml"));
            try {
                scene = new Scene(loader.load(), 1200, 700); //v:larghezza, v1:altezza
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            SearchRestaurantsController controller= loader.getController();
            try {
                controller.init(localizationManager, stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            changedTheme(scene);
            Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
            stage.getIcons().add(icon);
            stage.setTitle("DeliverBoo");
            stage.setScene(scene);
            stage.show();


            chargingStage.close();
            terminateExec(stage);

        });
    }

    public void setSearchingProgress() throws Exception {
        if(stage!=null) {stage.close();}

        chargingStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SearchingProgress.fxml"));
        searchScene = new Scene(loader.load(), 360, 215); //v:larghezza, v1:altezza

        SearchingProgressController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);



        changedTheme(searchScene);
        chargingStage.setTitle("DeliverBoo");
        //stage.setResizable(false);
        chargingStage.setScene(searchScene);
        chargingStage.initStyle(StageStyle.UNDECORATED);
        chargingStage.show();
        loadingVisible = true;
    }

    public void setRestaurantHome(Restaurant restaurant) throws Exception{
        secondStage= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "RestaurantHome.fxml"));
        Scene secondScene = new Scene(loader.load(), 1200, 700); //v:larghezza, v1:altezza

        RestaurantHomeController controller= loader.getController();
        controller.initialize(restaurant, stage, secondStage, localizationManager, controller);


        changedTheme(secondScene);
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        secondStage.getIcons().add(icon);
        secondStage.setTitle("DeliverBoo");
        secondStage.setScene(secondScene);
        secondStage.show();

        terminateExec(stage);
    }

    public void setRecapOrder(ListView<CartItem> cartList, String total) throws Exception {
        Stage thirdStage = new Stage();
        thirdStage.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "RecapOrder.fxml"));
        Scene secondScene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza

        thirdStage.initStyle(StageStyle.UNDECORATED);
        RecapOrderController controller= loader.getController();
        controller.init(localizationManager, cartList, total, thirdStage);

        changedTheme(secondScene);
        thirdStage.setTitle("DeliverBoo");
        thirdStage.setScene(secondScene);
        thirdStage.show();

        terminateExec(thirdStage);
    }

    public void setPayment() throws IOException {
        secondStage= new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Payment.fxml"));
        Scene secondScene = new Scene(loader.load(), 1200, 700); //v:larghezza, v1:altezza

        PaymentController controller= loader.getController();
        controller.initialize(stage, secondStage, localizationManager);


        changedTheme(secondScene);
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        secondStage.getIcons().add(icon);
        secondStage.setTitle("DeliverBoo");
        secondStage.setScene(secondScene);
        secondStage.show();

        terminateExec(secondStage);
    }

    public void setOrderDetails() throws IOException {
        hideStage(secondStage);
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "OrderDetails.fxml"));
        Scene secondScene = new Scene(loader.load(), 600, 700); //v:larghezza, v1:altezza

        OrderDetailsController controller= loader.getController();
        controller.init(localizationManager,stage, secondStage);

        changedTheme(secondScene);
        stage.setTitle("DeliverBoo");
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon); // Imposta l'icona per la finestra
        stage.setScene(secondScene);
        stage.show();

        terminateExec(stage);
    }

    public void setOrderConfirmed() throws IOException {
        closeStage(stage);
        closeStage(secondStage);
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "OrderConfirmed.fxml"));
        scene = new Scene(loader.load(), 700, 700); //v:larghezza, v1:altezza

        OrderConfirmedController controller= loader.getController();
        controller.init(localizationManager);

        changedTheme(scene);
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon);
        stage.setTitle("DeliverBoo");
        stage.setScene(scene);
        stage.show();
    }





    public void setLogIn() throws Exception {
        if(logInOrSignUpStage!=null){logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "LogIn.fxml"));
        logInScene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza


        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL);


        LogInController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(logInScene);
        logInOrSignUpStage.setTitle("DeliverBoo");

        //Nelle seguenti due righe si fa il cambio dell'icona nella barra sopra la finestra
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }



    public void setSignUp() throws Exception {
        if(logInOrSignUpStage!=null){logInOrSignUpStage.close();}
        logInOrSignUpStage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "SignUp.fxml"));
        logInScene = new Scene(loader.load(), 600, 500); //v:larghezza, v1:altezza

        logInOrSignUpStage.initModality(Modality.APPLICATION_MODAL);


        SignUpController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(logInScene);
        logInOrSignUpStage.setTitle("DeliverBoo");

        //Nelle seguenti due righe si fa il cambio dell'icona nella barra sopra la finestra
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        logInOrSignUpStage.getIcons().add(icon); // Imposta l'icona per la finestra

        logInOrSignUpStage.setScene(logInScene);
        logInOrSignUpStage.setResizable(false);
        logInOrSignUpStage.show();
    }



    public void setProfile() throws Exception {
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Profile.fxml"));
        logInScene = new Scene(loader.load(), 500, 700); //v:larghezza, v1:altezza


        ProfileController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(logInScene);
        stage.setTitle("DeliverBoo");

        //Nelle seguenti due righe si fa il cambio dell'icona nella barra sopra la finestra
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon); // Imposta l'icona per la finestra

        stage.setScene(logInScene);
        stage.show();
    }

    public Stage returnStage()
    {
        return stage;
    }
    public void setPasswordConfirmation() throws Exception {
        if (stage != null) stage.close();
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "Password.fxml"));
        logInScene = new Scene(loader.load(), 600, 400); //v:larghezza, v1:altezza


        PasswordController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(logInScene);
        stage.setTitle("DeliverBoo");

        //Nelle seguenti due righe si fa il cambio dell'icona nella barra sopra la finestra
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon); // Imposta l'icona per la finestra

        stage.setScene(logInScene);
        stage.show();
    }

    public void setFaq() throws Exception {
        if(stage!=null) {stage.close();}
        stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(FXML_PATH + "FAQ.fxml"));
        scene = new Scene(loader.load(), 900, 700); //v:larghezza, v1:altezza


        FAQController controller= loader.getController();
        controller.setLocalizationManager(localizationManager);

        changedTheme(scene);
        stage.setTitle("DeliverBoo");

        //Nelle seguenti due righe si fa il cambio dell'icona nella barra sopra la finestra
        Image icon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        stage.getIcons().add(icon); // Imposta l'icona per la finestra

        stage.setScene(scene);
        stage.show();


    }

    public void closeStage(Stage myStage)
    {
        myStage.close();
    }

    public void hideStage(Stage myStage){myStage.hide();}

    public void showStage(Stage myStage){myStage.show();}

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

    private void changedTheme(Scene scene) {
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
        changedTheme(scene);
    }

    public void changeFont(String newFont) {
        font= newFont;
        changedTheme(scene);
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

        /*
        dÃ  nullpointerException
        Image stageIcon = new Image(getClass().getResourceAsStream("/Icone/Ghost.png"));
        Stage stage = (Stage) alertError.getDialogPane().getScene().getWindow();
        stage.getIcons().add(stageIcon);*/

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

    public boolean showConfirmationCart(String message, String title) {
        ImageView icon = new ImageView(new Image(Objects.requireNonNull(getClass().getResource("/Icone/cart.png").toExternalForm())));
        icon.setFitWidth(90);
        icon.setFitHeight(60);
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
