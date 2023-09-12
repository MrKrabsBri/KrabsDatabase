package com.krabs;

public class Main {
    public static void main(String[] args) {
        String databaseName = "jdbchoteldb";
        //DatabaseConnector(databaseName);
        ExecuteQueries.PrintPeople(databaseName);
    }
}