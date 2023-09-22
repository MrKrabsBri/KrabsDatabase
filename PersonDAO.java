package com.krabs;

import java.sql.*;

public class PersonDAO {

    //create
    public static void createPerson(Connection connection, String dbName, String username, String email, String password ){

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

    //read
    public static void readPerson(Connection connection){

        try (Connection connection = DatabaseConnector.getConnection(dbName)) {
            String query = "SELECT username, email, password FROM hotelvisitors WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, someId);  // Bind parameter
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String firstName = resultSet.getString("Name");
                        String surName = resultSet.getString("Surname");

                        printPerson(id, firstName, surName);
                    }
                }
            }
        } catch (SQLException e) {
            handleSQLException(e);
        }

    }

}
