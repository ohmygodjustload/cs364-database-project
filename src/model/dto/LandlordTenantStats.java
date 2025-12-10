/**
 * A Data Transfer Object (DTO) class representing Landlord Tenant Statistics.
 * Modeled after the advanced query returning landlord tenant counts.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 9, 2025
 */
package model.dto;

public class LandlordTenantStats {

    private final int llid;
    private final String name;
    private final int totalTenants;

    public LandlordTenantStats(int llid, String name, int totalTenants) {
        this.llid = llid;
        this.name = name;
        this.totalTenants = totalTenants;
    }

    public int getLlid() {
        return llid;
    }

    public String getName() {
        return name;
    }

    public int getTotalTenants() {
        return totalTenants;
    }
}
