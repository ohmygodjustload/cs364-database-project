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
import db.DBConnection;
import model.Property;
import model.Tenant;
import model.Landlord;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class GUI {

    private JFrame frame;
    private JTable propertyTable;
    private DefaultTableModel tableModel;

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
        propertyTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(propertyTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Left Button Panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JButton viewPropertiesButton = new JButton("View All Properties");
        JButton addTenantButton = new JButton("Add Tenant");
        JButton updateTenantButton = new JButton("Update Tenant");
        JButton deletePropertyButton = new JButton("Delete Property");
        JButton advancedQueryButton = new JButton("Run Advanced Query");

        leftPanel.add(viewPropertiesButton);
        leftPanel.add(addTenantButton);
        leftPanel.add(updateTenantButton);
        leftPanel.add(deletePropertyButton);
        leftPanel.add(advancedQueryButton);
        
        frame.add(leftPanel, BorderLayout.WEST);

        // Button Actions
        viewPropertiesButton.addActionListener(e -> loadAllProperties());

        addTenantButton.addActionListener(e -> showAddTenantDialog());

        updateTenantButton.addActionListener(e -> showUpdateTenantDialog());

        deletePropertyButton.addActionListener(e -> deleteSelectedProperty());

        advancedQueryButton.addActionListener(e -> runAdvancedQuery());

        frame.setVisible(true);
    }

    // DAO Calls
    private void loadAllProperties() {
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

    private void showAddTenantDialog() {
        JOptionPane.showMessageDialog(frame, 
            "This will open a form to insert a new tenant.\n(Implement via TenantDAO.insertTenant())",
            "Add Tenant",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void showUpdateTenantDialog() {
        JOptionPane.showMessageDialog(frame, 
            "This will open a form to update an existing tenant.\n(Implement via TenantDAO.updateTenant())",
            "Update Tenant",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void deleteSelectedProperty() {
        int selectedRow = propertyTable.getSelectedRow();
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
        JOptionPane.showMessageDialog(frame, 
            "This will call one of the advanced queries and display results in the table.",
            "Advanced Query",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        try {
            DBConnection.getInstance().connect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(GUI::new);
    }

}
