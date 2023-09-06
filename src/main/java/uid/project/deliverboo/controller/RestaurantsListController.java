package uid.project.deliverboo.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import uid.project.deliverboo.model.AddressVerifier;
import uid.project.deliverboo.model.ExecutorProvider;
import uid.project.deliverboo.model.Restaurant;
import uid.project.deliverboo.model.TaskCreator;
import uid.project.deliverboo.view.MenuItem;
import uid.project.deliverboo.view.RestaurantItem;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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





    public void createList(List<Integer> queryResults) throws IOException {
        System.out.println("Controller 1");
        clearRestaurants();
        System.out.println("Controller 2");
        for (Integer element : queryResults) {
            System.out.println("Controller 3");

            Callable<Boolean> verifyCallable = TaskCreator.ReturnRestInfoTask(element);
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
            System.out.println("Controller 4");


        }

        for (Restaurant restaurant : restaurants) {
            System.out.println("Restaurant: " + restaurant.getName()); // Stampa il nome del ristorante o un altro attributo rilevante
        }

        restaurantsListView.getItems().clear();

        System.out.println("Controller 5");
        for (Restaurant restaurant : restaurants) {
            System.out.println("Controller 6");
            RestaurantItem restaurantItem = new RestaurantItem(restaurant);
            System.out.println("Controller 7");
            if (restaurantsListView.getItems().add(restaurantItem)) {
                System.out.println("Aggiunto alla lista view");
                System.out.println("Contenuto della ListView:");
                for (RestaurantItem item : restaurantsListView.getItems()) {
                    System.out.println(item);
                }
                restaurantItem.prefWidthProperty().bind(restaurantsListView.widthProperty().subtract(40.0));
                System.out.println("Controller 8");
            }

            restaurantsListView.refresh();
            System.out.println("Numero di elementi dopo il refresh: " + restaurantsListView.getItems().size());

        }

        restaurantsListView.setOnMousePressed(event -> {
            RestaurantItem restaurantItem=restaurantsListView.getSelectionModel().getSelectedItem();
            System.out.println(restaurantItem);
            if(restaurantItem!= null){
                try {
                    Restaurant restaurant= restaurantItem.getRestaurant();
                    System.out.println(restaurant.getName());
                    SceneHandler.getInstance().setRestaurantHome(restaurant);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }


    public static boolean addToVector(Restaurant rest) {
        System.out.println("Aggiunge al vector");
        return restaurants.add(rest);
    }

    public static boolean clearRestaurants() {
        restaurants.clear();
        return true;
    }
}
