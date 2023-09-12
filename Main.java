package com.krabs;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String databaseName = "jdbchoteldb";
        //DatabaseConnector(databaseName);
        ExecuteQueries.PrintPeople(databaseName);

        DatabaseConnector dc = new DatabaseConnector(databaseName);
        Connection con = dc.getConnection();
        CreatePerson.createPersonTable(con);
    }
}