package uid.project.deliverboo.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;
import uid.project.deliverboo.view.SceneHandler;

public class AddressVerifier {
    private static String formattedAddress ;
    private static double latitude;
    private static double longitude;
    private static AddressVerifier instance = null;
    private AddressVerifier (){}
    public static AddressVerifier getInstance() {
        if(instance == null)
            instance = new AddressVerifier();
        return instance;
    }
    public static boolean validAddress(String address) {
        String nominatimEndpoint = "https://nominatim.openstreetmap.org/search?q=" + address + "&format=json";

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

                JSONArray jsonArray = new JSONArray(response.toString());
                if (jsonArray.length() > 0) { //se l'array non è vuoto
                    JSONObject result = jsonArray.getJSONObject(0); //prende il JSONObject
                    formattedAddress = result.getString("display_name"); //oltre a lat lon e display_name si può avere place_id, country, state, city, license, class e type (Classificazione e tipo del luogo)
                    latitude = result.getDouble("lat");
                    longitude = result.getDouble("lon");
                    return true;
                } else {
                    return false;
                }
            } else {
                System.out.println("HTTP Request Failed with error code: " + responseCode); //restituisce il codice di errore
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static double getLatitude() {
        return latitude;
    }
    public static double getLongitude() {
        return longitude;
    }
    public static String getFormattedAddress() {
        return formattedAddress;
    }
}