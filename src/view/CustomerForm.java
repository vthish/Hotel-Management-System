package view;

import controller.CustomerController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class CustomerForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Customer Form");

        JTextField nameField = new JTextField("Enter Name");
        JTextField phoneField = new JTextField("Enter Phone");
        JTextField emailField = new JTextField("Enter Email");

        JButton saveButton = new JButton("Save");

        nameField.setBounds(50, 50, 200, 30);
        phoneField.setBounds(50, 90, 200, 30);
        emailField.setBounds(50, 130, 200, 30);
        saveButton.setBounds(50, 180, 100, 30);

        setPlaceholder(nameField, "Enter Name");
        setPlaceholder(phoneField, "Enter Phone");
        setPlaceholder(emailField, "Enter Email");

        CustomerController controller = new CustomerController();

        nameField.addActionListener(e -> phoneField.requestFocus());
        phoneField.addActionListener(e -> emailField.requestFocus());
        emailField.addActionListener(e -> saveButton.doClick());

        saveButton.addActionListener(e -> {
            controller.saveCustomer(
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText()
            );

            JOptionPane.showMessageDialog(frame, "Saved Successfully");
            frame.dispose();
        });

        frame.add(nameField);
        frame.add(phoneField);
        frame.add(emailField);
        frame.add(saveButton);

        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void setPlaceholder(JTextField field, String text) {
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