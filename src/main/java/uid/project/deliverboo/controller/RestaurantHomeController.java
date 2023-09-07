package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.Food;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.CartItem;
import uid.project.deliverboo.view.MenuItem;

import java.io.IOException;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;


public class RestaurantHomeController {

    @FXML
    private Button backButton;

    @FXML
    private ImageView backButtonImg;

    @FXML
    private AnchorPane cartSpace;

    @FXML
    private ImageView imageRestaurant;

    @FXML
    private ListView<MenuItem> menuList;

    @FXML
    private Label restaurantNameLabel;

    private Stage searchRestaurant;

    private Stage ownStage;

    private static Vector<Food> menu = new Vector<>();
    private ExecutorService executor = ExecutorProvider.getExecutor();

    @FXML
    private ListView<CartItem> cartList;

    @FXML
    private Label totalLabel;

    private double tot;

    @FXML
    private Button paymentButton;

    private Label cartEmpty= new Label();

    private Label total= new Label();

    public void initialize(Restaurant restaurant, Stage stage, Stage secondStage, LocalizationManager localizationManager) throws IOException {
        this.searchRestaurant=stage;
        this.ownStage=secondStage;
        tot=0.0;
        stage.hide();
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            cartEmpty.setText("Carrello vuoto");
            total.setText("Totale: ");
            paymentButton.setText("Vai al pagamento");
        }else{
            cartEmpty.setText("Cart empty");
            total.setText("Total: ");
            paymentButton.setText("Go to payment");
        }
        totalLabel.setText(total.getText()+tot);


        cartList.setPlaceholder(cartEmpty);
        restaurantNameLabel.setText(restaurant.getName());
        menu.clear();

        //imageRestaurant.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath2())).toExternalForm()));

        Callable<Boolean> verifyCallable = TaskCreator.ReturnFoodInfoCallable(restaurant.getCode());

        Future<Boolean> result = executor.submit(verifyCallable);
        try {

            Boolean flag = result.get();
        } catch (InterruptedException e) {

            throw new RuntimeException(e);
        } catch (ExecutionException e) {

            throw new RuntimeException(e);
        }



        menuList.getItems().clear();

        for(Food food: menu){

            MenuItem menuItem= new MenuItem(food, localizationManager);

            menuList.getItems().add(menuItem);

        }

        menuList.refresh();




    }

    public void getBack(){
        //qua va aggiunto l'if se la list del cart è vuoto
        ownStage.close();
        searchRestaurant.show();
    }

    public static boolean addToVector(Food food) {

        return menu.add(food);
    }

}


