package uid.project.deliverboo.model;
import java.sql.*;

public class DataBaseManager {

    private static final String DB_URL = "jdbc:sqlite:/src/main/resources/database/DataBase.db"; //percorso del DB

    //per stabilire una connessione al db:
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    //metodo per eseguire le query
    public ResultSet executeQuery(String query) throws SQLException
    {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
        //resultSet: interfaccia che rappresenta un insieme di dati ottenuti tramite operazioni su JDBC
    }

    public int executeUpdate(String query) throws SQLException
    {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);

    }
}
