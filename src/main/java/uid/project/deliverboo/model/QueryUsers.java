package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryUsers {
    private DataBaseManager databaseManager;

    public QueryUsers(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    public boolean insertUser(String username, String nome, String cognome, String email, String password, String indirizzo, String numeroTelefono) {
        try {
            PreparedStatement insertQuery = DataBaseManager.getConnection().prepareStatement("INSERT INTO utenti (username, nome, cognome, email, password, indirizzo, numero_telefono) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertQuery.setString(1, username);
            insertQuery.setString(2, nome);
            insertQuery.setString(3, cognome);
            insertQuery.setString(4, email);
            insertQuery.setString(5, password);
            insertQuery.setString(6, indirizzo);
            insertQuery.setString(7, numeroTelefono);
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
