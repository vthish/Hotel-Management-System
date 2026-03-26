package view;

import controller.BookingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class BookingForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Booking Form");

        JTextField customerId = new JTextField("Enter Customer ID");
        JTextField roomId = new JTextField("Enter Room ID");
        JTextField checkIn = new JTextField("YYYY-MM-DD");
        JTextField checkOut = new JTextField("YYYY-MM-DD");

        JButton saveBtn = new JButton("Save");

        customerId.setBounds(50, 30, 200, 30);
        roomId.setBounds(50, 70, 200, 30);
        checkIn.setBounds(50, 110, 200, 30);
        checkOut.setBounds(50, 150, 200, 30);
        saveBtn.setBounds(50, 200, 100, 30);

        setPlaceholder(customerId, "Enter Customer ID");
        setPlaceholder(roomId, "Enter Room ID");
        setPlaceholder(checkIn, "YYYY-MM-DD");
        setPlaceholder(checkOut, "YYYY-MM-DD");

        BookingController controller = new BookingController();

        customerId.addActionListener(e -> roomId.requestFocus());
        roomId.addActionListener(e -> checkIn.requestFocus());
        checkIn.addActionListener(e -> checkOut.requestFocus());
        checkOut.addActionListener(e -> saveBtn.doClick());

        saveBtn.addActionListener(e -> {
            controller.saveBooking(
                    customerId.getText(),
                    roomId.getText(),
                    checkIn.getText(),
                    checkOut.getText()
            );

            JOptionPane.showMessageDialog(frame, "Booking Saved");
            frame.dispose();
        });

        frame.add(customerId);
        frame.add(roomId);
        frame.add(checkIn);
        frame.add(checkOut);
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