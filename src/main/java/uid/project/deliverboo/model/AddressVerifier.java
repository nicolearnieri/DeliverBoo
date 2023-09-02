package uid.project.deliverboo.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import uid.project.deliverboo.controller.LocalizationManager;
import uid.project.deliverboo.view.SceneHandler;

public class AddressVerifier {
    private static JSONArray jsonArray;
    private static JSONObject jsonObject;
    private static JSONObject restaurant ;
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static LocalizationManager localizationManager = new LocalizationManager();
    private static AddressVerifier instance = null;
    private AddressVerifier (){}
    public static AddressVerifier getInstance() {
        if(instance == null)
            instance = new AddressVerifier();
        return instance;
    }
    public static boolean validAddress(String address) {
        String replacedAddress = address.replace(" ", "+");
        String nominatimEndpoint = "https://nominatim.openstreetmap.org/search?q=" + replacedAddress + "&format=json";

        try {
            URL url = new URL(nominatimEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //crea un connessione con l'endpoint
            connection.setRequestMethod("GET"); //in lettura

            int responseCode = connection.getResponseCode(); //prende il responso della connessione
            if (responseCode == HttpURLConnection.HTTP_OK) { //se la connessione è andata a buon fine (codice 200, es 404 quando manca la risorsa)
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                jsonArray = new JSONArray(response.toString());
                int correctIndex=correctAddressIndex(jsonArray);
                if (jsonArray.length() > 0 && correctIndex>=0) { //se l'array non è vuoto
                    jsonObject = jsonArray.getJSONObject(correctIndex); //prende il JSONObject
                    return true;
                } else {
                    return false;//se finisco qui l'indirizzo che cerco non è nell'array
                }
            } else {
                System.out.println("HTTP Request Failed with error code: " + responseCode); //restituisce il codice di errore
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isValid(String address) {
        String replacedAddress = address.replace(" ", "+");
        String nominatimEndpoint = "https://nominatim.openstreetmap.org/search?q=" + replacedAddress + "&format=json";

        try {
            URL url = new URL(nominatimEndpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //crea un connessione con l'endpoint
            connection.setRequestMethod("GET"); //in lettura

            int responseCode = connection.getResponseCode(); //prende il responso della connessione
            if (responseCode == HttpURLConnection.HTTP_OK) { //se la connessione è andata a buon fine (codice 200, es 404 quando manca la risorsa)
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray array = new JSONArray(response.toString());
                int correctIndex = correctAddressIndex(array);
                if (array.length() > 0 && correctIndex >= 0) { //se l'array non è vuoto
                    restaurant = array.getJSONObject(correctIndex); //prende il JSONObject
                    return true;
                } else {
                    return false;//se finisco qui l'indirizzo che cerco non è nell'array
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static double getLatitude() {
        return jsonObject.getDouble("lat");}
    public static double getLongitude() {
        return jsonObject.getDouble("lon");}
    public static String getFormattedAddress() {
        return jsonObject.getString("display_name"); //oltre a lat lon e display_name si può avere place_id, country, state, city, license, class e type (Classificazione e tipo del luogo)
        }
    private static double getRestaurantLatitude(String address) {return restaurant.getDouble("lat");}
    private static double getRestaurantLongitude(String address) {return restaurant.getDouble("lon");}
    public static String getCity(String temporaryAddress) {
        String city="";
        String[] addressParts = getFormattedAddress().split(", ");
        if (addressParts.length >= 4) {
            city = addressParts[1]; // La città dovrebbe essere il secondo elemento
        }
        return city;
    }

    public static double getDistance (String restaurantAddress) {
        double lat1 = Math.toRadians(getLatitude());
        double lon1 = Math.toRadians(getLongitude());
        double lat2 = Math.toRadians(getRestaurantLatitude(restaurantAddress));
        double lon2 = Math.toRadians(getRestaurantLongitude(restaurantAddress));

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }

    //funzione che, data jsonArray, restituisce l'indice del JSONObject giusto, altrimenti return -1
    public static int correctAddressIndex(JSONArray jsonArray) {
        for (int cont = 0; cont < jsonArray.length(); cont++) {
            JSONObject obj = jsonArray.getJSONObject(cont);//prende il JSONObject in posizione cont
            String address = obj.getString("display_name");

            String message = localizationManager.getLocalizedString("address.confirmationMessage") + address;
            String title = localizationManager.getLocalizedString("address.errorTitle");

            if (SceneHandler.getInstance().showConfirmation(message, title)) {return cont;}
        }
        return -1;
    }
}
// cercando "via don minzoni cosenza": Via Don Minzoni, Roges, Rende, Cosenza, Calabria, 87036, Italia