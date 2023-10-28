package krabs;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class DatabaseManager {
    private static BasicDataSource dataSource;

    static {
        // Initialize the connection pool
        dataSource = new BasicDataSource();

       //dataSource.setDriverClassName("com.mysql.jdbc.Driver");

        //dataSource.setUrl("jdbc:mysql://localhost:3306/krustykrabrestaurant");
        dataSource.setUsername("root");
        dataSource.setPassword("pw");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(10);

    }

    public static Connection getConnection(String url) throws SQLException {
        dataSource.setUrl(url);
        return dataSource.getConnection();
    }
}
