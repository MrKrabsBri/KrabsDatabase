package com.krabs;

import java.sql.*;


public class DatabaseConnector {

private static Connection connection;

            private static String url = "jdbc:mysql://localhost:3306/" ;//+ dbName;
            private static String username = "root";
            private static String password = "pw";

            public DatabaseConnector (String dbName) throws SQLException {

                try {
                    connection = DriverManager.getConnection(url + dbName, username, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    }

