package uid.project.deliverboo.model;

public class CurrentUser
{
    private static  CurrentUser instance;
    private String nomeUtente;
    private String email;
    private boolean access = false;

    private CurrentUser() {}

    public String getNomeUtente() {return nomeUtente;}

    public void setNomeUtente(String nomeUtente) {this.nomeUtente = nomeUtente;}
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public boolean getAccess() {
        if (nomeUtente != null && email != null) return true;
        else return false;
    }

    public void setAccess(boolean value) {this.access = value;}

    public static CurrentUser getInstance() {
        if (instance == null)
            instance = new CurrentUser();
        return instance;
    }

    public void logOut ()
    {
        this.email = null;
        this.nomeUtente = null;
        this.access= false; }
}
