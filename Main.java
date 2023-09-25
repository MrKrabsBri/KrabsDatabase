package com.krabs;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
//        String databaseName = "jdbchoteldb";

//        CreatePerson.createPersonTable(con);
//        String username = "MrKrabs";
//        String email = "Eugene.Krabs@gmail.com";
//        String password = "Qwerty";
//        String database1 = "users";
//        CRUDoperations.createOperation(con,database1,username,email,password);

        DatabaseManager databaseManager = new DatabaseManager();

        PersonDAO personDAO = new PersonDAOImpl(databaseManager);


        // Create a new Person
        Person newPerson = new Person();
        newPerson.setName("Eugene");
        newPerson.setSurname("Krabs Sr.");

        //create
        personDAO.createPerson(newPerson);

        //read
        int personIdToRead = 2;
        Person retrievedPerson = personDAO.readPerson(personIdToRead);

        if (retrievedPerson != null) {
            // Print or work with the retrieved person
            System.out.println("Retrieved Person: " + retrievedPerson.getId()+ " "  +
                    retrievedPerson.getName() + " " + retrievedPerson.getSurname());
        } else {
            System.out.println("No person found with ID: " + personIdToRead);
        }

    }
}