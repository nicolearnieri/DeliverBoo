package uid.project.deliverboo.model;



import java.util.List;
import java.util.concurrent.Callable;

public class TaskCreator
{
    public static Callable<Boolean> createInsertUser(String username, String nome, String cognome, String email, String password, String indirizzo, String numeroTelefono)
    {
        InsertUserTask task = new InsertUserTask(username,nome,cognome, email, password, indirizzo, numeroTelefono);
        return task;
    }

    public static Callable<Boolean> createDeleteUser (String userId)
    {
        DeleteUserTask task = new DeleteUserTask(userId);
        return task;
    }

    public static Callable<Boolean> createUsernameNotExists (String username)
    {
        UsernameNotExistsTask task = new UsernameNotExistsTask(username);
        return task;
    }

    public static  Callable<Boolean> createEmailNotExists (String email)
    {
        EmailNotExistsTask task = new EmailNotExistsTask(email);
        return task;
    }

    public static  Callable<String> createGetPassword (String param)
    {
        GetPasswordTask task = new GetPasswordTask(param);
        return task;
    }

    public static  Callable<String> createGetUsername (String email)
    {
        GetUsernameTask task = new GetUsernameTask(email);
        return task;
    }

    public static  Callable<String> createGetEmail (String username)
    {
        GetEmailTask task = new GetEmailTask(username);
        return task;
    }

    public static  Callable<Boolean> createUpdateOnUser(String username, String nome, String cognome, String indirizzo, String numeroTelefono)
    {
        UpdateOnUserTask task = new UpdateOnUserTask(username,nome,cognome, numeroTelefono, indirizzo);
        return task;
    }

    public static  Callable<List<Integer>> CreateSearchByType (String type, List<Integer> prevResults)
    {
        SearchByTypeTask task = new SearchByTypeTask(type, prevResults);
        return task;
    }

    public static  Callable<List<Integer>> CreateSearchByName (String name, List<Integer> prevResults)
    {
        SearchByNameTask task = new SearchByNameTask(name, prevResults);
        return task;
    }

    public static  Callable<List<Integer>> CreateSearchByCity (String city)
    {
        SearchByAddressTask task = new SearchByAddressTask(city);
        return task;
    }
public static  Callable<List<Integer>> ReturnAddressTask (String addressToCheck)
    {
        ReturnAddressTask task = new ReturnAddressTask(addressToCheck);
        return task;
    }

public static Callable<Boolean> ReturnRestInfoTask (int code)
    {
        ReturnRestInfoTask task = new ReturnRestInfoTask(code);
        return task;
    }

}

