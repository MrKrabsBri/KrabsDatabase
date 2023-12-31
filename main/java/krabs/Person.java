package krabs;

import java.util.Objects;

public class Person {

    private String name;
    private String surname;
    private int id;
    private String email;
    private String username;




    private String position;
    public Person() {
    }

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, String email, String username, String position) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.position = position;
    }

    public void setFirstname(String name) {
        this.name = name;
    }
    public String getFirstname() {
        return name;
    }

    public void setLastname(String surname) {
        this.surname = surname;
    }
    public String getLastname() {
        return surname;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    @Override
    public String toString(){
        return name + " " + surname;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
