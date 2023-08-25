package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class RestaurantItemController {

    @FXML
    private Label evaluationLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label openingTimeLabel;

    @FXML
    private ImageView restaurantImage;
    //qui si potrà usare HomeController.getAddress() per prendere l'indirizzo dell'utente e calcolare la distanza dal ristorante
}
