/**
 * Data Access Object (DAO) for Tenant entities.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 4, 2025
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TenantDAO {
    private final DBConnection db;

    public TenantDAO() {
        db = DBConnection.getInstance();
    }
    
    // TODO: Implement methods for Tenant data access

    public List<Tenant> getAllTenants() throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Tenant");
        List<Tenant> tenants = new ArrayList<>();
        ResultSet results = stmt.executeQuery();

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
        
        return tenants;
    }

    public Tenant getTenantBySSN(String ssn) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Tenant WHERE SSN = ?");
        Tenant tenant = null;
        stmt.setString(1, ssn);
        ResultSet results = stmt.executeQuery();

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
        
        return tenant;
    }

    public void insertTenant(Tenant t) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "INSERT INTO Tenant (SSN, FName, MName, LName, Budget, PhoneNum, Email, BirthDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
        );
        
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

    public void updateTenant(Tenant t) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "UPDATE Tenant SET FName = ?, MName = ?, LName = ?, Budget = ?, PhoneNum = ?, Email = ?, BirthDate = ? WHERE SSN = ?"
        );

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

    public void deleteTenant(String ssn) throws SQLException {
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
