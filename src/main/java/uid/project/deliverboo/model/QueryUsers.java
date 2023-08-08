package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryUsers {
    private DataBaseManager databaseManager;

    public QueryUsers(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    public boolean insertUser(String username, String nome, String cognome, String email, String password, String indirizzo, String numeroTelefono) {
        try
        {
            PreparedStatement insertQuery = DataBaseManager.getConnection().prepareStatement("INSERT INTO utenti (username, nome, cognome, email, password, indirizzo, numero_telefono) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertQuery.setString(1, username);
            insertQuery.setString(2, nome);
            insertQuery.setString(3, cognome);
            insertQuery.setString(4, email);
            insertQuery.setString(5, password);
            insertQuery.setString(6, indirizzo);
            insertQuery.setString(7, numeroTelefono);

            int rowsAffected = insertQuery.executeUpdate();
            return rowsAffected > 0;

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean deleteUser(int userId) {
        String query = "DELETE FROM utenti WHERE id = ?";

        try
        {
            PreparedStatement deleteQuery = DataBaseManager.getConnection().prepareStatement(query);
            deleteQuery.setInt(1, userId);

            int rowsAffected = deleteQuery.executeUpdate();
            return rowsAffected > 0;
        }
        catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public boolean searchUserByEmailOrUsername(String searchTerm) {
        String query = "SELECT COUNT(*) FROM utenti WHERE email LIKE ? OR username LIKE ?";
        boolean isEmail = searchTerm.contains("@"); // Controlla se il termine di ricerca sembra un'email

        try {
            PreparedStatement selectQuery = databaseManager.getConnection().prepareStatement(query);
            selectQuery.setString(1, "%" + searchTerm + "%");
            selectQuery.setString(2, "%" + searchTerm + "%");

            try (ResultSet resultSet = selectQuery.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Restituisce true se almeno un utente corrisponde alla ricerca
                }
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}




