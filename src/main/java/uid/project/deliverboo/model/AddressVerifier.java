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
    private static String city;
    private static final double EARTH_RADIUS_KM = 6371.0;
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

                JSONArray jsonArray = new JSONArray(response.toString());
                if (jsonArray.length() > 0) { //se l'array non è vuoto
                    JSONObject result = jsonArray.getJSONObject(0); //prende il JSONObject
                    formattedAddress = result.getString("display_name"); //oltre a lat lon e display_name si può avere place_id, country, state, city, license, class e type (Classificazione e tipo del luogo)
                    System.out.println(formattedAddress);
                    latitude = result.getDouble("lat");
                    longitude = result.getDouble("lon");
                    String[] addressParts = formattedAddress.split(", ");
                    if (addressParts.length >= 4) {
                        city = addressParts[1]; // La città dovrebbe essere il secondo elemento
                    } else {return false;}
                    System.out.println(city);
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
    public static double getLatitude(String temporaryAddress) {
        validAddress(temporaryAddress);
        return latitude;}
    public static double getLongitude(String temporaryAddress) {
        validAddress(temporaryAddress);
        return longitude;}
    public static String getFormattedAddress(String temporaryAddress) {
        validAddress(temporaryAddress);
        return formattedAddress;}

    public static String getCity(String temporaryAddress) {
        validAddress(temporaryAddress);
        return city;}

    public static double getDistance(String firstAddress, String secondAddress) {
        double lat1 = Math.toRadians(getLatitude(firstAddress));
        double lon1 = Math.toRadians(getLongitude(firstAddress));
        double lat2 = Math.toRadians(getLatitude(secondAddress));
        double lon2 = Math.toRadians(getLongitude(secondAddress));

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }
}
// cercando "via don minzoni cosenza": Via Don Minzoni, Roges, Rende, Cosenza, Calabria, 87036, Italia