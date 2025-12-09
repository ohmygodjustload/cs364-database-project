/**
 * Driver class for the application.
 * 
 * @author Andrew Peirce
 * Date Last Modified: December 9, 2025
 */
package app;

import javax.swing.SwingUtilities;
import db.DBConnection;
import gui.GUI;

public class Driver {
    
    public static void main(String[] args) {
        System.out.println("Starting Rental Management System...");

        try {
            DBConnection.getInstance().connect();
            System.out.println("Database connected successfully.");
        } catch (Exception e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            return;
        }

        SwingUtilities.invokeLater(() -> {
            new GUI(); // Launch the GUI
        });
    }
}
