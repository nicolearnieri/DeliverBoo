package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.Restaurant;

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

    public void init(Restaurant restaurant){
        nameLabel.setText(restaurant.getName());
        evaluationLabel.setText(restaurant.getEvaluation());
        cityLabel.setText(restaurant.getCity());
        restaurantImage.setImage(new Image(restaurant.getPath1()));

    }
}
