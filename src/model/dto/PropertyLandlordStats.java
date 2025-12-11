/**
 * Data Transfer Object (DTO) for Property Landlord Statistics.
 * Modeled after the advanced query returning properties whose rent is higher than that landlord's average.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 11, 2025
 */
package model.dto;

public class PropertyLandlordStats {

    private final int pid;
    private final String address;
    private final double price;
    private final int llid;
    private final String landlordName;

    public PropertyLandlordStats(int pid, String address, double price, int llid, String landlordName) {
        this.pid = pid;
        this.address = address;
        this.price = price;
        this.llid = llid;
        this.landlordName = landlordName;
    }

    public int getPid() {
        return pid;
    }

    public String getAddress() {
        return address;
    }

    public double getPrice() {
        return price;
    }

    public int getLlid() {
        return llid;
    }

    public String getLandlordName() {
        return landlordName;
    }
}
