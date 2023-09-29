package com.krabs;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();
        PersonDAO personDAO = new PersonDAOImpl(databaseManager);

//        // Create
//        Person newPerson = new Person();
//        newPerson.setName("Eugene");
//        newPerson.setSurname("Krabs");
//        //create
//        personDAO.createPerson(newPerson);

        //read
        int personIdToRead = 10;
        Person retrievedPerson = personDAO.readPerson(personIdToRead);

        if (retrievedPerson != null) {
            // Print or work with the retrieved person
            System.out.println("Retrieved Person: " + retrievedPerson.getId()+ " "  +
                    retrievedPerson.getName() + " " + retrievedPerson.getSurname());
        } else {
            System.out.println("No person found with ID: " + personIdToRead);
        }

//        //update
//        Person personToUpdate = new Person();
//        personToUpdate.setName("Sandy");
//        personToUpdate.setSurname("Cheeks");
//        int idToUpdate = 10; // hardcoded id to Update
//        personDAO.updatePerson(personToUpdate,idToUpdate);

        //delete
        int idToDelete = 13;
        personDAO.deletePerson(idToDelete);




    }
}