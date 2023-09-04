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




    public void createList(List<Integer> queryResults) {
        restaurants.clear();
        for(Integer element: queryResults) {
            Callable<Boolean> verifyCallable = TaskCreator.ReturnRestInfoTask(element);


        }



        for(Restaurant restaurant: restaurants){
            RestaurantItem restaurantItem= new RestaurantItem(restaurant);
            restaurantsListView.getItems().add(restaurantItem);
        }

    }

    public static boolean addToVector (Restaurant rest)
    {
        return restaurants.add(rest);
    }






}
