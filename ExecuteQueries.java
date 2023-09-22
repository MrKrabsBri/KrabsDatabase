package com.krabs;

import com.krabs.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQueries {
    public static void PrintPeople(Connection con, String dbName)  {

       // DatabaseConnector databaseConnection = new DatabaseConnector(dbName);

        try (/*Connection connection = DatabaseConnector.getConnection(dbName)*/ Statement statement = con.createStatement()) {
            String query = "SELECT * FROM hotelvisitors";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("Name");
                String surName = resultSet.getString("Surname");

                System.out.println("Guest ID: " + id);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + surName);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
