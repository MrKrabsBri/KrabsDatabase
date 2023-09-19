package com.krabs;

import com.mysql.cj.protocol.Resultset;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class CRUDoperations {

    public static void createOperation(Connection connection,String dbName, String username,String email,String password ){

        try {
            String insertQuery = ("INSERT INTO " + dbName + " ( username, email, password) VALUES (?, ?, ?)");

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
            }
        } catch (SQLIntegrityConstraintViolationException e){
            System.err.println("Email is already in use. Please choose a different email. : " + e.getMessage());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static void readOperation(Connection connection){ //select
//
//
//
//        try /*(PreparedStatement preparedStatement = connection.prepareStatement(readQuery))*/{
//            String readQuery = "SELECT * FROM employees";
//            try{
//
//                Resultset resultSet = preparedStatement.executeQuery(readQuery);
//            } catch {
//
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//    }



}
