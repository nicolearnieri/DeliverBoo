package uid.project.deliverboo.model;



import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

public class TaskCreator
{
    public static Callable<Boolean> createInsertUser(String username, String nome, String cognome, String email, String password, String numeroTelefono)
    {
        InsertUserTask callable = new InsertUserTask(username,nome,cognome, email, password,numeroTelefono);
        return callable;
    }

    public static Callable<Boolean> createDeleteUser (String userId)
    {
        DeleteUserTask callable = new DeleteUserTask(userId);
        return callable;
    }

    public static Callable<Boolean> createUsernameNotExists (String username)
    {
        UsernameNotExistsTask callable = new UsernameNotExistsTask(username);
        return callable;
    }

    public static  Callable<Boolean> createEmailNotExists (String email)
    {
        EmailNotExistsTask callable = new EmailNotExistsTask(email);
        return callable;
    }

    public static  Callable<String> createGetPassword (String param)
    {
        GetPasswordTask callable = new GetPasswordTask(param);
        return callable;
    }

    public static  Callable<String> createGetUsername (String email)
    {
        GetUsernameTask callable = new GetUsernameTask(email);
        return callable;
    }

    public static  Callable<String> createGetEmail (String username)
    {
        GetEmailTask callable = new GetEmailTask(username);
        return callable;
    }

    public static  Callable<Boolean> createUpdateOnUser(String username, String nome, String cognome, String numeroTelefono)
    {
        UpdateOnUserTask callable = new UpdateOnUserTask(username,nome,cognome, numeroTelefono);
        return callable;
    }

    public static  Callable<List<Integer>> createSearchByType (String type, List<Integer> prevResults)
    {
        SearchByTypeCallable callable = new SearchByTypeCallable(type, prevResults);
        return callable;
    }

    public static  Callable<List<Integer>> createSearchByName (String name, List<Integer> prevResults)
    {
        SearchByNameTask callable = new SearchByNameTask(name, prevResults);
        return callable;
    }

    public static  Callable<List<Integer>> createSearchByCity (String city)
    {
        SearchByAddressTask callable = new SearchByAddressTask(city);
        return callable;
    }
public static  Callable<List<Integer>> createReturnAddressCallable (String addressToCheck)
    {
        //ReturnAddressTask task = new ReturnAddressTask(addressToCheck, listener);
        ReturnAddressTask callable = new ReturnAddressTask(addressToCheck);
        return callable;
    }

public static Callable<Boolean> createReturnRestInfoCallable (int code)
    {
        ReturnRestInfoTask callable = new ReturnRestInfoTask(code);
        return callable;
    }

public static Callable<Boolean> createReturnFoodInfoCallable (int code)
{
    ReturnFoodInfoCallable callable = new ReturnFoodInfoCallable(code);
    return callable;
}

public static Callable<Vector<String>> returnUserInfoCallable(String value)
{
    GetInfoCallable callable = new GetInfoCallable(value);
    return callable;
}

}

