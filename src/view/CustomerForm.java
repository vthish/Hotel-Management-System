package view;

import controller.CustomerController;

import javax.swing.*;

public class CustomerForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Customer Form");

        JTextField nameField = new JTextField();
        nameField.setBounds(50, 50, 200, 30);

        JTextField phoneField = new JTextField();
        phoneField.setBounds(50, 90, 200, 30);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 130, 200, 30);

        JButton saveButton = new JButton("Save");
        saveButton.setBounds(50, 180, 100, 30);

        CustomerController controller = new CustomerController();

        saveButton.addActionListener(e -> {
            controller.saveCustomer(
                    nameField.getText(),
                    phoneField.getText(),
                    emailField.getText()
            );
        });

        frame.add(nameField);
        frame.add(phoneField);
        frame.add(emailField);
        frame.add(saveButton);

        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}