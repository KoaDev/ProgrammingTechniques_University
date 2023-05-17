// dataAccessLayer/DBConnection.java
package dataAccessLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class used to connect to the database
 */
public class DBConnection {
    /**
     * The URL of the database
     */
    private static final String URL = "jdbc:mysql://localhost:3306/OrdersManagement";
    /**
     * The username used to connect to the database
     */
    private static final String USER = "root";
    /**
     * The password used to connect to the database
     */
    private static final String PASSWORD = "789kvbsU&";

    /**
     * @return a connection to the database
     */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to connect to the database", e);
        }
    }
}
