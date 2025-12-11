/**
 * Data Access Object (DAO) for Tenant entities.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 7, 2025
 */
package dao;

import db.DBConnection;
import model.Tenant;
import model.dto.OverpayingTenantStats;
import model.dto.TenantBudgetStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TenantDAO {

    private final DBConnection db; // Singleton DB connection instance

    public TenantDAO() {
        db = DBConnection.getInstance(); // Initialize DB connection
    }
    
    /**
     * Retrieve all tenants from the database.
     * @return List of all Tenant objects
     * @throws SQLException
     */
    public List<Tenant> getAllTenants() throws SQLException {
        String sql = "SELECT * FROM Tenant";
        List<Tenant> tenants = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                tenants.add(new Tenant(
                    results.getString("SSN"),
                    results.getString("FName"),
                    results.getString("MName"),
                    results.getString("LName"),
                    results.getDouble("Budget"),
                    results.getString("PhoneNum"),
                    results.getString("Email"),
                    results.getDate("BirthDate").toLocalDate()
                ));
            }
        }
        
        return tenants;
    }

    /**
     * Retrieve a tenant by their SSN.
     * @param ssn Tenant's SSN
     * @return Tenant object or null if not found
     * @throws SQLException
     */
    public Tenant getTenantBySSN(String ssn) throws SQLException {
        String sql = "SELECT * FROM Tenant WHERE SSN = ?";
        Tenant tenant = null;
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setString(1, ssn);

            try (ResultSet results = stmt.executeQuery();) {
                if (results.next()) {
                    tenant = new Tenant(
                        results.getString("SSN"),
                        results.getString("FName"),
                        results.getString("MName"),
                        results.getString("LName"),
                        results.getDouble("Budget"),
                        results.getString("PhoneNum"),
                        results.getString("Email"),
                        results.getDate("BirthDate").toLocalDate()
                    );
                }
            }
        }
        
        return tenant;
    }

    /**
     * Advanced Query: Retrieve tenants paying above average rent-per-bedroom for their property type.
     * (Written and integrated by Andrew Peirce)
     * 
     * @return List of Tenant objects
     * @throws SQLException
     */
    public List<OverpayingTenantStats> getTenantsPayingAboveAverageRent() throws SQLException {
        String sql = "SELECT t.SSN, CONCAT(t.FName, ' ', t.LName) AS TenantName, p.PID, p.Address, p.Bed, p.Price, (p.Price / p.Bed) AS RentPerBed " +
                     "FROM Tenant t " +
                     "JOIN LivesIn li ON t.SSN = li.SSN " +
                     "JOIN Property p ON li.PID = p.PID " +
                     "WHERE (p.Price / p.Bed) > ( " +
                     "    SELECT AVG(p2.Price / p2.Bed) " +
                     "    FROM Property p2 " +
                     "    WHERE p2.Bed = p.Bed " +
                     ") " +
                     "ORDER BY RentPerBed DESC";

        List<OverpayingTenantStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new OverpayingTenantStats(
                    results.getString("SSN"),
                    results.getString("TenantName"),
                    results.getInt("PID"),
                    results.getString("Address"),
                    results.getInt("Bed"),
                    results.getDouble("Price"),
                    results.getDouble("RentPerBed")
                ));
            }
        }
        
        return stats;
    }

    /**
     * Advanced Query: Retrieve tenants with budgets above the average budget for their properties.
     * (Written by Rohan Hari, integrated by Andrew Peirce)
     * 
     * @return List of TenantBudgetStats objects
     * @throws SQLException
     */
    public List<TenantBudgetStats> getTenantsWithAboveAverageBudget() throws SQLException {
        String sql = "SELECT t.FName, t.LName, t.Budget, li.PID " +
                     "FROM Tenant t JOIN LivesIn li ON t.SSN = li.SSN " +
                     "WHERE t.Budget > ( " +
                        "SELECT AVG(t2.Budget) " +
                            "FROM LivesIn li2 JOIN Tenant t2 ON li2.SSN = t2.SSN " +
                            "WHERE li2.PID = li.PID) " +
                     "ORDER BY t.Budget DESC";

        List<TenantBudgetStats> stats = new ArrayList<>();

        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
            ResultSet results = stmt.executeQuery();
        ) {
            while (results.next()) {
                stats.add(new TenantBudgetStats(
                    results.getString("FName"),
                    results.getString("LName"),
                    results.getDouble("Budget"),
                    results.getInt("PID")
                ));
            }
        }

        return stats;
    }

    /**
     * Insert a new tenant into the database.
     * @param t Tenant object to insert
     * @throws SQLException
     */
    public void insertTenant(Tenant t) throws SQLException {
        String sql = "INSERT INTO Tenant (SSN, FName, MName, LName, Budget, PhoneNum, Email, BirthDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setString(1, t.getSSN());
            stmt.setString(2, t.getFname());
            stmt.setString(3, t.getMname());
            stmt.setString(4, t.getLname());
            stmt.setDouble(5, t.getBudget());
            stmt.setString(6, t.getPhoneNum());
            stmt.setString(7, t.getEmail());
            stmt.setDate(8, java.sql.Date.valueOf(t.getBirthDate()));
            stmt.executeUpdate();
        }
    }   

    /**
     * Update an existing tenant in the database.
     * @param t Tenant object with updated values
     * @throws SQLException
     */
    public void updateTenant(Tenant t) throws SQLException {
        String sql = "UPDATE Tenant SET FName = ?, MName = ?, LName = ?, Budget = ?, PhoneNum = ?, Email = ?, BirthDate = ? WHERE SSN = ?";
        try (
            PreparedStatement stmt = db.getConnection().prepareStatement(sql);
        ) {
            stmt.setString(1, t.getFname());
            stmt.setString(2, t.getMname());
            stmt.setString(3, t.getLname());
            stmt.setDouble(4, t.getBudget());
            stmt.setString(5, t.getPhoneNum());
            stmt.setString(6, t.getEmail());
            stmt.setDate(7, java.sql.Date.valueOf(t.getBirthDate()));
            stmt.setString(8, t.getSSN());
            stmt.executeUpdate();
        }
    }

    /**
     * Delete a tenant from the database.
     * @param ssn Tenant's SSN
     * @throws SQLException
     */
    public void deleteTenant(String ssn) throws SQLException {
        // To maintain referential integrity, delete related records first
        String deleteFromLivesIn = "DELETE FROM LivesIn WHERE SSN = ?";
        String deleteFromLeasesFrom = "DELETE FROM LeasesFrom WHERE SSN = ?";
        String deleteTenant = "DELETE FROM Tenant WHERE SSN = ?";

        Connection conn = db.getConnection();
        boolean originalAutoCommit = conn.getAutoCommit();
        conn.setAutoCommit(false);

        try (
            PreparedStatement stmt0 = conn.prepareStatement(deleteFromLivesIn);
            PreparedStatement stmt1 = conn.prepareStatement(deleteFromLeasesFrom);
            PreparedStatement stmt2 = conn.prepareStatement(deleteTenant);
        ) {
            // Delete from LivesIn table
            stmt0.setString(1, ssn);
            stmt0.executeUpdate();

            // Delete from LeasesFrom table
            stmt1.setString(1, ssn);
            stmt1.executeUpdate();

            // Delete from Tenant table
            stmt2.setString(1, ssn);
            int rows = stmt2.executeUpdate();

            if (rows == 0) {
                throw new SQLException("No tenant found with SSN: " + ssn);
            }
            
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(originalAutoCommit);
        }
    }

    // public static void main(String[] args) {
    //     try {
    //         DBConnection.getInstance().connect();
    //         new TenantDAO().insertTenant(new Tenant("000-00-0000", "John", "Andrew", "Doe", 1000.0, "123-456-7890", "john.doe@example.com", LocalDate.of(1990, 1, 1)));
    //         System.out.println("Inserted tenant successfully.");
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }
}
