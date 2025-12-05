/**
 * A class to instantiate a connection to the database using JDBC. Implements the Singleton design pattern 
 * to ensure only one connection instance exists, and provides methods to connect and disconnect from the database.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 2, 2025
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

public class DBConnection {
    private String url = getCredentials();

    private Connection connection;

    // Singleton pattern
    private static final DBConnection INSTANCE = new DBConnection();

    private DBConnection() { }

    /**
     * Get the singleton instance of DBConnection.
     * @return The singleton DBConnection instance.
     */
    public static DBConnection getInstance() {
        return INSTANCE;
    }

    /**
     * Connect to the database.
     * @throws SQLException if a database access error occurs
     */
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
        }
    }

    /**
     * Disconnect from the database.
     * @throws SQLException if a database access error occurs
     */
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    /**
     * Get the current database connection.
     * @return The current database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * A helper method to read JDBC credentials from a properties file.
     * @return A formatted JDBC URL string.
     */
    private String getCredentials() {
        String url = "", user = "", pass = "";

        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("config/db.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        url = properties.getProperty("db.url");
        user = properties.getProperty("db.user");
        pass = properties.getProperty("db.pass");

        return "jdbc:mysql://" + url + "?user=" + user + "&password=" + pass;
    }
}
