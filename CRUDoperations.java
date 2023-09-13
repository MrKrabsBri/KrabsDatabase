package com.krabs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUDoperations {

public static void createOperation(Connection connection,String username,String email,String password ){

    try {
        String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";



        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int rowsInserted = preparedStatement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("A new employee was inserted successfully.");
            } else {
                System.out.println("Insertion failed.");
            }
        }// catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
    } catch (SQLException e) {
        e.printStackTrace();
    }



}


}
