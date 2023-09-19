package com.krabs;

import java.sql.*;
import org.apache.commons.dbcp2.BasicDataSource;



public class DatabaseConnector {
    private static BasicDataSource dataSource;

    static {
        // Initialize the connection pool
        dataSource = new BasicDataSource();
       // dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/");
        dataSource.setUsername("root");
        dataSource.setPassword("pw");
    }

    public static Connection getConnection(String dbName) throws SQLException {
        dataSource.setUrl("jdbc:mysql://localhost:3306/"+ dbName);
        return dataSource.getConnection();
    }
}
