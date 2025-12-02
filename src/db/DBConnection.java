/**
 * A class to instantiate a connection to the database using JDBC.
 * Also implements the Singleton design pattern to ensure only one connection instance exists.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 2, 2025
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    /* Load JDBC
     * Query database
     * Disconnect from database
     */


    private String url = getCredentials();

    private Connection connection;

    // Singleton pattern
    private static final DBConnection INSTANCE = new DBConnection();

    private DBConnection() { }

    public static DBConnection getInstance() {
        return INSTANCE;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

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

    public static void main(String[] args) {
        DBConnection db = DBConnection.getInstance();
        
        try {
            db.connect();
            System.out.println("Connected to database successfully.");
        } catch (SQLException e) {
            System.out.println("Connection failed:");
            e.printStackTrace();
        }

        try {
            db.disconnect();
            System.out.println("Disconnected from database successfully.");
        } catch (SQLException e) {
            System.out.println("Disconnection failed:");
            e.printStackTrace();
        }
    }



}
