package com.krabs;

import java.sql.*;


public class DatabaseConnector {



            private static String url = "jdbc:mysql://localhost:3306/" ;//+ dbName;
            private static String username = "root";
            private static String password = "pw";

            public static Connection getConnection(String dbName) throws SQLException {
                return DriverManager.getConnection(url + dbName, username, password);
            }



    }

