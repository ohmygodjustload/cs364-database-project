/**
 * A POJO class representing a Landlord entity.
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */
public class Landlord {
    private int LLID;
    private String name;
    private String phoneNum;
    private String email;

    public Landlord(int LLID, String name, String phoneNum, String email) {
        this.LLID = LLID;
        this.name = name;
        this.phoneNum = phoneNum;
        this.email = email;
    }

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

    @Override
    public String toString() {
        return "Landlord{" +
                "LLID=" + LLID +
                ", name='" + name + '\'' +
                '}';
    }
}
