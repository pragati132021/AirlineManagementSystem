

package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close;
    JTextField tfusername;
    JPasswordField tfpassword;
    JCheckBox showPasswordCheckBox;

    public Login() {
        setTitle("Airline Management System - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Background color
        getContentPane().setBackground(new Color(135, 206, 250)); // Light blue background

        // Title Label
        JLabel title = new JLabel("Welcome to Airline Management System");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // Username label
        JLabel lblusername = new JLabel("Username:");
        lblusername.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(lblusername, gbc);

        tfusername = new JTextField(20);
        gbc.gridx = 1;
        add(tfusername, gbc);

        // Password label
        JLabel lblpassword = new JLabel("Password:");
        lblpassword.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblpassword, gbc);

        tfpassword = new JPasswordField(20);
        gbc.gridx = 1;
        add(tfpassword, gbc);

        // Checkbox to show/hide password
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setFont(new Font("Arial", Font.BOLD, 12));
        showPasswordCheckBox.setBackground(new Color(135, 206, 250)); // Same background color
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    tfpassword.setEchoChar((char) 0);  // Show password
                } else {
                    tfpassword.setEchoChar('*');  // Hide password
                }
            }
        });
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(showPasswordCheckBox, gbc);

        // Panel for buttons with better alignment
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(135, 206, 250)); // Light blue
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Reset Button
        reset = new JButton("Reset");
        reset.setBackground(new Color(255, 165, 0)); // Orange
        reset.setForeground(Color.WHITE);
        reset.setFont(new Font("Arial", Font.BOLD, 12));
        reset.addActionListener(this);
        reset.setFocusPainted(false);
        buttonPanel.add(reset);

        // Submit Button
        submit = new JButton("Submit");
        submit.setBackground(new Color(0, 128, 0)); // Green
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Arial", Font.BOLD, 12));
        submit.addActionListener(this);
        submit.setFocusPainted(false);
        buttonPanel.add(submit);

        // Close Button
        close = new JButton("Close");
        close.setBackground(new Color(255, 0, 0)); // Red
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Arial", Font.BOLD, 12));
        close.addActionListener(this);
        close.setFocusPainted(false);
        buttonPanel.add(close);

        // Add the button panel to the frame
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        // Set up window properties
        setSize(400, 350);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword()); // Get password securely

            // Debugging: Print username and password to console (Remove in production)
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

            try {
                Conn c = new Conn();
                
                // Query to check the login credentials
                String query = "select * from login where username = '" + username + "' and password = '" + password + "'";
                
                ResultSet rs = c.s.executeQuery(query);
                
                if (rs.next()) {
                    new Home();  // Open Home screen on successful login
                    setVisible(false);  // Hide the login screen
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    tfusername.setText("");
                    tfpassword.setText("");  // Clear fields after failure
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            System.exit(0);  // Close the application
        } else if (ae.getSource() == reset) {
            tfusername.setText("");  // Reset username field
            tfpassword.setText("");  // Reset password field
        }
    }

    public static void main(String[] args) {
        new Login();  // Create and display the login screen
    }
}
