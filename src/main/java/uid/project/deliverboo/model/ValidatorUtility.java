package uid.project.deliverboo.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtility {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Z])(?=.*\\d)(?=.*[$!€#*@?^%&]).{8,}$";
    private static final Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN); //crea una pattern a partire dalla stringa con la regex

    // Pattern per gli indirizzi email
    private static final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);

    // Metodo per verificare se una password è valida
    public static boolean isValidPassword(String password) {
        Matcher matcher = passwordPattern.matcher(password); //creo un matcher che confronta una stringa con la regex creata
        return matcher.matches();
    }

    // Metodo per verificare se un indirizzo email è valido
    public static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

}
