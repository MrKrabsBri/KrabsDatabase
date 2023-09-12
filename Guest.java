package com.krabs;

public class Guest {

    private String name;
    private String surname;

    public Guest(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name + " " + surname;
    }
}
