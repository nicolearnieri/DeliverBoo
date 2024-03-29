package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import uid.project.deliverboo.model.Food;
import uid.project.deliverboo.view.CartItem;
import uid.project.deliverboo.view.RecapItem;
import uid.project.deliverboo.view.SceneHandler;
import java.io.IOException;
import java.util.Locale;


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

    private Stage stage;

    private double tot;


    public void init(LocalizationManager localizationManager, ListView<CartItem> cartList, String total, Stage ownStage, double tot) throws IOException {
        stage=ownStage;
        this.tot=tot;
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            recapLabel.setText("Riepilogo ordine");
            proceedButton.setText("Avanti");
            backButton.setText("Chiudi");
        }else{
            recapLabel.setText("Order summary");
            proceedButton.setText("Proceed");
            backButton.setText("Close");
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

    public void proceed() throws IOException {
        SceneHandler.getInstance().closeStage(stage);
        SceneHandler.getInstance().setOrderDetails(tot);
    }




}
