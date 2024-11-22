package airlinemanagementsystem;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JTextField pnr;
    JButton show, clear;

    public JourneyDetails() {
        setTitle("Journey Details");
        setLayout(null);

        // Gradient background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(135, 206, 250); // Light blue
                Color color2 = new Color(0, 102, 204); // Dark blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        backgroundPanel.setBounds(0, 0, 1000, 650); // Adjusted window size
        add(backgroundPanel);

        // Header Label
        JLabel header = new JLabel("Journey Details");
        header.setFont(new Font("Tahoma", Font.BOLD, 32));
        header.setForeground(Color.WHITE);
        header.setBounds(380, 20, 300, 40);
        backgroundPanel.add(header);

        // PNR Label
        JLabel lblpnr = new JLabel("Enter PNR:");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblpnr.setForeground(Color.WHITE);
        lblpnr.setBounds(50, 80, 120, 30);
        backgroundPanel.add(lblpnr);

        // PNR Input Field
        pnr = new JTextField();
        pnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        pnr.setBounds(150, 80, 300, 30);
        pnr.setToolTipText("Enter your PNR to search");
        backgroundPanel.add(pnr);

        // Show Button
        show = new JButton("Show Details");
        show.setFont(new Font("Tahoma", Font.BOLD, 14));
        show.setBounds(480, 80, 150, 30);
        show.setForeground(Color.WHITE);
        show.setBackground(new Color(0, 153, 76));
        show.setCursor(new Cursor(Cursor.HAND_CURSOR));
        show.setFocusPainted(false);
        backgroundPanel.add(show);

        // Clear Button
        clear = new JButton("Clear");
        clear.setFont(new Font("Tahoma", Font.BOLD, 14));
        clear.setBounds(650, 80, 100, 30);
        clear.setForeground(Color.WHITE);
        clear.setBackground(new Color(204, 0, 0));
        clear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clear.setFocusPainted(false);
        backgroundPanel.add(clear);

        // Table Panel
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setGridColor(Color.LIGHT_GRAY);
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 16));
        table.getTableHeader().setBackground(new Color(0, 102, 204));
        table.getTableHeader().setForeground(Color.WHITE);

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(50, 150, 900, 400); // Adjusted table area
        jsp.setBackground(Color.WHITE);
        jsp.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));
        backgroundPanel.add(jsp);

        // Add ActionListeners
        show.addActionListener(this);
        clear.addActionListener(ae -> {
            pnr.setText(""); // Clear PNR input
            table.setModel(new DefaultTableModel()); // Clear table
        });

        // Frame Settings
        setSize(1000, 650); // Smaller window size
        setLocationRelativeTo(null); // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM reservation WHERE PNR = '" + pnr.getText() + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (!rs.isBeforeFirst()) { // Check if ResultSet is empty
                JOptionPane.showMessageDialog(this, "No Information Found for PNR: " + pnr.getText(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Use DbUtils to populate the table
            table.setModel(DbUtils.resultSetToTableModel(rs));

            // Dynamically adjust column widths
            autoResizeColumnWidths(table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Dynamically adjust column widths based on content
    private void autoResizeColumnWidths(JTable table) {
        TableColumnModel columnModel = table.getColumnModel();
        for (int col = 0; col < table.getColumnCount(); col++) {
            int maxWidth = 150; // Minimum width for each column
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, col);
                Component comp = table.prepareRenderer(renderer, row, col);
                maxWidth = Math.max(comp.getPreferredSize().width + 10, maxWidth);
            }
            columnModel.getColumn(col).setPreferredWidth(maxWidth);
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}
