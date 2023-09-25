package com.krabs;

public interface PersonDAO {

    //create
    void createPerson(Person person);
    Person readPerson(int id);
    void updatePerson(Person person);
    void deletePerson(int id);
}
