package krabs;

public interface PersonDAO {

    //create
    int createPerson(String dbUrl, String query, Person person);
    Person readPerson(String dbUrl, String selectQuery, int id);
    void updatePerson(String dbUrl, String updateQuery, Person person, int id);
    void deletePerson(String dbUrl, String deleteQuery, int personId);
}
