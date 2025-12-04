/**
 * A POJO class representing a Tenant entity.
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */ 
import java.time.LocalDate;

public class Tenant {
    private String SSN;
    private String Fname;
    private String Mname;
    private String Lname;
    private double budget;
    private String phoneNum;
    private String email;
    private LocalDate birthDate;

    public Tenant(String SSN, String Fname, String Mname, String Lname, double budget, String phoneNum, String email, LocalDate birthDate) {
        this.SSN = SSN;
        this.Fname = Fname;
        this.Mname = Mname;
        this.Lname = Lname;
        this.budget = budget;
        this.phoneNum = phoneNum;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getSSN() {
        return SSN;
    }

    public String getFname() {
        return Fname;
    }

    public String getMname() {
        return Mname;
    }

    public String getLname() {
        return Lname;
    }

    public double getBudget() {
        return budget;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "Tenant{" +
                "SSN='" + SSN + '\'' +
                ", Fname='" + Fname + '\'' +
                '}';
    }
}
