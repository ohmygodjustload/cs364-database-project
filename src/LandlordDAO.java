/**
 * Data Access Object (DAO) for Landlord entity.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 4, 2025
 */
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

    // TODO: Implement methods for Landlord data access

    public List<Landlord> getAllLandlords() throws SQLException {
        
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Landlord");
        List<Landlord> landlords = new ArrayList<>();
        ResultSet results = stmt.executeQuery();

        while (results.next()) {
            landlords.add(new Landlord(
                results.getInt("LLID"),
                results.getString("Name"),
                results.getString("PhoneNum"),
                results.getString("Email")
            ));
        }

        return landlords;
    }

    public Landlord getLandlordByID(int LLID) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Landlord WHERE LLID = ?");
        Landlord landlord = null;
        stmt.setInt(1, LLID);
        ResultSet results = stmt.executeQuery();

        if (results.next()) {
            landlord = new Landlord(
                results.getInt("LLID"),
                results.getString("Name"),
                results.getString("PhoneNum"),
                results.getString("Email")
            );
        }

        return landlord;
    }

    public void insertLandlord(Landlord ll) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "INSERT INTO Landlord (Name, PhoneNum, Email) VALUES (?, ?, ?)"
        );
        
        stmt.setString(1, ll.getName());
        stmt.setString(2, ll.getPhoneNum());
        stmt.setString(3, ll.getEmail());
        stmt.executeUpdate();
    }

    public void updateLandlord(Landlord ll) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "UPDATE Landlord SET Name = ?, PhoneNum = ?, Email = ? WHERE LLID = ?"
        );
        stmt.setString(1, ll.getName());
        stmt.setString(2, ll.getPhoneNum());
        stmt.setString(3, ll.getEmail());
        stmt.setInt(4, ll.getLLID());
    }

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
    //         LandlordDAO landlordDAO = new LandlordDAO();

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
