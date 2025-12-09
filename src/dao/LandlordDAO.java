/**
 * Data Access Object (DAO) for Landlord entity.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 7, 2025
 */
package dao;

import db.DBConnection;
import model.Landlord;
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
     * Advanced Query: Retrieve landlords with the most tenants.
     * @return List of Landlord objects with tenant counts
     * @throws SQLException
     */
    public List<Landlord> getLandlordsWithMostTenants() throws SQLException {
        String sql = "SELECT l.LLID, l.Name AS LandlordName, COUNT(t.SSN) AS TotalTenants " +
                     "FROM Landlord l " +
                     "JOIN Property p ON l.LLID = p.LLID " +
                     "LEFT JOIN LivesIn li ON p.PID = li.PID " +
                     "LEFT JOIN Tenant t ON li.SSN = t.SSN " +
                     "GROUP BY l.LLID, l.Name " +
                     "HAVING TotalTenants >= 1 " +
                     "ORDER BY TotalTenants DESC, LandlordName ASC " +
                     "LIMIT 10";
        List<Landlord> landlords = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                landlords.add(new Landlord(
                    results.getInt("LLID"),
                    results.getString("LandlordName"),
                    results.getInt("TotalTenants")
                ));
            }
        }

        return landlords;
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

    public static void main(String[] args) {
        try {
            DBConnection.getInstance().connect();
            LandlordDAO landlordDAO = new LandlordDAO();

            // Get most tenants landlords
            List<Landlord> landlords = landlordDAO.getLandlordsWithMostTenants();
            for (Landlord ll : landlords) {
                System.out.println(ll.toStringWithTenants());
            }

            // Example usage: Insert a new landlord
            // Landlord newLandlord = new Landlord("TEST-LANDLORD", "123-555-1234", "alice.smith@example.com");
            // landlordDAO.insertLandlord(newLandlord);
            // landlordDAO.deleteLandlord(47);
            // System.out.println("Deleted landlord successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
