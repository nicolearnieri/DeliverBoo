package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.view.SceneHandler;

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


    public void init(Restaurant restaurant) {
        nameLabel.setText(restaurant.getName());
        evaluationLabel.setText(restaurant.getEvaluation());
        cityLabel.setText(restaurant.getCity());
        restaurantImage.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath1())).toExternalForm()));

    }

}
