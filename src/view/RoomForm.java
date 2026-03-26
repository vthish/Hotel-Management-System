package view;

import controller.RoomController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoomForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Room Form");

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Labels
        JLabel roomLabel = new JLabel("Room Number");
        JLabel typeLabel = new JLabel("Type");
        JLabel priceLabel = new JLabel("Price");
        JLabel statusLabel = new JLabel("Status");

        // Fields with placeholders
        JTextField roomNumber = new JTextField("Ex: 101");
        JTextField type = new JTextField("Ex: Deluxe");
        JTextField price = new JTextField("Ex: 5000");

        // Dropdown
        String[] statuses = {"Available", "Booked", "Maintenance"};
        JComboBox<String> status = new JComboBox<>(statuses);

        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        // Positions
        roomLabel.setBounds(50, 10, 200, 20);
        roomNumber.setBounds(50, 30, 200, 30);

        typeLabel.setBounds(50, 70, 200, 20);
        type.setBounds(50, 90, 200, 30);

        priceLabel.setBounds(50, 130, 200, 20);
        price.setBounds(50, 150, 200, 30);

        statusLabel.setBounds(50, 190, 200, 20);
        status.setBounds(50, 210, 200, 30);

        saveBtn.setBounds(50, 260, 100, 30);
        backBtn.setBounds(160, 260, 90, 30); // FIXED POSITION

        // Placeholder setup
        addPlaceholder(roomNumber, "Ex: 101");
        addPlaceholder(type, "Ex: Deluxe");
        addPlaceholder(price, "Ex: 5000");

        RoomController controller = new RoomController();

        // Enter navigation
        roomNumber.addActionListener(e -> type.requestFocus());
        type.addActionListener(e -> price.requestFocus());
        price.addActionListener(e -> status.requestFocus());

        // Save
        saveBtn.addActionListener(e -> {

            String roomNo = roomNumber.getText();
            String typeText = type.getText();
            String priceText = price.getText();

            if (roomNo.equals("Ex: 101") || typeText.equals("Ex: Deluxe") || priceText.equals("Ex: 5000")) {
                JOptionPane.showMessageDialog(frame, "Fill all fields correctly");
                return;
            }

            controller.saveRoom(
                    roomNo,
                    typeText,
                    priceText,
                    status.getSelectedItem().toString()
            );

            JOptionPane.showMessageDialog(frame, "Saved Successfully");
            frame.dispose();
        });

        // BACK BUTTON
        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        // Add components
        frame.add(roomLabel);
        frame.add(roomNumber);
        frame.add(typeLabel);
        frame.add(type);
        frame.add(priceLabel);
        frame.add(price);
        frame.add(statusLabel);
        frame.add(status);
        frame.add(saveBtn);
        frame.add(backBtn);

        frame.setSize(300, 350); // little height increase
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