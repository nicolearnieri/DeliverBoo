package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.Objects;

public class RestaurantItemController {

    @FXML
    private Label evaluationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private ImageView restaurantImage;
    //qui si potrà usare HomeController.getAddress() per prendere l'indirizzo dell'utente e calcolare la distanza dal ristorante

    public void init(Restaurant restaurant) throws IOException {
        nameLabel.setText(restaurant.getName());
        evaluationLabel.setText(restaurant.getEvaluation());
        cityLabel.setText(restaurant.getCity());
        restaurantImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath1())).toExternalForm()));

//        restaurantImage.setImage(new Image(Objects.requireNonNull(getClass().getResource("/usedImages/Restaurants/bakery12.jpg")).toExternalForm()));
    }

    public static void setLightMode() {
        SceneHandler.getInstance().changeTheme("LightTheme");

    }

    public static void setParadiseTheme() {SceneHandler.getInstance().changeTheme("ParadiseTheme");

    }

    public static void setDeliverBooTheme() {SceneHandler.getInstance().changeTheme("DeliverBooTheme");

    }

    public static void setDarkTheme() {SceneHandler.getInstance().changeTheme("DarkTheme");

    }

    public static void setObsidianTheme() {SceneHandler.getInstance().changeTheme("ObsidianTheme");

    }
    public static void setFontDyslexia() {SceneHandler.getInstance().changeFont("FontDyslexic");

    }
    public static void setFontMontserrat() {SceneHandler.getInstance().changeFont("FontMontserrat");

    }
}
