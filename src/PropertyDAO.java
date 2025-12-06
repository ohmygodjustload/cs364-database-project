/**
 * Data Access Object (DAO) class for Property entities.
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

public class PropertyDAO {

    private final DBConnection db;

    public PropertyDAO() {
        db = DBConnection.getInstance();
    }

    // TODO: Implement methods for Property data access

    public List<Property> getAllProperties() throws SQLException {
        // Process results and populate properties list
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Property");
        List<Property> properties = new ArrayList<>();
        ResultSet results = stmt.executeQuery();

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

        return properties;
    }

    public Property getPropertyByID(int PID) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement("SELECT * FROM Property WHERE PID = ?");
        Property property = null;
        stmt.setInt(1, PID);
        ResultSet results = stmt.executeQuery();
        if (results.next()) {
            property = new Property(
                results.getInt("PID"),
                results.getInt("LLID"),
                results.getDouble("price"),
                results.getInt("bed"),
                results.getDouble("bath"),
                results.getBoolean("petsAllowed"),
                results.getString("address")
            );
        }

        return property;
    }

    public List<Property> getPropertiesByLandlordID(int LLID) {
        // Implementation here
        return new ArrayList<>();
    }

    public void insertProperty(Property p) throws SQLException {
        // Implementation here
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "INSERT INTO Property (LLID, Price, Bed, Bath, PetsAllowed, Address) VALUES (?, ?, ?, ?, ?, ?)"
        );
        stmt.setInt(1, p.getLLID());
        stmt.setDouble(2, p.getPrice());
        stmt.setInt(3, p.getBed());
        stmt.setDouble(4, p.getBath());
        stmt.setBoolean(5, p.isPetsAllowed());
        stmt.setString(6, p.getAddress());
        stmt.executeUpdate();
    }

    public void updateProperty(Property p) throws SQLException {
        PreparedStatement stmt = db.getConnection().prepareStatement(
            "UPDATE Property SET Price=?, Bed=?, Bath=?, PetsAllowed=?, Address=? WHERE PID=?"
        );

        stmt.setDouble(1, p.getPrice());
        stmt.setInt(2, p.getBed());
        stmt.setDouble(3, p.getBath());
        stmt.setBoolean(4, p.isPetsAllowed());
        stmt.setString(5, p.getAddress());
        stmt.setInt(6, p.getPID());
        stmt.executeUpdate();
    }

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
    //         new PropertyDAO().insertProperty(new Property(47, 1600.0, 4, 2, true, "124 Heck St, Anytown, USA"));
    //         System.out.println("Inserted property successfully.");
    //     } catch (SQLException e) {
    //         // TODO Auto-generated catch block
    //         e.printStackTrace();
    //     }
    // }
}