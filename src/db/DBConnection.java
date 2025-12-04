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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

        List<Property> properties = new ArrayList<>(); // To statically hold all properties from DB
        List<Tenant> tenants = new ArrayList<>(); // To statically hold all tenants from DB

        try {

            // Populate properties list
            ResultSet results = db.runQuery("SELECT * FROM Property;"); // Fetch all properties
            while (results.next()) 
                properties.add(new Property(
                    results.getInt("PID"),
                    results.getInt("LLID"),
                    results.getDouble("Price"),
                    results.getInt("Bed"),
                    results.getDouble("Bath"),
                    results.getBoolean("PetsAllowed"),
                    results.getString("Address"))
                );

            // Populate tenants list
            results = db.runQuery("SELECT * FROM Tenant;");
            while (results.next()) 
                tenants.add(new Tenant(
                    results.getString("SSN"),
                    results.getString("Fname"),
                    results.getString("Mname"),
                    results.getString("Lname"),
                    results.getDouble("Budget"),
                    results.getString("PhoneNum"),
                    results.getString("Email"),
                    results.getDate("BirthDate").toLocalDate())
                );

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Generate random occupancy mapping
        Map<Property, List<Tenant>> occupancy = new HashMap<>(); // Map of Each Properties to its Tenants
        Map<Property, Integer> propertyOccupancies = new HashMap<>(); // Map of each property's max generated occupancy
        
        Random rand = new Random();
        
        Set<Tenant> usedTenants = new HashSet<>(); // To avoid assigning the same tenant multiple times

        // Initialize occupancy map
        for (Property p : properties) {
            occupancy.put(p, new ArrayList<>()); // Initialize empty tenant list for each property
        }

        // Determine target occupancy for each property
        for (Property p : occupancy.keySet()) {
            int capacity = p.getBed(); // max tenants = number of beds
            int target = rand.nextInt(capacity + 1); // target occupancy between 0 and capacity

            propertyOccupancies.put(p, target);
        }

        // Print each property with its generated target occupancy
        System.out.println("Generated Property Target Occupancies:");
        for (Property p : propertyOccupancies.keySet()) {
            System.out.println("Property " + p.getPID() + " target occupancy: " + propertyOccupancies.get(p));
        }

        Collections.shuffle(tenants); // Shuffle tenants to ensure random assignment

        // System.out.println("Properties:");
        // for (Property p : properties) {
        //     System.out.println(p);
        // }
        int tenantIndex = 0;
        for (Property p : properties) {
            int slots = propertyOccupancies.get(p);

            while (slots > 0 && tenantIndex < tenants.size()) {
                Tenant t = tenants.get(tenantIndex);

                if (!usedTenants.contains(t)) {
                    occupancy.get(p).add(t);
                    usedTenants.add(t);
                    slots = slots - 1;
                }

                tenantIndex = tenantIndex + 1;
            }
        }

        // Print occupancy mapping
        System.out.println("\nOccupancy Mapping (Property -> Tenants):");
        for (Property p : occupancy.keySet()) {
            System.out.println(p);
            List<Tenant> tenantList = occupancy.get(p);
            for (Tenant t : tenantList) {
                System.out.println("  - " + t);
            }
        }

        // Print tenants who were not assigned
        List<Tenant> unassignedTenants = new ArrayList<>();
        for (Tenant t : tenants) {
            if (!usedTenants.contains(t)) {
                unassignedTenants.add(t);
            }
        }
        System.out.println("\nUnassigned Tenants:");
        for (Tenant t : unassignedTenants) {
            System.out.println(t);
        }

        
        int assignedCount = 0;
        // Count total assigned tenants
        for (Property p : occupancy.keySet()) {
            assignedCount += occupancy.get(p).size();
        }
        System.out.println("Total tenants assigned: " + assignedCount);
        System.out.println("Total tenants overall: " + tenants.size());

        // TODO: Prepare and execute INSERT statements to populate LivesIn and LeasesFrom tables based on occupancy mapping

        // System.out.println("\nTenants:");
        // for (Tenant t : tenants) {
        //     System.out.println(t);
        // }

        try {
            db.disconnect();
            System.out.println("Disconnected from database successfully.");
        } catch (SQLException e) {
            System.out.println("Disconnection failed:");
            e.printStackTrace();
        }
    }
}
