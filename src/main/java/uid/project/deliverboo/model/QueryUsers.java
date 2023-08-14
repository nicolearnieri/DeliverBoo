package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static uid.project.deliverboo.model.DataBaseManager.getConnection;

public class QueryUsers {
    private static DataBaseManager databaseManager;

    private QueryUsers(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }


    public static boolean insertUser(String username, String nome, String cognome, String email, String password, String indirizzo, String numeroTelefono) {
        try {
            PreparedStatement insertQuery = getConnection().prepareStatement("INSERT INTO utenti (nomeUtente, nome, cognome, email, password, indirizzoPredefinito, numeroTelefonico) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertQuery.setString(1, username);
            insertQuery.setString(2, nome);
            insertQuery.setString(3, cognome);
            insertQuery.setString(4, email);
            insertQuery.setString(5, password);
            insertQuery.setString(6, indirizzo);
            insertQuery.setString(7, numeroTelefono);

            int rowsAffected = insertQuery.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            return false;
        } finally {
            DataBaseManager.closeConnection();
        }

    }

    public static boolean deleteUser(int userId) {
        String query = "DELETE FROM utenti WHERE nomeUtente = ?";

        try {
            PreparedStatement deleteQuery = getConnection().prepareStatement(query);
            deleteQuery.setInt(1, userId);

            int rowsAffected = deleteQuery.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
    }


    //teoricamente non serve!! help
    public static boolean searchUserByEmailOrUsername(String searchTerm) {
        String query = "SELECT COUNT(*) FROM utenti WHERE email LIKE ? OR nomeUtente LIKE ?";
        boolean isEmail = ValidatorUtility.isValidEmail(searchTerm); // Controlla se il termine di ricerca sembra un'email

        try {
            PreparedStatement selectQuery = getConnection().prepareStatement(query);
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
        } finally {
            DataBaseManager.closeConnection();
        }
    }

    public static boolean usernameNotExists(String username) {
        String query = "SELECT COUNT(*) FROM utenti WHERE nomeUtente = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }

        return true;
    }


    public static boolean emailNotExists(String email) {
        String query = "SELECT COUNT(*) FROM utenti WHERE email = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }

        return true;
    }

    public static String getPassword(String param) {
        String query = "SELECT password FROM utenti WHERE nomeUtente = ? or email = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("password");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
        return "";
    }

    public static boolean updateOnUser(String user, String name, String surname, String phone, String address )
    {
        String query = "UPDATE utenti SET nome = ?, cognome = ?, numeroTelefonico = ?, indirizzoPredefinito = ? WHERE nomeUtente = ?";
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, address);
            preparedStatement.setString(5, user);
            preparedStatement.executeUpdate();
            return true;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }

    }

    public static String getUsername (String email) {
        String query = "SELECT nomeUtente FROM utenti WHERE email = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("nomeUtente");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
        return "";
    }

    public static String getEmail (String username) {
        String query = "SELECT email FROM utenti WHERE nomeUtente = ?";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("email");
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
        return "";
    }
}



