/**
 * Data Access Object (DAO) class for Property entities.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 4, 2025
 */
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PropertyDAO {
    // TODO: Implement methods for Property data access

    public List<Property> getAllProperties() {
        // Implementation here
        List<Property> properties = new ArrayList<>();
        
        return new ArrayList<>();
    }

    public Property getPropertyByID(int PID) {
        // Implementation here
        return null;
    }

    public List<Property> getPropertiesByLandlordID(int LLID) {
        // Implementation here
        return new ArrayList<>();
    }

    public void insertProperty(Property p) {
        // Implementation here
    }

    public void updateProperty(Property p) {
        // Implementation here
    }

    public void deleteProperty(int PID) {
        // Implementation here
    }
}
