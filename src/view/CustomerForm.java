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

        JLabel nameLabel = new JLabel("Name");
        JLabel phoneLabel = new JLabel("Phone (10 digits, starts with 0)");
        JLabel emailLabel = new JLabel("Email");

        JTextField nameField = new JTextField("Ex: John");
        JTextField phoneField = new JTextField("Ex: 0771234567");
        JTextField emailField = new JTextField("Ex: test@gmail.com");

        JButton saveButton = new JButton("Save");
        JButton backBtn = new JButton("Back");

        nameLabel.setBounds(50, 10, 250, 20);
        nameField.setBounds(50, 30, 250, 30);

        phoneLabel.setBounds(50, 70, 250, 20);
        phoneField.setBounds(50, 90, 250, 30);

        emailLabel.setBounds(50, 130, 250, 20);
        emailField.setBounds(50, 150, 250, 30);

        saveButton.setBounds(50, 200, 120, 30);
        backBtn.setBounds(180, 200, 120, 30);

        addPlaceholder(nameField, "Ex: John");
        addPlaceholder(phoneField, "Ex: 0771234567");
        addPlaceholder(emailField, "Ex: test@gmail.com");

        CustomerController controller = new CustomerController();

        nameField.addActionListener(e -> phoneField.requestFocus());
        phoneField.addActionListener(e -> emailField.requestFocus());
        emailField.addActionListener(e -> saveButton.doClick());

        saveButton.addActionListener(e -> {
            String name = nameField.getText().equals("Ex: John") ? "" : nameField.getText();
            String phone = phoneField.getText().equals("Ex: 0771234567") ? "" : phoneField.getText();
            String email = emailField.getText().equals("Ex: test@gmail.com") ? "" : emailField.getText();

            String result = controller.saveCustomer(name, phone, email);

            if (result.equals("Customer added successfully!")) {
                JOptionPane.showMessageDialog(frame, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                CustomerTable.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, result, "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(phoneLabel);
        frame.add(phoneField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(saveButton);
        frame.add(backBtn);

        frame.setSize(360, 300);
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