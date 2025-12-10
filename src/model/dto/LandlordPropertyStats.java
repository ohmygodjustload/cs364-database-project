/**
 * Data Transfer Object (DTO) for Landlord Property Statistics.
 * Modeled after the advanced query returning landlord property counts.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 10, 2025
 */
package model.dto;

public class LandlordPropertyStats {

    private final int llid;
    private final String name;
    private final String email;
    private final int AvailableProperties;

    public LandlordPropertyStats(int llid, String name, String email, int availableProperties) {
        this.llid = llid;
        this.name = name;
        this.email = email;
        this.AvailableProperties = availableProperties;
    }

    public int getLlid() {
        return llid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getAvailableProperties() {
        return AvailableProperties;
    }
}
