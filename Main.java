package com.krabs;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String databaseName = "jdbchoteldb";
        //DatabaseConnector(databaseName);


       // DatabaseConnector dc = new DatabaseConnector(databaseName);
        Connection con = DatabaseConnector.getConnection(databaseName);
       //Connection con = dc.getConnection();//
        ExecuteQueries.PrintPeople(con,databaseName);


        CreatePerson.createPersonTable(con);

        String username = "MrKrabs";
        String email = "Eugene.Krabs@gmail.com";
        String password = "Qwerty";
        String database1 = "users";
        CRUDoperations.createOperation(con,database1,username,email,password);

    }
}