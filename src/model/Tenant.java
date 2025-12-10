/**
 * A POJO class representing a Tenant entity.
 * @author Andrew Peirce
 * Date Last Modified: December 1, 2025
 */ 
package model;

import java.time.LocalDate;

public class Tenant {
    // Attributes
    private String SSN; // Primary Key
    private String Fname;
    private String Mname;
    private String Lname;
    private double budget;
    private String phoneNum;
    private String email;
    private LocalDate birthDate;

    // Constructor
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

    // Getters
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

    // Setters
    public void setSSN(String SSN) {
        this.SSN = SSN;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public void setMname(String Mname) {
        this.Mname = Mname;
    }

    public void setLname(String Lname) {
        this.Lname = Lname;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    // To String
    @Override
    public String toString() {
        return "Tenant{" +
                "SSN='" + SSN + '\'' +
                ", Fname='" + Fname + '\'' +
                '}';
    }
}
