/**
 * DTO for Property Tenant Statistics.
 * Modeled after the advanced query returning number of tenants per property, but only for properteis with 'St' in the address.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 11, 2025
 */
package model.dto;

public class PropertyTenantStats {

    private final int pid;
    private final String address;
    private final int numTenants;

    public PropertyTenantStats(int pid, String address, int numTenants) {
        this.pid = pid;
        this.address = address;
        this.numTenants = numTenants;
    }

    public int getPid() {
        return pid;
    }

    public String getAddress() {
        return address;
    }

    public int getNumTenants() {
        return numTenants;
    }
}
