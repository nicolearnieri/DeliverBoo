package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.util.Pair;
import uid.project.deliverboo.model.Food;
import uid.project.deliverboo.view.CartItem;
import uid.project.deliverboo.view.RecapItem;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Vector;

public class RecapOrderController {

    @FXML
    private Button backButton;


    @FXML
    private Button proceedButton;

    @FXML
    private Label recapLabel;

    @FXML
    private ListView<RecapItem> recapListView;

    @FXML
    private Label totalLabel;

    private Stage stage




    public void init(LocalizationManager localizationManager, ListView<CartItem> cartList, String total, Stage stage) throws IOException {
        this.stage=stage;
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            recapLabel.setText("Riepilogo ordine");
            proceedButton.setText("Avanti");
        }else{
            recapLabel.setText("Order summary");
            proceedButton.setText("Proceed");
        }

        totalLabel.setText(total);

        for(CartItem cartItem: cartList.getItems()){
            Food food= cartItem.getFood();
            int quantity= cartItem.getQuantity();
            RecapItem recapItem= new RecapItem(food, localizationManager, quantity);
            recapListView.getItems().add(recapItem);
        }





    }

    public void goBack(){
        recapListView.getItems().clear();
        SceneHandler.getInstance().closeStage(stage);
    }


}
