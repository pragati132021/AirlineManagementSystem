package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class FlightInfo extends JFrame {
    
    public FlightInfo() {
        // Set the title of the window
        setTitle("Flight Information");
        
        // Set the background color and layout
        getContentPane().setBackground(new Color(173, 216, 230)); // Light blue background
        setLayout(new BorderLayout());
        
        // Create a title label
        JLabel titleLabel = new JLabel("Flight Information", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204)); // Custom color for the title
        add(titleLabel, BorderLayout.NORTH);
        
        // Create a JTable and populate it with data
        JTable table = new JTable();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        
        // Set table header properties
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);
        
        // Fetch data from the database
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("SELECT * FROM flight");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Add the table to a JScrollPane
        JScrollPane jsp = new JScrollPane(table);
        add(jsp, BorderLayout.CENTER);
        
        // Set frame properties
        setSize(800, 500);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit on close
        setVisible(true);
    }

    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety
        SwingUtilities.invokeLater(() -> new FlightInfo());
    }
}