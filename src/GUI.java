import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI {

    public GUI() {
        // Create main frame
        JFrame frame = new JFrame("Rental Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create panels and components here
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Welcome to the Rental Management System");

        // Add more shit here
        JButton button = new JButton("Click Me");
        panel.add(label);
        panel.add(button);
        frame.add(panel);
        button.addActionListener(e -> System.out.println("Button clicked!"));
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            DBConnection.getInstance().connect();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        new GUI();
    }

}
