//package com.krabs;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class CreatingPersonTable {
//
//    try (Connection connection = DatabaseConnector.getConnection();
//    Statement statement = connection.createStatement())
//
//    {
//
//        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
//                "id INT AUTO_INCREMENT PRIMARY KEY," +
//                "username VARCHAR(50) NOT NULL," +
//                "email VARCHAR(100) NOT NULL UNIQUE," +
//                "password VARCHAR(100) NOT NULL)";
//
//    } catch (
//    SQLException e) {
//        e.printStackTrace();
//    }
//}
