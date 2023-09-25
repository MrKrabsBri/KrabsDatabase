package com.krabs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.ls.LSOutput;

import java.sql.*;

public class PersonDAOImpl implements PersonDAO { //contains interfaces

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
                System.out.println(Statement.RETURN_GENERATED_KEYS);

               int rowsAffected = preparedStatement.executeUpdate();
                System.out.println(rowsAffected);

                if(rowsAffected == 1){

                    try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()) {
                            int generatedKey = generatedKeys.getInt(1); // Retrieve the generated key value
                            System.out.println("Generated Key: " + generatedKey);
                        } else {
                            System.err.println("No generated keys found.");
                        }
                    }


//                    ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
//                    System.out.println(generatedKeys);
//                    if (generatedKeys.next()){
//                        int generatedId = generatedKeys.getInt(1);
//                        person.setId(generatedId);
//                        System.out.println("Person added");
//                        System.out.println(generatedId);
//                    }
                } else {
                    System.out.println("Insertion was not successful.");
                }

                // Execute the SQL insert statement
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("SQL exception caught.");
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
            System.out.println("SQL Exception caught.");
            return null;
        }


    }

    @Override
    public void updatePerson(Person person) {

    }

    @Override
    public void deletePerson(int id) {

    }
}


