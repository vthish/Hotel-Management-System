package view;

import controller.CustomerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomerForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Customer Form");

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Labels
        JLabel nameLabel = new JLabel("Name");
        JLabel phoneLabel = new JLabel("Phone");
        JLabel emailLabel = new JLabel("Email");

        // Fields with placeholder
        JTextField nameField = new JTextField("Ex: John");
        JTextField phoneField = new JTextField("Ex: 0771234567");
        JTextField emailField = new JTextField("Ex: test@gmail.com");

        JButton saveButton = new JButton("Save");

        // Positions
        nameLabel.setBounds(50, 10, 200, 20);
        nameField.setBounds(50, 30, 200, 30);

        phoneLabel.setBounds(50, 70, 200, 20);
        phoneField.setBounds(50, 90, 200, 30);

        emailLabel.setBounds(50, 130, 200, 20);
        emailField.setBounds(50, 150, 200, 30);

        saveButton.setBounds(50, 200, 100, 30);

        // Placeholder setup
        addPlaceholder(nameField, "Ex: John");
        addPlaceholder(phoneField, "Ex: 0771234567");
        addPlaceholder(emailField, "Ex: test@gmail.com");

        CustomerController controller = new CustomerController();

        // Enter navigation
        nameField.addActionListener(e -> phoneField.requestFocus());
        phoneField.addActionListener(e -> emailField.requestFocus());
        emailField.addActionListener(e -> saveButton.doClick());

        // Save
        saveButton.addActionListener(e -> {

            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();

            if (name.equals("Ex: John") || phone.equals("Ex: 0771234567") || email.equals("Ex: test@gmail.com")) {
                JOptionPane.showMessageDialog(frame, "Fill all fields correctly");
                return;
            }

            controller.saveCustomer(name, phone, email);

            JOptionPane.showMessageDialog(frame, "Saved Successfully");
            frame.dispose();
        });

        // Add components
        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(saveButton);

        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void addPlaceholder(JTextField field, String text) {
        field.setForeground(Color.GRAY);

        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(text)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(text);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }
}