package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {
    
    JTextField tfpnr;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;
    
    public Cancel() {
        getContentPane().setBackground(new Color(245, 245, 245));  // Light gray background
        setLayout(null);
        
        Random random = new Random();
        
        // Heading
        JLabel heading = new JLabel("CANCELLATION");
        heading.setBounds(180, 20, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(new Color(0, 102, 204));  // Blue color
        add(heading);
        
        // Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(470, 120, 250, 250);
        add(image);
        
        // PNR label and text field
        JLabel lblaadhar = new JLabel("PNR Number");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblaadhar.setForeground(new Color(0, 102, 204)); // Blue color
        add(lblaadhar);
        
        tfpnr = new JTextField();
        tfpnr.setBounds(220, 80, 150, 25);
        tfpnr.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(tfpnr);
        
        // Fetch button
        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(new Color(0, 102, 204));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setBounds(380, 80, 120, 25);
        fetchButton.addActionListener(this);
        add(fetchButton);
        
        // Name label
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblname.setForeground(new Color(0, 102, 204)); // Blue color
        add(lblname);
        
        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        tfname.setFont(new Font("Tahoma", Font.PLAIN, 14));
        tfname.setForeground(new Color(0, 102, 204));
        add(tfname);
        
        // Cancellation No label
        JLabel lblnationality = new JLabel("Cancellation No");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblnationality.setForeground(new Color(0, 102, 204)); // Blue color
        add(lblnationality);
        
        cancellationno = new JLabel("" + random.nextInt(1000000));
        cancellationno.setBounds(220, 180, 150, 25);
        cancellationno.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cancellationno.setForeground(new Color(0, 102, 204));
        add(cancellationno);
        
        // Flight Code label
        JLabel lbladdress = new JLabel("Flight Code");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lbladdress.setForeground(new Color(0, 102, 204)); // Blue color
        add(lbladdress);
        
        lblfcode = new JLabel();
        lblfcode.setBounds(220, 230, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblfcode.setForeground(new Color(0, 102, 204));
        add(lblfcode);
        
        // Date of Travel label
        JLabel lblgender = new JLabel("Date");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblgender.setForeground(new Color(0, 102, 204)); // Blue color
        add(lblgender);
        
        lbldateoftravel = new JLabel();
        lbldateoftravel.setBounds(220, 280, 150, 25);
        lbldateoftravel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lbldateoftravel.setForeground(new Color(0, 102, 204));
        add(lbldateoftravel);
        
       
        
        // Cancel button
        flight = new JButton("Cancel");
        flight.setBackground(new Color(255, 0, 0)); // Red color for cancellation
        flight.setForeground(Color.WHITE);
        flight.setBounds(220, 330, 120, 25);
        flight.addActionListener(this);
        add(flight);
        
        // Frame settings
        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = tfpnr.getText();
            
            try {
            Conn conn = new Conn();

            String query = "select * from reservation where PNR = '"+pnr+"'";

            ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name")); 
                    lblfcode.setText(rs.getString("flightcode")); 
                    lbldateoftravel.setText(rs.getString("ddate"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");                
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String name = tfname.getText();
            String pnr = tfpnr.getText();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();
            
            // To make ensure PNR Field is not empty
            if (pnr.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Enter PNR!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further execution
        }
            
            
            try {
                Conn conn = new Conn();

                String query = "insert into cancellation values('"+pnr+"', '"+name+"', '"+cancelno+"', '"+fcode+"', '"+date+"' )";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR = '"+pnr+"'");
                
                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);
            
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }

    public static void main(String[] args) {
        new Cancel();
    }
}
