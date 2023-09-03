package uid.project.deliverboo.model;
import javafx.concurrent.Task;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.concurrent.Callable;

import static uid.project.deliverboo.model.DataBaseManager.getConnection;

class InsertUserTask implements Callable<Boolean> {
    private String username, nome, cognome, email, password, indirizzo, numeroTelefono;

    public InsertUserTask(String username, String nome, String cognome, String email, String password, String indirizzo, String numeroTelefono) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.indirizzo = indirizzo;
        this.numeroTelefono = numeroTelefono;
    }

    @Override
    public Boolean call() throws Exception {
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
            System.out.println("sto eseguendo");
            return rowsAffected > 0;

        } catch (Exception e) {
            System.err.println("Errore durante l'inserimento dell'utente: " + e.getMessage());
            return false;
        } finally {
            DataBaseManager.closeConnection();
        }
    }
}

class DeleteUserTask implements Callable<Boolean> {
    private String userId;

    public DeleteUserTask(String userId) {
        this.userId = userId;
    }

    @Override
    public Boolean call() throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement deleteQuery = conn.prepareStatement("DELETE FROM utenti WHERE nomeUtente = ?")) {
            deleteQuery.setString(1, userId);

            int rowsAffected = deleteQuery.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
    }
}



class UsernameNotExistsTask implements Callable<Boolean> {
    private String username;

    public UsernameNotExistsTask(String username) {
        this.username = username;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT COUNT(*) FROM utenti WHERE nomeUtente = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
            finally {
                DataBaseManager.closeConnection();
            }
        }
        return true;
    }
}
class EmailNotExistsTask implements Callable<Boolean> {
    private String email;

    public EmailNotExistsTask(String email) {
        this.email = email;
    }

    @Override
    public Boolean call() throws Exception {
        String query = "SELECT COUNT(*) FROM utenti WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 0;
                }
            }
        }
        return true;
    }
}


class GetPasswordTask implements Callable<String> {
    private String param;

    public GetPasswordTask(String param) {
        this.param = param;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT password FROM utenti WHERE nomeUtente = ? or email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, param);
            preparedStatement.setString(2, param);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        }
        return "";
    }
}
class GetUsernameTask implements Callable<String>  {
    private String email;

    public GetUsernameTask(String email) {
        this.email = email;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT nomeUtente FROM utenti WHERE email = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("nomeUtente");
                }
            } finally {
                DataBaseManager.closeConnection();
            }
            return "";
        }
    }
}
class GetEmailTask implements Callable<String>  {
    private String username;

    public GetEmailTask(String username) {
        this.username = username;
    }

    @Override
    public String call() throws Exception {
        String query = "SELECT email FROM utenti WHERE nomeUtente = ?";
        try (Connection conn = getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("email");
                }
            } finally {
                DataBaseManager.closeConnection();
            }
            return "";
        }
    }
}



 class UpdateOnUserTask implements Callable<Boolean>  {
     private String user, name, surname, phone, address;

     public UpdateOnUserTask(String user, String name, String surname, String phone, String address) {
         this.user = user;
         this.name = name;
         this.surname = surname;
         this.phone = phone;
         this.address = address;
     }

     @Override
     public Boolean call() throws Exception {
         String query = "UPDATE utenti SET nome = ?, cognome = ?, numeroTelefonico = ?, indirizzoPredefinito = ? WHERE nomeUtente = ?";
         try (Connection conn = getConnection();
              PreparedStatement preparedStatement = conn.prepareStatement(query)) {
             preparedStatement.setString(1, name);
             preparedStatement.setString(2, surname);
             preparedStatement.setString(3, phone);
             preparedStatement.setString(4, address);
             preparedStatement.setString(5, user);
             preparedStatement.executeUpdate();
             return true;
         } finally {
             DataBaseManager.closeConnection();
         }

     }
 }


