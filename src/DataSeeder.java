import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This class seeds LivesIn relationships between Tenants and Properties into the database.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 4, 2025
 */

public class DataSeeder {

    public void seedLivesInRelationships() {
        // Implementation here
        List<Tenant> tenants = null;
        try {
            tenants = new TenantDAO().getAllTenants();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Property> properties = null;
        try {
            properties = new PropertyDAO().getAllProperties();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Map<Property, List<Tenant>> occupancyMap = new HashMap<>();
        Map<Property, Integer> capacityMap = new HashMap<>();
        Random rand = new Random();
        Set<Tenant> usedTenants = new HashSet<>();

        // Initialize occupancyMap
        for (Property p : properties) {
            occupancyMap.put(p, new ArrayList<>());
        }

        // Randomly determine target occupancy for each property
        for (Property p : occupancyMap.keySet()) {
            int maxCapacity = p.getBed();
            int targetOccupancy = rand.nextInt(maxCapacity + 1); // 0 to maxCapacity

            capacityMap.put(p, targetOccupancy);
        }

        Collections.shuffle(tenants);
        // Assign shuffled tenants to properties
        int tenantIndex = 0;
        for (Property p : occupancyMap.keySet()) {
            int slots = capacityMap.get(p);

            while (slots > 0 && tenantIndex < tenants.size()) {
                Tenant t = tenants.get(tenantIndex);

                if (!usedTenants.contains(t)) {
                    occupancyMap.get(p).add(t);
                    usedTenants.add(t);
                    slots--;
                }

                tenantIndex++;
            }
        }

    }

    public void seedLeasesFromRelationships() {
        // Implementation here

    }

    public static void main(String[] args) {
        DataSeeder seeder = new DataSeeder();
        seeder.seedLivesInRelationships();
        seeder.seedLeasesFromRelationships();
    }
}