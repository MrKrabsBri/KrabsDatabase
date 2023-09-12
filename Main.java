package com.krabs;

import static com.krabs.DatabaseConnector.ConnectAndExecuteMySQL;

public class Main {
    public static void main(String[] args) {
        String databaseName = "jdbchoteldb";
        ConnectAndExecuteMySQL(databaseName);
    }
}