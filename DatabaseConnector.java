package com.krabs;

import java.sql.*;


public class DatabaseConnector {


        public static void ConnectAndExecuteMySQL(String dbName, String SQLQuery) {
            String url = "jdbc:mysql://localhost:3306/" + dbName;
            String username = "root";
            String password = "pw";

            public static Connection getConnection() throws SQLException {
                return DriverManager.getConnection(url, username, password);
            }



            try {
               // Class.forName("com.mysql.cj.jdbc.Driver");
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

               //ResultSet resultSet = statement.executeQuery("select * from hotelvisitors");// where id = 0");
                ResultSet resultSet = statement.executeQuery(SQLQuery);




                while (resultSet.next()) {
                    System.out.println(resultSet.getInt(1) + " "
                            + resultSet.getString(2) + " " + resultSet.getString(3));
                }

                connection.close();
            } catch (SQLException e) {
                // System.out.println(e);
                e.printStackTrace();
            }
//             catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
        }

    }

