/**
 * Data Access Object (DAO) class for Property entities.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 9, 2025
 */
package dao;

import model.dto.PropertyLandlordStats;
import model.dto.PropertyTenantStats;
import model.dto.PropertyVacancyStats;
import db.DBConnection;
import model.Property;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {

    private final DBConnection db; // Singleton DB connection instance

    public PropertyDAO() {
        db = DBConnection.getInstance(); // Initialize DB connection
    }

    /**
     * Retrieve all properties from the database.
     * @return List of all Property objects
     * @throws SQLException
     */
    public List<Property> getAllProperties() throws SQLException {
        // Process results and populate properties list
        String sql = "SELECT * FROM Property";
        List<Property> properties = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                properties.add(new Property(
                    results.getInt("PID"),
                    results.getInt("LLID"),
                    results.getDouble("price"),
                    results.getInt("bed"),
                    results.getDouble("bath"),
                    results.getBoolean("petsAllowed"),
                    results.getString("address")
                ));
            }
        }

        return properties;
    }

    /**
     * Retrieve a property by its PID.
     * @param PID Property ID
     * @return Property object or null if not found
     * @throws SQLException
     */
    public Property getPropertyByID(int PID) throws SQLException {
        String sql = "SELECT * FROM Property WHERE PID = ?";
        Property property = null;

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setInt(1, PID);
            try (ResultSet results = stmt.executeQuery();) {
                if (results.next()) {
                    property = new Property(
                        results.getInt("PID"),
                        results.getInt("LLID"),
                        results.getDouble("Price"),
                        results.getInt("Bed"),
                        results.getDouble("Bath"),
                        results.getBoolean("PetsAllowed"),
                        results.getString("Address")
                    );
                }
            }
        }

        return property;
    }

    /**
     * Advanced Query: Retrieve the top 10 most expensive properties with at least one vacant bed.
     * (Written and integrated by Andrew Peirce)
     * 
     * @return List of PropertyVacancyStats objects
     * @throws SQLException
     */
    public List<PropertyVacancyStats> getMostExpensiveProperties() throws SQLException {
        String sql = "SELECT p.PID, p.Address, p.Price, p.Bed, COUNT(li.SSN) As CurrentOccupancy, (p.bed - COUNT(li.SSN)) AS VacantBeds " +
                     "FROM Property p " +
                     "LEFT JOIN LivesIn li ON p.PID = li.PID " +
                     "GROUP BY p.PID, p.Address, p.Price, p.Bed " +
                     "HAVING VacantBeds > 0 " +
                     "ORDER BY p.Price DESC " +
                     "LIMIT 10";

        List<PropertyVacancyStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new PropertyVacancyStats(
                    results.getInt("PID"),
                    results.getString("Address"),
                    results.getDouble("Price"),
                    results.getInt("Bed"),
                    results.getInt("CurrentOccupancy"),
                    results.getInt("VacantBeds")
                ));
            }
        }

        return stats;
    }

    /**
     * Advanced Query: Retrieve the 50 cheapest properties with at least one vacant bed.
     * Offset the first 3 cheapest properties because they might be too good to be true.
     * (Written and integrated by Andrew Peirce)
     * 
     * @return List of PropertyVacancyStats objects
     * @throws SQLException
     */
    public List<PropertyVacancyStats> getCheapestProperties() throws SQLException {
        String sql = "SELECT p.PID, p.Address, ROUND((p.Price / p.Bed), 2) AS PricePerBed, p.Bed, COUNT(li.SSN) AS CurrentOccupancy, (p.Bed - COUNT(li.SSN)) AS VacantBeds, l.Name AS LandlordName " +
                     "FROM Property AS p LEFT JOIN LivesIn AS li ON p.PID = li.PID " +
                     "LEFT JOIN Tenant AS t ON li.SSN = t.SSN " +
                     "LEFT JOIN Landlord AS l ON p.LLID = l.LLID " +
                     "GROUP BY p.PID, p.Address, p.price, p.Bed, l.Name " +
                     "HAVING VacantBeds > 0 " +
                     "ORDER BY PricePerBed ASC " +
                     "LIMIT 50 " +
                     "OFFSET 3";

        List<PropertyVacancyStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new PropertyVacancyStats(
                    results.getInt("PID"),
                    results.getString("Address"),
                    results.getDouble("PricePerBed"),
                    results.getInt("Bed"),
                    results.getInt("CurrentOccupancy"),
                    results.getInt("VacantBeds"),
                    results.getString("LandlordName")
                ));
            }
        }

        return stats;
    }

    /**
     * Advanced Query: Retrieve the top 10 properties with 'St' in the address by number of tenants.
     * (Written by Rohan Hari, integrated by Andrew Peirce)
     * 
     * @return List of PropertyTenantStats objects
     * @throws SQLException
     */
    public List<PropertyTenantStats> getPropertyTenantStats() throws SQLException {
        String sql = "SELECT p.PID, p.Address, COUNT(l.SSN) AS NumTenants " +
                     "FROM Property p JOIN LivesIn l ON p.PID = l.PID " +
                     "WHERE p.Address LIKE '%St%' " +
                     "GROUP BY p.PID, p.Address " +
                     "ORDER BY NumTenants DESC " +
                     "LIMIT 10";

        List<PropertyTenantStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new PropertyTenantStats(
                    results.getInt("PID"),
                    results.getString("Address"),
                    results.getInt("NumTenants")
                ));
            }
        }
        
        return stats;
    }

    /**
     * Advanced Query: Retrieve properties whose rent is higher than that landlord's average.
     * (Written by Jacob Rogers, integrated by Andrew Peirce)
     * 
     * @return List of PropertyLandlordStats objects
     * @throws SQLException
     */
    public List<PropertyLandlordStats> getPropertiesAboveLandlordAverage() throws SQLException {
        String sql = "SELECT p.PID, p.Address, p.Price, l.LLID, l.Name AS LandlordName " +
                     "FROM Property AS p JOIN Landlord AS l ON p.LLID = l.LLID " +
                     "WHERE p.Price > (SELECT AVG(p2.Price) " +
                        "FROM Property AS p2 " +
                        "WHERE p2.LLID = p.LLID)";

        List<PropertyLandlordStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new PropertyLandlordStats(
                    results.getInt("PID"),
                    results.getString("Address"),
                    results.getDouble("Price"),
                    results.getInt("LLID"),
                    results.getString("LandlordName")
                ));
            }
        }

        return stats;
    }

    /**
     * Retrieve all properties owned by a specific landlord.
     * @param LLID Landlord ID
     * @return List of Property objects owned by the landlord
     * @throws SQLException
     */
    public List<Property> getPropertiesByLandlordID(int LLID) throws SQLException {
        String sql = "SELECT * FROM Property WHERE LLID = ?";
        List<Property> properties = new ArrayList<>();
        
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setInt(1, LLID);
            try (ResultSet results = stmt.executeQuery();) {
                while (results.next()) {
                    properties.add(new Property(
                        results.getInt("PID"),
                        results.getInt("LLID"),
                        results.getDouble("Price"),
                        results.getInt("Bed"),
                        results.getDouble("Bath"),
                        results.getBoolean("PetsAllowed"),
                        results.getString("Address")
                    ));
                }
            }
        }

        return properties;
    }

    /**
     * Insert a new property into the database.
     * @param p Property object to insert
     * @throws SQLException
     */
    public void insertProperty(Property p) throws SQLException {
        String sql = "INSERT INTO Property (LLID, Price, Bed, Bath, PetsAllowed, Address) VALUES (?, ?, ?, ?, ?, ?)";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setInt(1, p.getLLID());
            stmt.setDouble(2, p.getPrice());
            stmt.setInt(3, p.getBed());
            stmt.setDouble(4, p.getBath());
            stmt.setBoolean(5, p.isPetsAllowed());
            stmt.setString(6, p.getAddress());
            stmt.executeUpdate();
        }
    }

    /**
     * Update an existing property in the database.
     * @param p Property object with updated values
     * @throws SQLException
     */
    public void updateProperty(Property p) throws SQLException {
        String sql = "UPDATE Property SET Price=?, Bed=?, Bath=?, PetsAllowed=?, Address=? WHERE PID=?";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setDouble(1, p.getPrice());
            stmt.setInt(2, p.getBed());
            stmt.setDouble(3, p.getBath());
            stmt.setBoolean(4, p.isPetsAllowed());
            stmt.setString(5, p.getAddress());
            stmt.setInt(6, p.getPID());
            stmt.executeUpdate();
        }
    }

    /**
     * Delete a property from the database.
     * @param PID Property ID
     * @throws SQLException
     */
    public void deleteProperty(int PID) throws SQLException {
        String deleteLivesIn = "DELETE FROM LivesIn WHERE PID = ?";
        String deleteLeases = "DELETE FROM LeasesFrom WHERE PID = ?";
        String deleteProperty = "DELETE FROM Property WHERE PID = ?";

        Connection conn = db.getConnection();
        boolean originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try (
            PreparedStatement stmt0 = conn.prepareStatement(deleteLivesIn);
            PreparedStatement stmt1 = conn.prepareStatement(deleteLeases);
            PreparedStatement stmt2 = conn.prepareStatement(deleteProperty)
        ) {
            // delete from all child tables first

            // Delete from LivesIn first
            stmt0.setInt(1, PID);
            stmt0.executeUpdate();

            // Delete child rows from LeasesFrom
            stmt1.setInt(1, PID);
            stmt1.executeUpdate();

            // Then delete parent row from Property
            stmt2.setInt(1, PID);
            int rows = stmt2.executeUpdate();

            if (rows == 0) {
                throw new SQLException("No Property found with PID = " + PID);
            }

            conn.commit(); // only commit if both deletions succeed
        } catch (SQLException e) {
            conn.rollback(); // rollback if either deletion fails
            throw e; // rethrow the exception after rollback
        } finally {
            conn.setAutoCommit(originalAutoCommit); // restore original auto-commit setting
        }
    }

    // public static void main(String[] args) {
    //     try {
    //         DBConnection.getInstance().connect();
    //         PropertyDAO propertyDAO = new PropertyDAO();
    //         List<Property> properties = propertyDAO.getMostExpensiveProperties();
    //         for (Property p : properties) {
    //             System.out.println(p.toStringWithTenants());
    //         }
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }
}