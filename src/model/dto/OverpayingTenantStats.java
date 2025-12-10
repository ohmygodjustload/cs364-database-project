/**
 * A Data Transfer Object (DTO) class representing Overpaying Tenant Statistics.
 * Modeled after the advanced query returning overpaying tenants.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 9, 2025
 */
package model.dto;

public class OverpayingTenantStats {

    private final String ssn;
    private final String tenantName;
    private final int pid;
    private final String address;
    private final int bed;
    private final double price;
    private final double rentPerBed;

    public OverpayingTenantStats(String ssn, String tenantName, int pid, String address, int bed, double price, double rentPerBed) {
        this.ssn = ssn;
        this.tenantName = tenantName;
        this.pid = pid;
        this.address = address;
        this.bed = bed;
        this.price = price;
        this.rentPerBed = rentPerBed;
    }

    public String getSsn() {
        return ssn;
    }

    public String getTenantName() {
        return tenantName;
    }

    public int getPid() {
        return pid;
    }

    public String getAddress() {
        return address;
    }

    public int getBed() {
        return bed;
    }

    public double getPrice() {
        return price;
    }

    public double getRentPerBed() {
        return rentPerBed;
    }
}
