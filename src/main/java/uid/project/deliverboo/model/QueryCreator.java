package uid.project.deliverboo.model;



import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

public class QueryCreator
{
    public static Callable<Boolean> createInsertUser(String username, String nome, String cognome, String email, String password, String numeroTelefono)
    {
        InsertUserCallable callable = new InsertUserCallable(username,nome,cognome, email, password,numeroTelefono);
        return callable;
    }

    public static Callable<Boolean> createDeleteUser (String userId)
    {
        DeleteUserCallable callable = new DeleteUserCallable(userId);
        return callable;
    }

    public static Callable<Boolean> createUsernameNotExists (String username)
    {
        UsernameNotExistsCallable callable = new UsernameNotExistsCallable(username);
        return callable;
    }

    public static  Callable<Boolean> createEmailNotExists (String email)
    {
        EmailNotExistsCallable callable = new EmailNotExistsCallable(email);
        return callable;
    }

    public static  Callable<String> createGetPassword (String param)
    {
        GetPasswordCallable callable = new GetPasswordCallable(param);
        return callable;
    }

    public static  Callable<String> createGetUsername (String email)
    {
        GetUsernameCallable callable = new GetUsernameCallable(email);
        return callable;
    }

    public static  Callable<String> createGetEmail (String username)
    {
        GetEmailCallable callable = new GetEmailCallable(username);
        return callable;
    }

    public static  Callable<Boolean> createUpdateOnUser(String username, String nome, String cognome, String numeroTelefono)
    {
        UpdateOnUserCallable callable = new UpdateOnUserCallable(username,nome,cognome, numeroTelefono);
        return callable;
    }

    public static  Callable<List<Integer>> createSearchByType (String type, List<Integer> prevResults)
    {
        SearchByTypeCallable callable = new SearchByTypeCallable(type, prevResults);
        return callable;
    }

    public static  Callable<List<Integer>> createSearchByName (String name, List<Integer> prevResults)
    {
        SearchByNameCallable callable = new SearchByNameCallable(name, prevResults);
        return callable;
    }

public static  Callable<List<Integer>> createReturnAddressCallable (String addressToCheck)
    {
        ReturnAddressCallable callable = new ReturnAddressCallable(addressToCheck);
        return callable;
    }

public static Callable<Boolean> createReturnRestInfoCallable (int code)
    {
        ReturnRestInfoCallable callable = new ReturnRestInfoCallable(code);
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

