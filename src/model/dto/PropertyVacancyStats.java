/**
 * A Data Transfer Object (DTO) class representing Property Vacancy Statistics.
 * Modeled after the advanced query returning property vacancy stats.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 9, 2025
 */
package model.dto;

public class PropertyVacancyStats {

    private final int pid;
    private final String address;
    private final double price;
    private final int bed;
    private final int currentOccupancy;
    private final int vacantBeds;
    private final String landlordName;

    /**
     * Constructor including landlordName.
     * @param pid
     * @param address
     * @param price
     * @param bed
     * @param currentOccupancy
     * @param vacantBeds
     * @param landlordName
     */
    public PropertyVacancyStats(int pid, String address, double price, int bed, int currentOccupancy, int vacantBeds, String landlordName) {
        this.pid = pid;
        this.address = address;
        this.price = price;
        this.bed = bed;
        this.currentOccupancy = currentOccupancy;
        this.vacantBeds = vacantBeds;
        this.landlordName = landlordName;
    }

    /**
     * Constructor without landlordName for backward compatibility.
     * Used in cases where landlordName is not required, such as certain advanced queries.
     * @param pid
     * @param address
     * @param price
     * @param bed
     * @param currentOccupancy
     * @param vacantBeds
     */
    public PropertyVacancyStats(int pid, String address, double price, int bed, int currentOccupancy, int vacantBeds) {
        this.pid = pid;
        this.address = address;
        this.price = price;
        this.bed = bed;
        this.currentOccupancy = currentOccupancy;
        this.vacantBeds = vacantBeds;
        this.landlordName = null;
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

    public int getBed() {
        return bed;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public int getVacantBeds() {
        return vacantBeds;
    }

    public String getLandlordName() {
        return landlordName;
    }
}
