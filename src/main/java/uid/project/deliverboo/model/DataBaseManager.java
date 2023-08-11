package uid.project.deliverboo.model;
import java.net.URL;
import java.sql.*;

public class DataBaseManager {


    static String connectionString = "jdbc:sqlite:DataBase.db" ;



    private static Connection connection;
    //per stabilire una connessione al db:


    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(connectionString);
        } catch (SQLException e) {
            System.err.println("An error occurred: " + e.getMessage());
            throw new RuntimeException(e);

        }
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

    public static void closeConnection() {
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


