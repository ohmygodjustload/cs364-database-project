/**
 * Graphical User Interface (GUI) for the Rental Management System.
 * Makes use of Swing components to provide an interactive interface.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 7, 2025
 */
package gui;

import dao.LandlordDAO;
import dao.PropertyDAO;
import dao.TenantDAO;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Landlord;
import model.Property;
import model.Tenant;

public class GUI {

    private JFrame frame;
    private JTable mainTable;
    private DefaultTableModel tableModel;
    private boolean isTenantView = false;

    // DAOs for database access
    private PropertyDAO propertyDAO;
    private TenantDAO tenantDAO;
    private LandlordDAO landlordDAO;

    public GUI() {
        // Initialize DAOs
        propertyDAO = new PropertyDAO();
        tenantDAO = new TenantDAO();
        landlordDAO = new LandlordDAO();

        initUI();
        // loadAllProperties();
    }

    private void initUI() {
        frame = new JFrame("Rental Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLayout(new BorderLayout());

        // Top Title
        JLabel titleLabel = new JLabel("Rental Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Center Table
        tableModel = new DefaultTableModel(
            new Object[]{"PID", "Address", "Beds", "Baths", "Price", "Pets"}, 0
        );
        mainTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(mainTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Left Button Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(10, 1, 10, 10));

        JButton viewPropertiesButton = new JButton("View All Properties");
        JButton addPropertyButton = new JButton("Add Property");
        // TODO - add update property button (Rohan will do this)
        JButton deletePropertyButton = new JButton("Delete Property");
        JButton viewAllTenantsButton = new JButton("View All Tenants");
        JButton addTenantButton = new JButton("Add Tenant");
        JButton updateTenantButton = new JButton("Update Tenant");
        JButton deleteTenantButton = new JButton("Delete Tenant");
        JButton viewAllLandlordsButton = new JButton("View All Landlords");
        JButton advancedQueryButton = new JButton("Run Advanced Query");

        leftPanel.add(viewPropertiesButton);
        leftPanel.add(addPropertyButton);
        // leftPanel.add(updatePropertyButton);
        leftPanel.add(deletePropertyButton);
        leftPanel.add(viewAllTenantsButton);
        leftPanel.add(addTenantButton);
        leftPanel.add(updateTenantButton);
        leftPanel.add(deleteTenantButton);
        leftPanel.add(viewAllLandlordsButton);
        leftPanel.add(advancedQueryButton);
        
        frame.add(leftPanel, BorderLayout.WEST);

        // Button Actions
        viewPropertiesButton.addActionListener(e -> loadAllProperties());

        addPropertyButton.addActionListener(e -> addProperty());

        // updatePropertyButton.addActionListener(e -> updateProperty());

        deletePropertyButton.addActionListener(e -> deleteSelectedProperty());

        viewAllTenantsButton.addActionListener(e -> loadAllTenants());

        addTenantButton.addActionListener(e -> runAddTenantDialog());

        updateTenantButton.addActionListener(e -> runUpdateTenantDialog());

        deleteTenantButton.addActionListener(e -> deleteSelectedTenant());

        viewAllLandlordsButton.addActionListener(e -> loadAllLandlords());

        advancedQueryButton.addActionListener(e -> runAdvancedQuery());

        frame.setVisible(true);
    }

    // DAO Calls
    private void loadAllProperties() {
        isTenantView = false;
        tableModel.setColumnIdentifiers(
            new Object[]{"PID", "Address", "Beds", "Baths", "Price", "Pets"}
        );
        tableModel.setRowCount(0); // Clear existing rows

        List<Property> properties = null;
        try {
            properties = propertyDAO.getAllProperties();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Property p : properties) {
            tableModel.addRow(new Object[]{
                p.getPID(),
                p.getAddress(),
                p.getBed(),
                p.getBath(),
                p.getPrice(),
                p.isPetsAllowed() ? "Yes" : "No"
            });
        }
    }

    private void loadAllTenants() {
        isTenantView = true;
        tableModel.setColumnIdentifiers(
            new Object[]{"SSN", "First Name", "Last Name", "Budget", "Phone", "Email"}
        );
        tableModel.setRowCount(0); // Clear existing rows

        try {
            List<Tenant> tenants = tenantDAO.getAllTenants();
            for (Tenant t : tenants) {
                tableModel.addRow(new Object[]{
                    t.getSSN(),
                    t.getFname(),
                    t.getLname(),
                    t.getBudget(),
                    t.getPhoneNum(),
                    t.getEmail()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load tenants: " + e.getMessage());
        }
    }

    private void loadAllLandlords() {
        isTenantView = false;
        tableModel.setColumnIdentifiers(
            new Object[]{"LLID", "Name", "PhoneNum", "Email"}
        );
        tableModel.setRowCount(0);
        try {
            List<Landlord> landlords = landlordDAO.getAllLandlords();
            for(Landlord l : landlords) {
                tableModel.addRow(new Object[]{
                    l.getLLID(),
                    l.getName(),
                    l.getPhoneNum(),
                    l.getEmail()
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Failed to load landlords: " + e.getMessage());
        }
    }

    private void runAddTenantDialog() {
        if (!isTenantView) {
            JOptionPane.showMessageDialog(frame, "Switch to Tenant view first.");
            return;
        }
        JTextField ssnField = new JTextField();
        JTextField fNameField = new JTextField();
        JTextField mNameField = new JTextField();
        JTextField lNameField = new JTextField();
        JTextField budgetField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField birthDateField = new JTextField("YYYY-MM-DD");

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        panel.add(new JLabel("SSN:")); panel.add(ssnField);
        panel.add(new JLabel("First Name:")); panel.add(fNameField);
        panel.add(new JLabel("Middle Name:")); panel.add(mNameField);
        panel.add(new JLabel("Last Name:")); panel.add(lNameField);
        panel.add(new JLabel("Budget:")); panel.add(budgetField);
        panel.add(new JLabel("Phone Number:")); panel.add(phoneField);
        panel.add(new JLabel("Email:")); panel.add(emailField);
        panel.add(new JLabel("Birth Date:")); panel.add(birthDateField);

        int result = JOptionPane.showConfirmDialog(frame, panel, 
            "Add New Tenant", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Tenant tenant = new Tenant(
                    ssnField.getText(),
                    fNameField.getText(),
                    mNameField.getText(),
                    lNameField.getText(),
                    Double.parseDouble(budgetField.getText()),
                    phoneField.getText(),
                    emailField.getText(),
                    java.time.LocalDate.parse(birthDateField.getText())
                );

                tenantDAO.insertTenant(tenant);
                JOptionPane.showMessageDialog(frame, "Tenant added successfully.");
                loadAllTenants();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid input:\n" + e.getMessage());
            }
        }
    }

    private void runUpdateTenantDialog() {
        if (!isTenantView) {
            JOptionPane.showMessageDialog(frame, "Switch to Tenant view first.");
            return;
        }

        int selectedRow = mainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Select a tenant first");
            return;
        }

        String ssn = tableModel.getValueAt(selectedRow, 0).toString();

        try {
            Tenant t = tenantDAO.getTenantBySSN(ssn);

            JTextField fNameField = new JTextField(t.getFname());
            JTextField mNameField = new JTextField(t.getMname());
            JTextField lNameField = new JTextField(t.getLname());
            JTextField budgetField = new JTextField(String.valueOf(t.getBudget()));
            JTextField phoneField = new JTextField(t.getPhoneNum());
            JTextField emailField = new JTextField(t.getEmail());
            JTextField birthDateField = new JTextField(t.getBirthDate().toString());
            
            JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
            panel.add(new JLabel("First Name:")); panel.add(fNameField);
            panel.add(new JLabel("Middle Name:")); panel.add(mNameField);
            panel.add(new JLabel("Last Name:")); panel.add(lNameField);
            panel.add(new JLabel("Budget:")); panel.add(budgetField);
            panel.add(new JLabel("Phone Number:")); panel.add(phoneField);
            panel.add(new JLabel("Email:")); panel.add(emailField);
            panel.add(new JLabel("Birth Date:")); panel.add(birthDateField);

            int result = JOptionPane.showConfirmDialog(frame, panel, 
                "Update Tenant", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
            );

            if (result == JOptionPane.OK_OPTION) {
                Tenant updated = new Tenant(
                    ssn,
                    fNameField.getText(),
                    mNameField.getText(),
                    lNameField.getText(),
                    Double.parseDouble(budgetField.getText()),
                    phoneField.getText(),
                    emailField.getText(),
                    java.time.LocalDate.parse(birthDateField.getText())
                );

                tenantDAO.updateTenant(updated);
                JOptionPane.showMessageDialog(frame, "Tenant updated successfully!");
                loadAllTenants();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
    }

    private void deleteSelectedTenant() {
        if (!isTenantView) {
            JOptionPane.showMessageDialog(frame, "Switch to Tenant view first.");
            return;
        }

        int selectedRow = mainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Select a tenant first");
            return;
        }

        String ssn = (String) tableModel.getValueAt(selectedRow, 0);
        try {
            tenantDAO.deleteTenant(ssn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllTenants();
    }

    private void addProperty(){
        if(isTenantView) {
            JOptionPane.showMessageDialog(frame, "Switch to Property view first.");
            return;
        }
        // JTextField PIDField = new JTextField();
        JTextField LLIDField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField bedField = new JTextField();
        JTextField bathField = new JTextField();
        JTextField petsField = new JTextField();
        JTextField addressField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));
        // panel.add(new JLabel("PID:")); panel.add(PIDField);
        panel.add(new JLabel("LLID:")); panel.add(LLIDField);
        panel.add(new JLabel("Price:")); panel.add(priceField);
        panel.add(new JLabel("Beds:")); panel.add(bedField);
        panel.add(new JLabel("Baths:")); panel.add(bathField);
        panel.add(new JLabel("Pets Allowed:")); panel.add(petsField);
        panel.add(new JLabel("Address:")); panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(frame, panel, 
            "Add New Property", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                Property property = new Property(
                    // Integer.parseInt(PIDField.getText()),
                    Integer.parseInt(LLIDField.getText()),
                    Double.parseDouble(priceField.getText()),
                    Integer.parseInt(bedField.getText()),
                    Double.parseDouble(bathField.getText()),
                    Boolean.parseBoolean(petsField.getText()),
                    addressField.getText()
                );

                propertyDAO.insertProperty(property);
                JOptionPane.showMessageDialog(frame, "Property added successfully.");
                loadAllProperties();
            } catch ( Exception e) {
                JOptionPane.showMessageDialog(frame, "Invalid input:\n" + e.getMessage());
            }
        }
    }

    private void deleteSelectedProperty() {
        if(isTenantView) {
            JOptionPane.showMessageDialog(frame, "Switch to Property view first.");
            return;
        }
        
        int selectedRow = mainTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Select a property first");
            return;
        }

        int pid = (int) tableModel.getValueAt(selectedRow, 0);
        try {
            propertyDAO.deleteProperty(pid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadAllProperties();
    }


    private void runAdvancedQuery() {
        String[] options = {
            "Top 10 Most Expensive Properties (with Vacancy)",
            "Landlords with Most Tenants",
            "Tenants Paying Above Average Rent/Bed"
        };

        int choice = JOptionPane.showOptionDialog(
            frame,
            "Select an advanced query to run:",
            "Advanced Queries",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            options,
            options[0]
        );

        try {
            switch (choice) {
                case 0 -> runPropertyVacancyStatsQuery();
                case 1 -> runLandlordTenantStatsQuery();
                case 2 -> runOverpayingTenantStatsQuery();
                default -> {}
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Advanced Query failed: " + e.getMessage());
        }
    }

    private void runPropertyVacancyStatsQuery() throws SQLException {
        isTenantView = false;
        tableModel.setColumnIdentifiers(new Object[] {
            "PID", "Address", "Price", "Beds", "Occupied", "Vacant"
        });
        tableModel.setRowCount(0); // Clear existing rows

        var stats = propertyDAO.getMostExpensiveProperties();
        for (var s : stats) {
            tableModel.addRow(new Object[] {
                s.getPid(),
                s.getAddress(),
                s.getPrice(),
                s.getBed(),
                s.getCurrentOccupancy(),
                s.getVacantBeds()
            });
        }
    }

    private void runLandlordTenantStatsQuery() throws SQLException {
        isTenantView = false;
        tableModel.setColumnIdentifiers(new Object[] {
            "LLID", "Landlord Name", "Total Tenants"
        });
        tableModel.setRowCount(0); // Clear existing rows

        var stats = landlordDAO.getLandlordsWithMostTenants();
        for (var s : stats) {
            tableModel.addRow(new Object[] {
                s.getLlid(),
                s.getName(),
                s.getTotalTenants()
            });
        }
    }

    private void runOverpayingTenantStatsQuery() throws SQLException {
        isTenantView = true;
        tableModel.setColumnIdentifiers(new Object[] {
            "SSN", "Tenant Name", "PID", "Address", "Beds", "Price", "Rent/Bed"
        });
        tableModel.setRowCount(0); // Clear existing rows

        var stats = tenantDAO.getTenantsPayingAboveAverageRent();
        for (var s : stats) {
            tableModel.addRow(new Object[] {
                s.getSsn(),
                s.getTenantName(),
                s.getPid(),
                s.getAddress(),
                s.getBed(),
                s.getPrice(),
                s.getRentPerBed()
            });
        }
    }

    // public static void main(String[] args) {
    //     try {
    //         DBConnection.getInstance().connect();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     SwingUtilities.invokeLater(GUI::new);
    // }

}
