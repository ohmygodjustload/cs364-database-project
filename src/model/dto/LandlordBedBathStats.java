/**
 * DTO class for Landlord Bed and Bath Statistics.
 * Modeled after the advanced query returning landlords and their properties with bed and bath counts.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 11, 2025
 */
package model.dto;

public class LandlordBedBathStats {

    private final int llid;
    private final String name;
    private final int bedCount;
    private final double bathCount;
    private final int propertyCount;

    public LandlordBedBathStats(int llid, String name, int bedCount, double bathCount, int propertyCount) {
        this.llid = llid;
        this.name = name;
        this.bedCount = bedCount;
        this.bathCount = bathCount;
        this.propertyCount = propertyCount;
    }

    public int getLlid() {
        return llid;
    }

    public String getName() {
        return name;
    }

    public int getBedCount() {
        return bedCount;
    }

    public double getBathCount() {
        return bathCount;
    }

    public int getPropertyCount() {
        return propertyCount;
    }
}
