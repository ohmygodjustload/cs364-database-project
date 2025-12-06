package db;

/**
 * A POJO class representing a Landlord entity.
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */
public class Landlord {
    // Attributes
    private int LLID; // Primary Key
    private String name;
    private String phoneNum;
    private String email;

    // Constructor
    public Landlord(int LLID, String name, String phoneNum, String email) {
        this.LLID = LLID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    // Overloaded Constructor without LLID (for inserts)
    public Landlord(String name, String phoneNum, String email) {
        // this.LLID = LLID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    // Getters
    public int getLLID() {
        return LLID;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setLLID(int LLID) {
        this.LLID = LLID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // To String
    @Override
    public String toString() {
        return "Landlord{" +
                "LLID=" + LLID +
                ", name='" + name + '\'' +
                '}';
    }
}
