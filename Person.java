package com.krabs;

public class Person {

    private String name;
    private String surname;
    private int id;
    private String email;

    public Person() {
    }

    public Person(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }
}
