/**
 * A POJO class representing a Property entity.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */
public class Property {
    private int PID;
    private int LLID;
    private double price;
    private int bed;
    private double bath;
    private boolean petsAllowed;
    private String address;

    public Property(int PID, int LLID, double price, int bed, double bath, boolean petsAllowed, String address) {
        this.PID = PID;
        this.LLID = LLID;
        this.price = price;
        this.bed = bed;
        this.bath = bath;
        this.petsAllowed = petsAllowed;
        this.address = address;
    }

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

    @Override
    public String toString() {
        return "Property{" +
                "PID=" + PID +
                ", bed=" + bed +
                '}';
    }
}
