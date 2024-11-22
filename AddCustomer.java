package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;

    public AddCustomer() {
        // Set the frame properties
        setTitle("Add Customer");
        getContentPane().setBackground(new Color(245, 245, 245)); // Light gray background
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Arial", Font.BOLD, 32));
        heading.setForeground(new Color(0, 102, 204));
        add(heading);

        // Name Label and TextField
        createLabel("Name", 60, 80);
        tfname = createTextField(220, 80);

        // Nationality Label and TextField
        createLabel("Nationality", 60, 130);
        tfnationality = createTextField(220, 130);

        // Aadhar Label and TextField
        createLabel("Aadhar Number", 60, 180);
        tfaadhar = createTextField(220, 180);
        tfaadhar.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                validateNumericInput(e, tfaadhar, 12);
            }
        });

        // Address Label and TextField
        createLabel("Address", 60, 230);
        tfaddress = createTextField(220, 230);

        // Gender Label and RadioButtons
        createLabel("Gender", 60, 280);
        ButtonGroup gendergroup = new ButtonGroup();
        rbmale = createRadioButton("Male", 220, 280);
        rbfemale = createRadioButton("Female", 300, 280);
        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        // Phone Label and TextField
        createLabel("Phone", 60, 330);
        tfphone = createTextField(220, 330);
        tfphone.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                validateNumericInput(e, tfphone, 10);
            }
        });

        // Save Button
        JButton save = new JButton("SAVE");
        save.setBackground(new Color(0, 102, 204));
        save.setForeground(Color.WHITE);
        save.setBounds(220, 380, 150, 30);
        save.setFont(new Font("Arial", Font.BOLD, 16));
        save.addActionListener(this);
        add(save);

        // Image
        ImageIcon image = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/emp.png"));
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(450, 80, 280, 400);
        add(lblimage);

        // Frame properties
        setSize(900, 600);
        setLocation(300, 150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, 150, 25);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setForeground(new Color(0, 51, 102)); // Dark blue color for text
        add(label);
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, 150, 25);
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        add(textField);
        return textField;
    }

    private JRadioButton createRadioButton(String text, int x, int y) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setBounds(x, y, 70, 25);
        radioButton.setBackground(new Color(245, 245, 245)); // Match background color
        radioButton.setFont(new Font("Arial", Font.PLAIN, 14));
        add(radioButton);
        return radioButton;
    }

    private void validateNumericInput(KeyEvent e, JTextField textField, int maxLength) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c) || textField.getText().length() >= maxLength) {
            e.consume(); // Ignore non-numeric or excessive input
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String name = tfname.getText().trim();
        String nationality = tfnationality.getText().trim();
        String phone = tfphone.getText().trim();
        String address = tfaddress.getText().trim();
        String aadhar = tfaadhar.getText().trim();
        String gender = null;

        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        }

        // Validation: Check if any field is empty
        if (name.isEmpty() || nationality.isEmpty() || phone.isEmpty() || address.isEmpty() || aadhar.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return; // Stop further execution
        }

        try {
            Conn conn = new Conn();
            String query = "INSERT INTO passenger VALUES('" + name + "', '" + nationality + "', '" + phone + "', '" + address + "', '" + aadhar + "', '" + gender + "')";
            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}
