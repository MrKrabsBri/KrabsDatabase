package krabs;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;

public class PersonDAOImpl implements PersonDAO { //contains interfaces

    private BasicDataSource dataSource;
    boolean personCreated;


    public PersonDAOImpl(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isNameValid(String name) {
        // Check if the name is not null and not empty
        return name != null && !name.trim().isEmpty() && name.matches("[A-Za-z]+");
    }
    public boolean isSurnameValid(String surname) {
        // Check if the surname is not null and not empty
        return surname != null && !surname.trim().isEmpty() && surname.matches("[A-Za-z]+");
    }
    private DatabaseManager databaseManager;

    public PersonDAOImpl(DatabaseManager databaseManager){
        this.databaseManager = databaseManager;
    }


    //create
    @Override
    public int createPerson(String dbUrl, String insertQuery,  Person person) {

        int generatedKey = -1;

        if (!isNameValid(person.getFirstname())) {
            System.err.println("Invalid name. Please provide a valid name.");
            return generatedKey; // Don't proceed with the database operation
        }

        if (!isSurnameValid(person.getLastname())) {
            System.err.println("Invalid surname. Please provide a valid surname.");
            return generatedKey; // Don't proceed with the database operation
        }

        try (Connection connection = databaseManager.getConnection(dbUrl)) {
            // SQL query for inserting a new person
            //String insertQuery = "INSERT INTO staff (firstname, lastname, email, username, position) VALUES (?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                // Set the parameters for the prepared statement
                preparedStatement.setString(1, person.getFirstname());
                preparedStatement.setString(2, person.getLastname());
                preparedStatement.setString(3, person.getEmail());
                preparedStatement.setString(4, person.getUsername());
                preparedStatement.setString(5, person.getPosition());

                int rowsAffected = preparedStatement.executeUpdate();

                if(rowsAffected == 1){
                    try(ResultSet generatedKeys = preparedStatement.getGeneratedKeys()){
                        if (generatedKeys.next()) {
                            generatedKey = generatedKeys.getInt(1); // Retrieve the generated key value
                            personCreated = true;
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
        return generatedKey;
    }


    //read
    @Override
    public Person readPerson(String dbUrl, String selectQuery, int idPerson) {

        try (Connection connection = databaseManager.getConnection(dbUrl)) {
            // SQL query for retrieving a person by ID

            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
                preparedStatement.setInt(1, idPerson);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Create a new Person object with data from the result set
                        Person person = new Person();
                        person.setId(resultSet.getInt("id"));
                        person.setFirstname(resultSet.getString("firstname"));
                        person.setLastname(resultSet.getString("lastname"));
                        person.setEmail(resultSet.getString("email"));
                        person.setUsername(resultSet.getString("username"));
                        person.setPosition(resultSet.getString("position"));
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
    public void updatePerson(String dbUrl, String updateQuery, Person person, int id) { // pass a new person as an argument, ID of the current person.

        try (Connection connection = databaseManager.getConnection(dbUrl);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, person.getFirstname());
            preparedStatement.setString(2, person.getLastname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setString(4, person.getUsername());
            preparedStatement.setString(5, person.getPosition());
            preparedStatement.setInt(6, id);

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

    //delete
    @Override
    public void deletePerson(String dbUrl, String deleteQuery, int personId) {
        //final String deleteQuery = "DELETE FROM staff WHERE ID = ?";

        try (Connection connection = databaseManager.getConnection(dbUrl);
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


