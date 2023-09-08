package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.RestaurantItem;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class RestaurantsListController {

    @FXML
    private ListView<RestaurantItem> restaurantsListView;

    private static Vector<Restaurant> restaurants = new Vector<>();
    private ExecutorService executor = ExecutorProvider.getExecutor();

    private Label listEmpty= new Label();

    @FXML
    private AnchorPane listPane;

    private AnchorPane anchorPane;

    private Double newVal;


    public void createList(List<Integer> queryResults, LocalizationManager localizationManager, AnchorPane anchorPane) throws IOException {
        this.anchorPane=anchorPane;
       anchorPane.widthProperty().addListener((observable, oldValue, newValue) ->{
            listPane.setPrefWidth(newValue.doubleValue());

        });

        anchorPane.heightProperty().addListener((observable, oldValue, newValue) -> {

            listPane.setPrefHeight(newValue.doubleValue());
        });

        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            listEmpty.setText("Nessun ristorante trovato");
        }else{
            listEmpty.setText("Restaurants not found.");
        }

        for (Integer element : queryResults) {


            Callable<Boolean> verifyCallable = TaskCreator.ReturnRestInfoTask(element);
            Future<Boolean> result = executor.submit(verifyCallable);
            try {

                Boolean flag = result.get();
            } catch (InterruptedException e) {

                throw new RuntimeException(e);
            } catch (ExecutionException e) {

                throw new RuntimeException(e);
            }

        }

        restaurantsListView.getItems().clear();

        for (Restaurant restaurant : restaurants) {

            RestaurantItem restaurantItem = new RestaurantItem(restaurant);

            restaurantsListView.getItems().add(restaurantItem);


            restaurantsListView.refresh();


        }

        restaurantsListView.setPlaceholder(listEmpty);



        restaurantsListView.setOnMousePressed(event -> {
            RestaurantItem restaurantItem=restaurantsListView.getSelectionModel().getSelectedItem();
            if(restaurantItem!= null){
                try {
                    Restaurant restaurant= restaurantItem.getRestaurant();
                    SceneHandler.getInstance().setRestaurantHome(restaurant);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    public static boolean addToVector(Restaurant rest) {

        return restaurants.add(rest);
    }

    public static boolean clearRestaurants() {
        restaurants.clear();
        return true;
    }

    public void refersh(Double newVal){

        listPane.setPrefWidth(newVal-280);


    }


}
