package uid.project.deliverboo.model;

public class CurrentUser
{
    private String nomeUtente;
    private String email;

    public CurrentUser(String nomeUtente, String email)
    {
        this.nomeUtente = nomeUtente;
        this.email= email;
    }

    public String getNomeUtente() {return nomeUtente;}

    public void setNomeUtente(String nomeUtente) {this.nomeUtente = nomeUtente;}
    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}
}
