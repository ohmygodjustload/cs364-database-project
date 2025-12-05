/**
 * A POJO class representing a Property entity.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */
public class Property {
    // Attributes
    private int PID; // Primary Key
    private int LLID; // Foreign Key
    private double price;
    private int bed;
    private double bath;
    private boolean petsAllowed;
    private String address;

    // Constructor
    public Property(int PID, int LLID, double price, int bed, double bath, boolean petsAllowed, String address) {
        this.PID = PID;
        this.LLID = LLID;
        this.price = price;
        this.bed = bed;
        this.bath = bath;
        this.petsAllowed = petsAllowed;
        this.address = address;
    }

    // Overloaded Constructor without PID (for inserts)
    public Property(int LLID, double price, int bed, double bath, boolean petsAllowed, String address) {
        this.LLID = LLID;
        this.price = price;
        this.bed = bed;
        this.bath = bath;
        this.petsAllowed = petsAllowed;
        this.address = address;
    }

    // Getters
    public int getPID() {
        return PID;
    }

    public int getLLID() {
        return LLID;
    }

    public double getPrice() {
        return price;
    }

    public int getBed() {
        return bed;
    }

    public double getBath() {
        return bath;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public String getAddress() {
        return address;
    }

    // Setters
    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setLLID(int LLID) {
        this.LLID = LLID;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setBed(int bed) {
        this.bed = bed;
    }

    public void setBath(double bath) {
        this.bath = bath;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // To String
    @Override
    public String toString() {
        return "Property{" +
                "PID=" + PID +
                ", bed=" + bed +
                '}';
    }
}
