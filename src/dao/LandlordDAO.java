/**
 * Data Access Object (DAO) for Landlord entity.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 7, 2025
 */
package dao;

import db.DBConnection;
import model.Landlord;
import model.dto.LandlordBedBathStats;
import model.dto.LandlordPropertyStats;
import model.dto.LandlordTenantStats;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LandlordDAO {
    private final DBConnection db;

    public LandlordDAO() {
        db = DBConnection.getInstance();
    }

    /**
     * Retrieve all landlords from the database.
     * @return List of all Landlord objects
     * @throws SQLException
     */
    public List<Landlord> getAllLandlords() throws SQLException {
        String sql = "SELECT * FROM Landlord";
        List<Landlord> landlords = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
            landlords.add(new Landlord(
                results.getInt("LLID"),
                results.getString("Name"),
                results.getString("PhoneNum"),
                results.getString("Email")
            ));
            }
        }

        return landlords;
    }

    /**
     * Retrieve a landlord by their LLID.
     * @param LLID Landlord ID
     * @return Landlord object or null if not found
     * @throws SQLException
     */
    public Landlord getLandlordByID(int LLID) throws SQLException {
        String sql = "SELECT * FROM Landlord WHERE LLID = ?";
        Landlord landlord = null;
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setInt(1, LLID);
            try (ResultSet results = stmt.executeQuery();) {
                if (results.next()) {
                    landlord = new Landlord(
                        results.getInt("LLID"),
                        results.getString("Name"),
                        results.getString("PhoneNum"),
                        results.getString("Email")
                    );
                }
            }
        }

        return landlord;
    }

    /**
     * DEPRECATED: This method is too similar to getLandlordTenantStats().
     * Used previously as an advanced query example.
     * Advanced Query: Retrieve landlords with the most tenants.
     * @return List of Landlord objects with tenant counts
     * @throws SQLException
     */
    // public L)ist<LandlordTenantStats> getLandlordsWithMostTenants() throws SQLException {
    //     String sql = "SELECT l.LLID, l.Name AS LandlordName, COUNT(t.SSN) AS TotalTenants " +
    //                  "FROM Landlord l " +
    //                  "JOIN Property p ON l.LLID = p.LLID " +
    //                  "LEFT JOIN LivesIn li ON p.PID = li.PID " +
    //                  "LEFT JOIN Tenant t ON li.SSN = t.SSN " +
    //                  "GROUP BY l.LLID, l.Name " +
    //                  "HAVING TotalTenants >= 1 " +
    //                  "ORDER BY TotalTenants DESC, LandlordName ASC " +
    //                  "LIMIT 10";
    //     List<LandlordTenantStats> stats = new ArrayList<>();

    //     try (
    //         PreparedStatement stmt = db.getConnection().prepareStatement(sql);
    //         ResultSet results = stmt.executeQuery();
    //     ) {
    //         while (results.next()) {
    //             stats.add(new LandlordTenantStats(
    //                 results.getInt("LLID"),
    //                 results.getString("LandlordName"),
    //                 results.getInt("TotalTenants")
    //             ));
    //         }
    //     }

    //     return stats;
    // }

    /**
     * Advanced Query: Find first 20 landlords with properties that have more than 2 tenants.
     * Offset by 5 because the first 5 are outliers with extremely high tenant counts.
     * (Written by Rohan Hari, integrated by Andrew Peirce)
     * 
     * @return List of LandlordTenantStats objects
     * @throws SQLException
     */
    public List<LandlordTenantStats> getLandlordTenantStats() throws SQLException {
        String sql = "SELECT ll.LLID, ll.Name, COUNT(t.SSN) AS TotalTenants " +
                     "FROM Landlord ll LEFT JOIN Property p ON ll.LLID = p.LLID " +
                     "LEFT JOIN LivesIn li ON p.PID = li.PID " +
                     "LEFT JOIN Tenant t ON li.SSN = t.SSN " +
                     "GROUP BY ll.LLID, ll.Name " +
                     "HAVING COUNT(t.SSN) > 2 " +
                     "ORDER BY TotalTenants DESC " +
                     "LIMIT 20 " +
                     "OFFSET 5";

        List<LandlordTenantStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new LandlordTenantStats(
                    results.getInt("LLID"),
                    results.getString("Name"),
                    results.getInt("TotalTenants")
                ));
            }
        }

        return stats;
    }

    /**
     * Advanced Query: Retrieve landlords with more than 3 available properties.
     * (Written by Jacob Rogers, integrated by Andrew Peirce)
     * 
     * @return List of LandlordPropertyStats objects
     * @throws SQLException
     */
    public List<LandlordPropertyStats> getLandlordsWithAvailableProperties() throws SQLException {
        String sql = "SELECT l.LLID, l.Name, l.Email, COUNT(p.PID) AS AvailableProperties " +
                     "FROM Landlord l " +
                     "JOIN Property AS p ON l.LLID = p.LLID " +
                     "LEFT JOIN LivesIn AS li ON p.PID = li.PID " +
                     "WHERE li.PID IS NULL " +
                     "GROUP BY l.LLID, l.Name, l.Email " +
                     "HAVING COUNT(p.PID) > 3";
        List<LandlordPropertyStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new LandlordPropertyStats(
                    results.getInt("LLID"),
                    results.getString("Name"),
                    results.getString("Email"),
                    results.getInt("AvailableProperties")
                ));
            }
        }
        return stats;
    }

    /**
     * Advanced Query: Retrieve landlords and their properties with bed and bath counts.
     * (Written by Jacob Rogers, integrated by Andrew Peirce)
     * 
     * @return List of LandlordBedBathStats objects
     * @throws SQLException
     */
    public List<LandlordBedBathStats> getLandlordBedBathStats() throws SQLException {
        String sql = "SELECT l.LLID AS LandlordID, l.Name AS LandlordName, p.Bed, p.Bath, COUNT(*) AS PropertyCount " +
                     "FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID " +
                     "GROUP BY l.LLID, l.Name, p.Bed, p.Bath " +
                     "ORDER BY l.LLID, PropertyCount DESC";

        List<LandlordBedBathStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new LandlordBedBathStats(
                    results.getInt("LandlordID"),
                    results.getString("LandlordName"),
                    results.getInt("Bed"),
                    results.getDouble("Bath"),
                    results.getInt("PropertyCount")
                ));
            }
        }

        return stats;
    }

    /**
     * DEPRECATED: This method is similar to getLandlordTenantStats(). Use that instead.
     * 
     * Advanced Query: Retrieve landlords along with their tenant counts without offset. Inclusive of all landlords.
     * (Written by Jacob Rogers, integrated by Andrew Peirce)
     * 
     * @return List of LandlordTenantStats objects
     * @throws SQLException
     */
    public List<LandlordTenantStats> getLandlordTenantsNoOffset() throws SQLException {
        String sql = "SELECT l.LLID, l.Name AS LandlordName, COUNT(t.SSN) AS NumTenants " +
                     "FROM Landlord AS l JOIN Property AS p ON l.LLID = p.LLID " +
                     "JOIN LivesIn AS li ON p.PID = li.PID " +
                     "JOIN Tenant AS t ON li.SSN = t.SSN " +
                     "GROUP BY l.LLID, l.Name " +
                     "ORDER BY NumTenants DESC";

        List<LandlordTenantStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new LandlordTenantStats(
                    results.getInt("LLID"),
                    results.getString("LandlordName"),
                    results.getInt("NumTenants")
                ));
            }
        }

        return stats;
    }

    /**
     * Insert a new landlord into the database.
     * @param ll Landlord object to insert
     * @throws SQLException
     */
    public void insertLandlord(Landlord ll) throws SQLException {
        String sql = "INSERT INTO Landlord (Name, PhoneNum, Email) VALUES (?, ?, ?)";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setString(1, ll.getName());
            stmt.setString(2, ll.getPhoneNum());
            stmt.setString(3, ll.getEmail());
            stmt.executeUpdate();
        }
    }

    /**
     * Update an existing landlord in the database.
     * @param ll Landlord object with updated values
     * @throws SQLException
     */
    public void updateLandlord(Landlord ll) throws SQLException {
        String sql = "UPDATE Landlord SET Name = ?, PhoneNum = ?, Email = ? WHERE LLID = ?";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setString(1, ll.getName());
            stmt.setString(2, ll.getPhoneNum());
            stmt.setString(3, ll.getEmail());
            stmt.setInt(4, ll.getLLID());
            stmt.executeUpdate();
        }
    }

    /**
     * Delete a landlord from the database.
     * @param LLID Landlord ID
     * @throws SQLException
     */
    public void deleteLandlord(int LLID) throws SQLException {
        // To maintain referential integrity, delete related records first
        // (Could call delete method from PropertyDAO but this would create a dependency)
        String deleteFromLivesIn = "DELETE FROM LivesIn WHERE PID IN (SELECT PID FROM Property WHERE LLID = ?)";
        String deleteFromLeasesFrom = "DELETE FROM LeasesFrom WHERE PID IN (SELECT PID FROM Property WHERE LLID = ?)";
        String deleteFromProperty = "DELETE FROM Property WHERE LLID = ?";
        String deleteFromLandlord = "DELETE FROM Landlord WHERE LLID = ?";

        Connection conn = db.getConnection();
        boolean originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try (
            PreparedStatement stmt0 = conn.prepareStatement(deleteFromLivesIn);
            PreparedStatement stmt1 = conn.prepareStatement(deleteFromLeasesFrom);
            PreparedStatement stmt2 = conn.prepareStatement(deleteFromProperty);
            PreparedStatement stmt3 = conn.prepareStatement(deleteFromLandlord);
        ) {
            // Delete from LivesIn table
            stmt0.setInt(1, LLID);
            stmt0.executeUpdate();

            // Delete from LeasesFrom table
            stmt1.setInt(1, LLID);
            stmt1.executeUpdate();

            // Delete from Property table
            stmt2.setInt(1, LLID);
            stmt2.executeUpdate();

            // Delete from Landlord table
            stmt3.setInt(1, LLID);
            int rows = stmt3.executeUpdate();

            if (rows == 0) {
                throw new SQLException("No landlord found with LLID: " + LLID);
            }

            conn.commit(); // only commit if all deletions succeed
        } catch (SQLException e) {
            conn.rollback(); // undo changes on error
            throw e;
        } finally {
            conn.setAutoCommit(originalAutoCommit); // restore original setting
        }
    }

    // public static void main(String[] args) {
    //     try {
    //         // DBConnection.getInstance().connect();
    //         // LandlordDAO landlordDAO = new LandlordDAO();

            

    //         // Example usage: Insert a new landlord
    //         // Landlord newLandlord = new Landlord("TEST-LANDLORD", "123-555-1234", "alice.smith@example.com");
    //         // landlordDAO.insertLandlord(newLandlord);
    //         // landlordDAO.deleteLandlord(47);
    //         // System.out.println("Deleted landlord successfully.");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
