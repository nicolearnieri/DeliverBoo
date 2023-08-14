package uid.project.deliverboo.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QuerySearch {
    private static DataBaseManager databaseManager;

    private QuerySearch(DataBaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
}
