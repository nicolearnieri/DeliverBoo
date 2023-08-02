package uid.project.deliverboo.model;
import java.sql.*;

public class DataBaseManager {

    private static final String DB_URL = "jdbc:sqlite:/src/main/resources/database/DataBase.db"; //percorso del DB
    private static Connection connection;
    //per stabilire una connessione al db:


    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DB_URL);
        return connection;
    }

    //metodo per eseguire le query
    public ResultSet executeQuery(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
        //resultSet: interfaccia che rappresenta un insieme di dati ottenuti tramite operazioni su JDBC
    }


    public int executeUpdate(String query) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connessione al database chiusa.");
            }
        } catch (SQLException e) {
            System.err.println("Errore durante la chiusura della connessione.");
            e.printStackTrace(); //fa risalire agli errori
        }
    }

}


