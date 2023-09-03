package uid.project.deliverboo.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Callable;

class ReturnFoodInfoCallable implements Callable<Boolean> {

    private int code;

    public ReturnFoodInfoCallable(int code) {
        this.code = code;
    }

    @Override
    public Boolean call() throws Exception {

        String query = "SELECT nomeIta, nomeEng, descrizioneIta, decrizioneEng, pathImm, prezzo FROM menu WHERE codiceRest LIKE ?";



        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query.toString())) {
            preparedStatement.setInt(1, code);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Food food = new Food();
                    food.setItName(resultSet.getString("nomeIta"));
                    food.setEngName(resultSet.getString("nomeEng"));
                    food.setItDescr(resultSet.getString("descrizioneIta"));
                    food.setEngDescr(resultSet.getString("descrizioneEng"));
                    food.setPath(resultSet.getString("pathImm"));
                    food.setPrice(resultSet.getDouble("prezzo"));

/*
                    hjbswjbdhjbdhjbdhjbds


                    if (SearchReasturantsController.addToVector(food))
                        return true; */
                }
            }
        }
        return false;

    }

}