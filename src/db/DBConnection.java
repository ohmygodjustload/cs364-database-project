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
import java.util.Properties;

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
     * Connect to the database using the provided JDBC URL.
     * @throws SQLException if a database access error occurs
     */
    public void connect() throws SQLException {
        connection = DriverManager.getConnection(url);
    }

    /**
     * Disconnect from the database.
     * @throws SQLException if a database access error occurs
     */
    public void disconnect() throws SQLException {
        connection.close();
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

    public ResultSet runQuery(String query) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet results = stmt.executeQuery();
        return results;
    }

    /**
     * A simple test method to verify database connection functionality.
     * @param args Command line arguments (not used).
     */
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
            ResultSet results = db.runQuery("SELECT * FROM Landlord;");
            while (results.next()) {
                System.out.println("LLID: " + results.getString("LLID") + ", Name: " + results.getString("Name")+ ", Phone: " + results.getString("PhoneNum") + ", Email: " + results.getString("Email"));
            }
        } catch (SQLException e) {
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
