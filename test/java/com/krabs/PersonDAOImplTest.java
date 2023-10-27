package com.krabs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.*;
import java.util.Random;

/*import static org.junit.jupiter.api.Assertions.**/;

class PersonDAOImplTest {

    private BasicDataSource dataSource = new BasicDataSource();
    public String testUrl = "jdbc:mysql://localhost:3306/testing_krustykrabrestaurant";
    private String insertQueryTest = "INSERT INTO test_staff (firstname, lastname, email, username, position) VALUES (?, ?, ?, ?, ?)";
    private String selectQueryTest = "SELECT id, firstname, lastname, email, username, position FROM test_staff WHERE id = ?";
    private String deleteQueryTest = "DELETE FROM test_staff WHERE ID = ?";
    private PersonDAO personDAO ;

    public static String generateRandomString(int length) { //generate a random String for testing insertion
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = rnd.nextInt(characters.length());
            sb.append(characters.charAt(index));
        }
        return sb.toString();
    }

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
    public void testReadPersonReturnsNotNullObject() {
        int readingID1 = 1;
        int readingID3 = 3;
        int readingID9 = 9;
//        Person testPerson = new Person("Eugen", "Krab", "Eug.Krabs123@krustykrab.com",
//                "eugkra", "CEO");
        Assertions.assertNotNull(personDAO.readPerson(testUrl, selectQueryTest, readingID1));
        Assertions.assertNotNull(personDAO.readPerson(testUrl, selectQueryTest, readingID3));
        Assertions.assertNotNull(personDAO.readPerson(testUrl, selectQueryTest, readingID9));
    }

    @Test
    public void testReadPersonReturnsNull() {
        int readingID2 = 2;
        int readingID4 = 4;
        int readingID8 = 8;
//        Person testPerson = new Person("Eugen", "Krab", "Eug.Krabs123@krustykrab.com",
//                "eugkra", "CEO");
        Assertions.assertNull(personDAO.readPerson(testUrl, selectQueryTest, readingID2));
        Assertions.assertNull(personDAO.readPerson(testUrl, selectQueryTest, readingID4));
        Assertions.assertNull(personDAO.readPerson(testUrl, selectQueryTest, readingID8));
    }

    @Test
    public void testReadPersonReturnsCorrectPerson() {
        int readingID1 = 1;
        int readingID9 = 9;
        Person testPersonEugenKrab = new Person("Eugen", "Krab", "Eug.Krabs123@krustykrab.com",
                "eugkra", "CEO");
        Person testPersonEugeneTestKrabs = new Person("EugeneTest", "Krabs", "tzcjgbl@krustykrab.com",
                "komcnk", "CEO");
        Assertions.assertEquals(testPersonEugenKrab,personDAO.readPerson(testUrl, selectQueryTest, readingID1));
        Assertions.assertEquals(testPersonEugeneTestKrabs,personDAO.readPerson(testUrl, selectQueryTest, readingID9));
    }

    @Test
    public void testReadPersonReturnsIncorrectPerson() {
        int readingID1 = 1;
        int readingID3 = 3;
        Person testPersonSquidwardTentacles = new Person("Squidward", "Tentacles", "SQ.TENT@krustykrab.com",
                "squten", "cashier");
        Assertions.assertNotEquals(testPersonSquidwardTentacles,personDAO.readPerson(testUrl, selectQueryTest, readingID1));
        Assertions.assertNotEquals(testPersonSquidwardTentacles,personDAO.readPerson(testUrl, selectQueryTest, readingID3));
    }

    @Test
    public void testDeletePersonReturnsNull() {
        Person testPerson = new Person("Testname", "Testsurname", generateRandomString(8)+"@test.com",
                generateRandomString(6), "test");
        int testPersonID = personDAO.createPerson(testUrl, insertQueryTest,testPerson);
        personDAO.deletePerson(testUrl,deleteQueryTest,testPersonID);
        Assertions.assertNull(personDAO.readPerson(testUrl,selectQueryTest,testPersonID));
    }

    @Test
    public void testCreatePersonWithInvalidData() {

        String name = "EugeneTest";
        String surname = "Krabs";
        String email = generateRandomString(7).toLowerCase()+"@krustykrab.com";
        String username = generateRandomString(6).toLowerCase();
        String position = "CEO";
        Person testCreatePerson = new Person(name,surname,email,username,position);
        Person testReadPerson;

        int generatedID = personDAO.createPerson(testUrl, insertQueryTest, testCreatePerson);
        testCreatePerson.setId(generatedID);

        testReadPerson = personDAO.readPerson(testUrl, selectQueryTest, generatedID);
        Assertions.assertEquals(testReadPerson,testCreatePerson);

        personDAO.deletePerson(testUrl,deleteQueryTest,generatedID);//deleting the person after test
    }



    @Test
    public void testCreatePersonTestReturnsSquidwardTentacles() {
        Person testPersonSquidwardTentacles = new Person("Squidward", "Tentacles", "SQUID.TENT@krustykrab.com",
                "squten", "cashier");
        int testGeneratedKeyAfterCreate = personDAO.createPerson(testUrl,insertQueryTest,testPersonSquidwardTentacles);
        Person retrievedPerson = personDAO.readPerson(testUrl,selectQueryTest,testGeneratedKeyAfterCreate);
        Assertions.assertEquals(testPersonSquidwardTentacles,retrievedPerson);
        personDAO.deletePerson(testUrl,deleteQueryTest,testGeneratedKeyAfterCreate);
    }

    /*No Duplicates test/ email/ username*/
    /*test if duplicates are accepted*/
    /*test position correctness, name, surname*/
    /**/






    //assertThrows example
//    @Test
//    void exceptionTesting(){
//        var worker = new Person();
//        assertThrows(SQLException.class, () -> {
//
//        });
//        worker.getFirstname();
//    }
}