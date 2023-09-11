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
import uid.project.deliverboo.view.CartItem;
import uid.project.deliverboo.view.MenuItem;
import uid.project.deliverboo.view.SceneHandler;

import java.io.IOException;
import java.util.*;
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

    private Vector<Food> foodInCart= new Vector<>();
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

    private LocalizationManager localizationManager;

    private RestaurantHomeController controller;

    private int cont;

    private List<Integer> listQuantity=new ArrayList<>();

    public void initialize(Restaurant restaurant, Stage stage, Stage secondStage, LocalizationManager localizationManager, RestaurantHomeController controller) throws IOException {
        this.searchRestaurant=stage;
        this.ownStage=secondStage;
        this.localizationManager=localizationManager;
        this.controller=controller;

        tot=0.0;
        SceneHandler.getInstance().hideStage(searchRestaurant);
        if(localizationManager.getCurrentLocale().equals(Locale.ITALIAN)){
            cartEmpty.setText("Carrello vuoto");
            total.setText("Totale: ");
            paymentButton.setText("Vai al pagamento");
        }else{
            cartEmpty.setText("Cart empty");
            total.setText("Total: ");
            paymentButton.setText("Go to payment");
        }
        totalLabel.setText(total.getText()+tot+"€");


        cartList.setPlaceholder(cartEmpty);
        restaurantNameLabel.setText(restaurant.getName());

        menu.clear();

        imageRestaurant.setImage(new Image(Objects.requireNonNull(getClass().getResource(restaurant.getPath2())).toExternalForm()));

        Callable<Boolean> verifyCallable = TaskCreator.createReturnFoodInfoCallable(restaurant.getCode());

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

            MenuItem menuItem= new MenuItem(food, localizationManager, controller);

            menuList.getItems().add(menuItem);

        }

        menuList.refresh();




    }

    public void getBack(){
        //qua va aggiunto l'if se la list del cart è vuoto
        if(cartList.getItems().isEmpty()==false){
            Boolean flag= SceneHandler.getInstance().showConfirmationCart(localizationManager.getLocalizedString("cartInfo.text"), localizationManager.getLocalizedString("cartInfo.title"));
            if(flag){
                SceneHandler.getInstance().closeStage(ownStage);
                cartList.getItems().clear();

                SceneHandler.getInstance().showStage(searchRestaurant);
            }
        }else{
            SceneHandler.getInstance().closeStage(ownStage);

            SceneHandler.getInstance().showStage(searchRestaurant);
        }

    }

    public static boolean addToVector(Food food) {

        return menu.add(food);
    }

    public void ownAddFoodInCart(Food f) throws IOException {
        if(foodInCart.contains(f)) {
            for (CartItem cartItem : cartList.getItems()) {
                CartItemController controller = cartItem.returnCotroller();
                controller.addFood(f);

            }
        }else{

              CartItem cartItem=new CartItem(f, localizationManager, controller);
              cartList.getItems().add(cartItem);
              foodInCart.add(f);
            }
        tot=tot+Double.parseDouble(f.getPrice());
        totalLabel.setText(total.getText()+tot+"€");

    }

    public void addFood(Food f, int cont){
        for(MenuItem menuItem: menuList.getItems()){
            MenuItemController menuItemController=menuItem.getController();
            menuItemController.addFood(f, cont);
        }
        tot=tot+Double.parseDouble(f.getPrice());
        totalLabel.setText(total.getText()+tot+"€");
    }





    public void ownDeductFoodInCart(Food f, int cont) throws IOException {

        List<CartItem> itemsToRemove = new ArrayList<>();

        for (CartItem cartItem : cartList.getItems()) {
            Boolean flag=false;
            CartItemController controller = cartItem.returnCotroller();
            flag=controller.deductFood(f, cont);
            if (flag & cont == 0) {

                itemsToRemove.add(cartItem); // Aggiungi l'elemento da rimuovere a una lista temporanea

                foodInCart.remove(f);
            }
        }

        tot=tot-Double.parseDouble(f.getPrice());
        totalLabel.setText(total.getText()+tot+"€");


        // Rimuovi gli elementi dalla ListView
        cartList.getItems().removeAll(itemsToRemove);
        itemsToRemove.clear();
    }

    public void deductFood(Food f, int contCart, CartItem cartItem){
        List<CartItem> itemsToRemove = new ArrayList<>();
        for(MenuItem menuItem: menuList.getItems()){
            MenuItemController menuItemController=menuItem.getController();
            menuItemController.deductFood(f, contCart);
            if(contCart==0){

                itemsToRemove.add(cartItem); // Aggiungi l'elemento da rimuovere a una lista temporanea
                foodInCart.remove(f);
            }
        }
        tot=tot+Double.parseDouble(f.getPrice());
        totalLabel.setText(total.getText()+tot+"€");

        cartList.getItems().removeAll(itemsToRemove);
        itemsToRemove.clear();
    }

    public void goToPayment() throws Exception {
        if(cartList.getItems().isEmpty()){
            SceneHandler.getInstance().showInfo(localizationManager.getLocalizedString("cartEmpty.text"), "cartEmpty.title");
        }else {
            SceneHandler.getInstance().setRecapOrder(cartList, totalLabel.getText(), tot);
        }
    }





}


