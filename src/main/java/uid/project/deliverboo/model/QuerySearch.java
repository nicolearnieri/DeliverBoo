package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import static uid.project.deliverboo.model.DataBaseManager.getConnection;

public class QuerySearch {
    private static DataBaseManager databaseManager;

    private QuerySearch(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public static List<Integer> searchByType (String type, List<Integer> prevResults)
    {
        Vector<Integer> queryResults = new Vector<Integer>();
        StringBuilder query = new StringBuilder("SELECT codice FROM Ristoranti WHERE tipologia LIKE ? and codice IN (");
        for (int i = 0; i < prevResults.size(); i++) {
            query.append(i == 0 ? "?" : ", ?");  //se i è 0 appende ?, altrimenti , ?
        }
        query.append(")");

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query.toString());
            preparedStatement.setString(1, type);
            for (int i = 0; i <prevResults.size(); i++) {
                preparedStatement.setInt(i + 2, prevResults.get(i)); //tutti i ?, da 2 in poi (1 è la tipologia); imposta tutti i parametri dei codici con i valori in posizione i di prevResults
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
              queryResults.add(resultSet.getInt("codice"));
            }

            resultSet.close();
            preparedStatement.close();
            return queryResults;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
    }


    public static List<Integer> searchByName (String name, List<Integer> prevResults)
    {
        Vector<Integer> queryResults = new Vector<Integer>();
        StringBuilder query = new StringBuilder("SELECT codice FROM Ristoranti WHERE nome LIKE ? and codice IN (");
        for (int i = 0; i < prevResults.size(); i++) {
            query.append(i == 0 ? "?" : ", ?");  //se i è 0 appende ?, altrimenti , ?
        }
        query.append(")");

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query.toString());
            preparedStatement.setString(1, name);
            for (int i = 0; i <prevResults.size(); i++) {
                preparedStatement.setInt(i + 2, prevResults.get(i)); //tutti i ?, da 2 in poi (1 è la tipologia)
            }

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                queryResults.add(resultSet.getInt("codice"));
            }

            resultSet.close();
            preparedStatement.close();
            return queryResults;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }
    }
    public static List<Integer> searchByCity (String city)
    {
        Vector<Integer> queryResults = new Vector<Integer>();
        String query = "SELECT codice FROM Ristoranti WHERE citta LIKE ? ";

        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, city);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                queryResults.add(resultSet.getInt("codice"));
            }

            resultSet.close();
            preparedStatement.close();
            return queryResults;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DataBaseManager.closeConnection();
        }

    }
}

