/**
 * Data Transfer Object (DTO) for Tenant Budget Statistics.
 * Modeled after the advanced query returning tenants with budgets
 * higher than the average budget of all tenants in their properties.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 10, 2025
 */
package model.dto;

public class TenantBudgetStats {

    private final String fName;
    private final String lName;
    private final double budget;
    private final int pid;

    public TenantBudgetStats(String fName, String lName, double budget, int pid) {
        this.fName = fName;
        this.lName = lName;
        this.budget = budget;
        this.pid = pid;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public double getBudget() {
        return budget;
    }

    public int getPid() {
        return pid;
    }
}
