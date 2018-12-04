package currencyExchange.com.config;

import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@Configuration
public class ConnectionToDB {
    private static String URL = "jdbc:postgresql://localhost:5432/test1";
    private static String USERNAME = "postgres";
    private static String PASSWORD = "postgres";
    private static String driverName = "org.postgresql.Driver";
    private static Connection connection;

    public static Connection myCreateConnection() {
        connection = null;
        System.out.println("-------- PostgreSQL JDBC ConnectionToPostgreSQL Testing ------------");
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your PostgreSQL JDBC Driver? Include in your library path!");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("ConnectionToPostgreSQL Failed! Check output console");
            e.printStackTrace();
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        return connection;

    }

}
