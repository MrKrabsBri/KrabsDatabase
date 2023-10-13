package com.krabs;

public interface PersonDAO {

    //create
    int createPerson(String dbUrl, String query, Person person);
    Person readPerson(String dbUrl, String selectQuery, int id);
    void updatePerson(String dbUrl, Person person, int id);
    void deletePerson(String dbUrl, int personId);
}
