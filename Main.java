package com.krabs;



public class Main {
    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();
        PersonDAO personDAO = new PersonDAOImpl(databaseManager);
        String dbUrl = "jdbc:mysql://localhost:3306/krustykrabrestaurant";
        String insertQuery = "INSERT INTO staff (firstname, lastname, email, username, position) VALUES (?, ?, ?, ?, ?)";

//        try{
//            DatabaseManager.getConnection();
//        } catch(SQLException e){
//            System.out.println("create connection exception");
//            e.printStackTrace();
//        }

//        String createTableSQL = "CREATE TABLE IF NOT EXISTS Staff (" +
//                "id INT AUTO_INCREMENT PRIMARY KEY," +
//                "firstname VARCHAR(50) NOT NULL," +
//                "lastname VARCHAR(50) NOT NULL," +
//                "email VARCHAR(100) NOT NULL UNIQUE," +
//                "username VARCHAR(100) NOT NULL UNIQUE" +
//                "position VARCHAR(100) NOT NULL)";
//        QueryManager.createPersonTable(createTableSQL);


//        // Create
        Person newPerson = new Person();
        newPerson.setFirstname("Spongebob");
        newPerson.setLastname("Squarepants");
        newPerson.setEmail("SP.SQ@krustykrab.com");
        newPerson.setUsername("sposqu");
        newPerson.setPosition("Frycook");
        //create
        personDAO.createPerson(dbUrl, insertQuery, newPerson);


        // Read
//        int personIdToRead = 8;
//        Person retrievedPerson = personDAO.readPerson(dbUrl,personIdToRead);
//
//        if (retrievedPerson != null) {
//            // Print or work with the retrieved person
//            System.out.println("Retrieved Person: " + retrievedPerson.getId()+ " "  +
//                    retrievedPerson.getFirstname() + " " + retrievedPerson.getLastname());
//        } else {
//            System.out.println("No person found with ID: " + personIdToRead);
//        }

//        //update
//        Person personToUpdate = new Person();
//        personToUpdate.setName("Sandy");
//        personToUpdate.setSurname("Cheeks");
//        int idToUpdate = 10; // hardcoded id to Update
//        personDAO.updatePerson(personToUpdate,idToUpdate);

        //delete
//        int idToDelete = 13;
//        personDAO.deletePerson(idToDelete);




    }
}