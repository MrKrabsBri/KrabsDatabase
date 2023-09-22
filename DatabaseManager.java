package com.krabs;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

//public class DatabaseManager {
//
//    private final BlockingQueue<Connection> pool;
//
//    public CustomConnectionPool(String jdbcUrl, String username, String password, int poolSize) throws SQLException {
//        pool = new ArrayBlockingQueue<>(poolSize);
//        for (int i = 0; i < poolSize; i++) {
//            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
//            pool.offer(connection);
//        }
//    }
//
//    public Connection getConnection() throws InterruptedException {
//        return pool.take();
//    }
//
//    public void releaseConnection(Connection connection) throws SQLException {
//        if (connection != null && !connection.isClosed()) {
//            pool.offer(connection);
//        }
//    }
//
//}

public class DatabaseManager {

        // Configure a BasicDataSource with connection pool settings
        private BasicDataSource dataSource;


        public DatabaseManager(String jdbcUrl, String username, String password) throws SQLException {

                dataSource = new BasicDataSource();
                dataSource.setUrl(jdbcUrl);//"jdbc:mysql://localhost:3306/your_database"
                dataSource.setUsername(username);
                dataSource.setPassword(password);
                dataSource.setInitialSize(5); // Initial number of connections
                dataSource.setMaxTotal(10); // Maximum number of connections

        }






        // Create an executor service with multiple threads
        ExecutorService executorService = Executors.newFixedThreadPool(5);


        // Shutdown the executor service
        executorService.shutdown();




}