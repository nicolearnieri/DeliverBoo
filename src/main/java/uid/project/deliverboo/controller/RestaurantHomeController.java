package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.Food;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.Cart;
import uid.project.deliverboo.view.MenuItem;
import uid.project.deliverboo.view.SceneHandler;

import java.util.Objects;
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

    private Cart cart;

    private Stage searchRestaurant;

    private Stage ownStage;

    private static Vector<Food> menu = new Vector<>();
    private ExecutorService executor = ExecutorProvider.getExecutor();

    public void initialize(Restaurant restaurant, Stage stage, Stage secondStage, LocalizationManager localizationManager){
        this.searchRestaurant=stage;
        this.ownStage=secondStage;
        cart=new Cart();
        cart.loadCart();
        stage.hide();
        restaurantNameLabel.setText(restaurant.getName());

        System.out.println("pos1");
        //imageRestaurant.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath2())).toExternalForm()));

        Callable<Boolean> verifyCallable = TaskCreator.ReturnFoodInfoCallable(restaurant.getCode());

        Future<Boolean> result = executor.submit(verifyCallable);
        try {
            System.out.println("Controller try");
            Boolean flag = result.get();
        } catch (InterruptedException e) {
            System.out.println("Controller catch1");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            System.out.println("Controller catch2");
            throw new RuntimeException(e);
        }


        System.out.println("prima di creare il menu");
        menuList.getItems().clear();
        for(Food food: menu){
            System.out.println("crea menuItem");
            MenuItem menuItem= new MenuItem(food, localizationManager);
            System.out.println("aggiunge a menuList");
            menuList.getItems().add(menuItem);
            for(MenuItem menuIt: menuList.getItems()){
                System.out.println(menuIt);
            }
        }

        menuList.refresh();




    }

    public void getBack(){
        //qua va aggiunto l'if se la list del cart è vuoto
        ownStage.close();
        searchRestaurant.show();
    }

    public static boolean addToVector(Food food) {
        System.out.println("Aggiunge al menu");
        for(Food cibo: menu){
            System.out.println(cibo);
        }
        return menu.add(food);
    }

}


