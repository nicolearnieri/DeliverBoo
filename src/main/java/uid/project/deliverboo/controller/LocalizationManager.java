package uid.project.deliverboo.controller;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationManager {
    private ResourceBundle resourceBundle;
    private Locale currentLocale;

    public LocalizationManager(){
        currentLocale=Locale.ITALIAN;
        loadResources();
    }

    public void setLanguage(Locale locale){
        currentLocale=locale;
        loadResources();
    }

    private void loadResources(){
        String resourceName= "strings_"+ currentLocale.getLanguage();
        resourceBundle=ResourceBundle.getBundle(resourceName, currentLocale);
    }

    public String getLocalizedString(String key){
        return resourceBundle.getString(key);
    }

    public Locale getCurrentLocale(){
        return currentLocale;
    }
}
