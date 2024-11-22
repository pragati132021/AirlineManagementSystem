package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        setLayout(null);

        // Background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/front.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1600, 800);
        add(image);

        // Main heading
        JLabel heading = new JLabel("AIR INDIA WELCOMES YOU");
        heading.setBounds(445, 40, 1000, 40);
        heading.setForeground(Color.BLUE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        image.add(heading);

        // Subheading
        JLabel newHeading = new JLabel("EXPLORE AMAZING DESTINATIONS.");
        newHeading.setBounds(310, 100, 1000, 40);
        newHeading.setForeground(Color.WHITE);
        newHeading.setFont(new Font("Black Jack", Font.BOLD, 50));
        image.add(newHeading);

        // Menu Bar
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(30, 144, 255)); // Custom background color
        menubar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Add details menu
        JMenu details = createMenu("Details", new Color(255, 255, 255), new Font("Arial", Font.BOLD, 16));
        menubar.add(details);

        details.add(createMenuItem("Flight Details", this));
        details.add(createMenuItem("Add Customer Details", this));
        details.add(createMenuItem("Book Flight", this));
        details.add(createMenuItem("Journey Details", this));
        details.add(createMenuItem("Cancel Ticket", this));

        // Add ticket menu
        JMenu ticket = createMenu("Ticket", new Color(255, 255, 255), new Font("Arial", Font.BOLD, 16));
        menubar.add(ticket);
        ticket.add(createMenuItem("Boarding Pass", this));

        // Add help menu
        JMenu helpMenu = createMenu("Help", new Color(255, 255, 255), new Font("Arial", Font.BOLD, 16));
        menubar.add(helpMenu);

        helpMenu.add(createMenuItem("About Us", e -> {
            JOptionPane.showMessageDialog(this, "Airline Management System \nDeveloped by Pragati Mishra and Shruti\nVersion 1.0");
        }));
        helpMenu.add(createMenuItem("Help Contents", e -> {
            JOptionPane.showMessageDialog(this, """
                    Help Contents:
                    1. Add Customer Details: Enter customer information.
                    2. Flight Details: View available flights.
                    3. Book Flight: Reserve a flight.
                    4. Journey Details: View your booked journeys.
                    5. Cancel Ticket: Cancel your reservations.
                    6. Boarding Pass: View your boarding pass.
                    """);
        }));

        setJMenuBar(menubar);

        // Frame settings
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close
    }

    private JMenu createMenu(String name, Color foreground, Font font) {
        JMenu menu = new JMenu(name);
        menu.setForeground(foreground);
        menu.setFont(font);
        return menu;
    }

    private JMenuItem createMenuItem(String name, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(name);
        menuItem.setFont(new Font("Arial", Font.PLAIN, 14));
        menuItem.setBackground(new Color(135, 206, 250)); // Custom background for menu items
        menuItem.setForeground(Color.BLACK); // Text color
        menuItem.addActionListener(listener);
        return menuItem;
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if (text.equals("Add Customer Details")) {
            new AddCustomer(); // Assuming you have an AddCustomer class
        } else if (text.equals("Flight Details")) {
            new FlightInfo(); // Assuming you have a FlightInfo class
        } else if (text.equals("Book Flight")) {
            new BookFlight(); // Assuming you have a BookFlight class
        } else if (text.equals("Journey Details")) {
            new JourneyDetails(); // Assuming you have a JourneyDetails class
        } else if (text.equals("Cancel Ticket")) {
            new Cancel(); // Assuming you have a Cancel class
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass(); // Assuming you have a BoardingPass class
        }
    }

    public static void main(String[] args) {
        new Home(); // Create an instance of Home to launch the application
    }
}
