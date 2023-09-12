package com.krabs;

import java.sql.ResultSet;
import java.sql.Statement;

public class ExecuteQueries {


    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("select * from hotelvisitors");// where id = 0");

    while (resultSet.next()) {
        System.out.println(resultSet.getInt(1) + " "
                + resultSet.getString(2) + " " + resultSet.getString(3));
    }
    connection.close();

}
