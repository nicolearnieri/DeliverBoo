package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;


import java.util.List;
import java.util.Vector;
import java.util.concurrent.Callable;

class SearchByTypeTask implements Callable<List<Integer>> {
    private String type;
    private List<Integer> prevResults;

    public SearchByTypeTask(String type, List<Integer> prevResults) {
        this.type = type;
        this.prevResults = prevResults;
    }

    @Override
    public List<Integer> call() throws Exception {
        Vector<Integer> queryResults = new Vector<Integer>();
        StringBuilder query = new StringBuilder("SELECT codice FROM Ristoranti WHERE tipologia LIKE ? and codice IN (");
        for (int i = 0; i < prevResults.size(); i++) {
            query.append(i == 0 ? "?" : ", ?");
        }
        query.append(")");

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            preparedStatement.setString(1, type);
            for (int i = 0; i < prevResults.size(); i++) {
                preparedStatement.setInt(i + 2, prevResults.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    queryResults.add(resultSet.getInt("codice"));
                }
            }
            return queryResults;
        }
    }
}


class SearchByNameTask implements Callable<List<Integer>> {
    private String name;
    private List<Integer> prevResults;

    public SearchByNameTask(String name, List<Integer> prevResults) {
        this.name = name;
        this.prevResults = prevResults;
    }

    @Override
    public List<Integer> call() throws Exception {
        Vector<Integer> queryResults = new Vector<Integer>();
        StringBuilder query = new StringBuilder("SELECT codice FROM Ristoranti WHERE nome LIKE ? and codice IN (");
        for (int i = 0; i < prevResults.size(); i++) {
            query.append(i == 0 ? "?" : ", ?");
        }
        query.append(")");

        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            preparedStatement.setString(1, name);
            for (int i = 0; i < prevResults.size(); i++) {
                preparedStatement.setInt(i + 2, prevResults.get(i));
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    queryResults.add(resultSet.getInt("codice"));
                }
            }
            return queryResults;
        }
    }
}

class SearchByAddressTask implements Callable<List<Integer>> {
    private String address;


    public SearchByAddressTask(String address) {
        this.address = address;
    }

    @Override
    public List<Integer> call() throws Exception {
        Vector<Integer> queryResults = new Vector<Integer>();
        String query = "SELECT codice FROM Ristoranti WHERE indirizzo LIKE ?";


        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            preparedStatement.setString(1, address);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    queryResults.add(resultSet.getInt("codice"));
                }
            }
            return queryResults;
        }
    }
}

class ReturnAddressTask implements Callable<List<Integer>> {


    private String addressToCheck;

    public ReturnAddressTask(String addressToCheck) {
        this.addressToCheck = addressToCheck; 
    }

    @Override
    public List<Integer> call() throws Exception {
        Vector<Integer> queryResults = new Vector<Integer>();
        String query ="SELECT indirizzo, codice FROM Ristoranti";


        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String indirizzo = resultSet.getString("indirizzo");
                    if (AddressVerifier.getDistance(addressToCheck, indirizzo)<=10)
                        queryResults.add(resultSet.getInt("codice"));
                }
            }
            return queryResults;
        }
    }



    }