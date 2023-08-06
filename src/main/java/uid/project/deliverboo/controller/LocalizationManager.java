package uid.project.deliverboo.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private ResourceBundle resourceBundle; //permette di capire in che località è situato l'user
    private Locale currentLocale; //rappresente un punto geografico

    public LocalizationManager(){
        currentLocale=Locale.ITALIAN; //località italiana di default
        loadResources(); //riassegna resourceBundle
    }

    public void setLanguage(Locale locale){
        currentLocale=locale;
        loadResources();
    }

    private void loadResources(){
        String resourceName= "strings_"+ currentLocale.getLanguage(); //di default sarà "strings_it"
        resourceBundle=ResourceBundle.getBundle(resourceName, currentLocale); //prende il bunble corrispondente alla lingua corrente
    }

    public String getLocalizedString(String key){
        return resourceBundle.getString(key);
    }

    public Locale getCurrentLocale(){
        return currentLocale;
    }
}
