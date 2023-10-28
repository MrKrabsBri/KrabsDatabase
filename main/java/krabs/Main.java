package krabs;



public class Main {
    public static void main(String[] args) {

        DatabaseManager databaseManager = new DatabaseManager();
        PersonDAO personDAO = new PersonDAOImpl(databaseManager);
        String dbUrl = "jdbc:mysql://localhost:3306/krustykrabrestaurant";
        String testDBUrl = "jdbc:mysql://localhost:3306/testing_krustykrabrestaurant";
        final String insertQuery = "INSERT INTO staff (firstname, lastname, email, username, position) VALUES (?, ?, ?, ?, ?)";
        final String selectQuery = "SELECT id, firstname, lastname, email, username, position FROM staff WHERE id = ?";
        final String updateQuery = "UPDATE staff SET name = ?, surname = ?, email = ?, username = ?, position = ? WHERE ID = ?";
        final String deleteQuery = "DELETE FROM staff WHERE ID = ?";
        int personIdToRead = 11;
        int idToDelete = 13;

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
//        Person newPerson = new Person();
//        newPerson.setFirstname("Spongebob");
//        newPerson.setLastname("Squarepants");
//        newPerson.setEmail("SPO.SQUA@krustykrab.com");
//        newPerson.setUsername("sposqu2");
//        newPerson.setPosition("Frycook");
//        //create
//        personDAO.createPerson(dbUrl, insertQuery, newPerson);


        // Read

        Person retrievedPerson = personDAO.readPerson(dbUrl,selectQuery,personIdToRead);

        if (retrievedPerson != null) {
            // Print or work with the retrieved person
            System.out.println("Retrieved Person: " + retrievedPerson.getId()+ " "  +
                    retrievedPerson.getFirstname() + " " + retrievedPerson.getLastname()+
                    " " + retrievedPerson.getEmail());
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
        personDAO.deletePerson(dbUrl,deleteQuery,idToDelete);




    }
}