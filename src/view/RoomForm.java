package view;

import controller.RoomController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RoomForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Room Form");

        JTextField roomNumber = new JTextField("Enter Room Number");
        JTextField type = new JTextField("Enter Room Type");
        JTextField price = new JTextField("Enter Price");
        JTextField status = new JTextField("Enter Status");

        JButton saveBtn = new JButton("Save");

        roomNumber.setBounds(50, 30, 200, 30);
        type.setBounds(50, 70, 200, 30);
        price.setBounds(50, 110, 200, 30);
        status.setBounds(50, 150, 200, 30);
        saveBtn.setBounds(50, 200, 100, 30);

        setPlaceholder(roomNumber, "Enter Room Number");
        setPlaceholder(type, "Enter Room Type");
        setPlaceholder(price, "Enter Price");
        setPlaceholder(status, "Enter Status");

        RoomController controller = new RoomController();

        roomNumber.addActionListener(e -> type.requestFocus());
        type.addActionListener(e -> price.requestFocus());
        price.addActionListener(e -> status.requestFocus());
        status.addActionListener(e -> saveBtn.doClick());

        saveBtn.addActionListener(e -> {
            controller.saveRoom(
                    roomNumber.getText(),
                    type.getText(),
                    price.getText(),
                    status.getText()
            );

            JOptionPane.showMessageDialog(frame, "Saved Successfully");
            frame.dispose();
        });

        frame.add(roomNumber);
        frame.add(type);
        frame.add(price);
        frame.add(status);
        frame.add(saveBtn);

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