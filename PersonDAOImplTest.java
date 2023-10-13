package com.krabs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOImplTest {

    private BasicDataSource dataSource = new BasicDataSource();
    private String testUrl = "jdbc:mysql://localhost:3306/testing_krustykrabrestaurant";
    private String insertQuery = "INSERT INTO test_staff (firstname, lastname, email, username, position) VALUES (?, ?, ?, ?, ?)";
    private String selectQuery = "SELECT id, firstname, lastname, email, username, position FROM test_staff WHERE id = ?";
    private PersonDAO personDAO ;

    @BeforeEach
    public void setUp() {
        dataSource = new BasicDataSource();
        dataSource.setUrl(testUrl);
        dataSource.setUsername("root");
        dataSource.setPassword("pw");
        personDAO = new PersonDAOImpl(dataSource);
    }

    @AfterEach
    public void tearDown() {
        try {
            dataSource.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void CreatePersonTestInvalidData() {

        String name = "Eugen";
        String surname = "Krab";
        String email = "Eug.Krabs123@krustykrabz.com";
        String username = "eugkraz";
        String position = "CEO";
        Person testCreatePerson = new Person(name,surname,email,username,position);
        Person testReadPerson;

        int generatedID = personDAO.createPerson(testUrl, insertQuery, testCreatePerson);
        testCreatePerson.setId(generatedID);

        testReadPerson = personDAO.readPerson(testUrl, selectQuery, generatedID);

        assertEquals(testReadPerson.getFirstname(),testCreatePerson.getFirstname());
        assertEquals(testReadPerson.getId(),testCreatePerson.getId());
        //boolean created = personDAO.createPerson(testPerson); continue this

        // Assert: Verify that creation fails and no data is inserted
        //assertFalse(created, "Person creation should return false for invalid data");
    }

    //assertThrows example
    @Test
    void exceptionTesting(){
        var worker = new Person();
        assertThrows(SQLException.class, () -> {

        });
        worker.getFirstname();
    }
}