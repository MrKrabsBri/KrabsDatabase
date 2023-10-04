package com.krabs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryManager {

    private static DatabaseManager databaseManager;

    public static void createPersonTable(String sqlQuery){

        try (Connection connection = databaseManager.getConnection()) {

                Statement statement = connection.createStatement();
                statement.executeUpdate(sqlQuery);
                System.out.println("Query executed successfully.");
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
