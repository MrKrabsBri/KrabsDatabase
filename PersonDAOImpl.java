package com.krabs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class PersonDAOImpl implements PersonDAO { //contains interfaces

    private BasicDataSource dataSource;

    public PersonDAOImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    private DatabaseManager databaseManager;

    public PersonDAOImpl(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }


    //create
    @Override
    public void createPerson(Person person) {

        try (Connection connection = databaseManager.getConnection()) {
            // SQL query for inserting a new person
            String insertQuery = "INSERT INTO hotelvisitors (name, surname) VALUES (?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                // Set the parameters for the prepared statement
                preparedStatement.setString(1, person.getName());
                preparedStatement.setString(2, person.getSurname());

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected == 1){
                    try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()) {
                            int generatedKey = generatedKeys.getInt(1); // Retrieve the generated key value
                            System.out.println("Generated Key: " + generatedKey);
                        } else {
                            System.err.println("No generated keys found.");
                        }
                    }

                } else {
                    System.out.println("Insertion was not successful.");
                }

            }
        } catch (SQLException e) {
            System.out.println("SQL exception for CREATE was caught.");
            e.printStackTrace();
        }
    }


    //read
    @Override
    public Person readPerson(int id) {

        try (Connection connection = databaseManager.getConnection()) {
            // SQL query for retrieving a person by ID
            String selectQuery = "SELECT id, name, surname FROM hotelvisitors WHERE id = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, id);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Create a new Person object with data from the result set
                        Person person = new Person();
                        person.setId(resultSet.getInt("id"));
                        person.setName(resultSet.getString("name"));
                        person.setSurname(resultSet.getString("surname"));
                        return person;
                    } else {
                        // No matching person found
                        return null;
                    }
                }
            }
        } catch (SQLException e){
            System.out.println("SQL exception for READ was caught: ");
            e.printStackTrace();
            return null;
        }


    }


    //update
    @Override
    public void updatePerson(Person person, int id) { // pass a new person as an argument, ID of the current person.
        String updateQuery = "UPDATE hotelvisitors SET name = ?, surname = ? WHERE ID = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setInt(3, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Person with ID " + id + " updated successfully.");
            } else {
                System.err.println("Update operation did not affect one row as expected.");
                // Handle the case where the update didn't succeed
                // You can throw an exception or handle it based on your application's requirements
            }
        } catch (SQLException e){
            System.out.println("SQL exception for UPDATE was caught: ");
            e.printStackTrace();
        }
    }

    @Override
    public void deletePerson(int personId) {
        String deleteQuery = "DELETE FROM hotelvisitors WHERE ID = ?";

        try (Connection connection = databaseManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, personId);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                System.out.println("Person with ID " + personId + " deleted successfully.");
            } else {
                System.err.println("Delete operation did not affect one row as expected.");
            }
        } catch (SQLException e) {
            System.out.println("SQL exception for DELETE was caught: ");
            e.printStackTrace();
        }

    }
}


