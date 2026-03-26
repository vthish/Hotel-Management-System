package view;

import controller.BookingController;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;

public class BookingForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Booking Form");

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel customerLabel = new JLabel("Customer ID");
        JLabel roomLabel = new JLabel("Room ID");
        JLabel checkInLabel = new JLabel("Check In");
        JLabel checkOutLabel = new JLabel("Check Out");

        JTextField customerId = new JTextField("Ex: 1");
        JTextField roomId = new JTextField("Ex: 101");

        JDateChooser checkIn = new JDateChooser();
        JDateChooser checkOut = new JDateChooser();

        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        customerLabel.setBounds(50, 10, 200, 20);
        customerId.setBounds(50, 30, 200, 30);

        roomLabel.setBounds(50, 70, 200, 20);
        roomId.setBounds(50, 90, 200, 30);

        checkInLabel.setBounds(50, 130, 200, 20);
        checkIn.setBounds(50, 150, 200, 30);

        checkOutLabel.setBounds(50, 190, 200, 20);
        checkOut.setBounds(50, 210, 200, 30);

        saveBtn.setBounds(50, 260, 100, 30);
        backBtn.setBounds(160, 260, 90, 30); // FIXED POSITION

        addPlaceholder(customerId, "Ex: 1");
        addPlaceholder(roomId, "Ex: 101");

        BookingController controller = new BookingController();

        customerId.addActionListener(e -> roomId.requestFocus());
        roomId.addActionListener(e -> checkIn.requestFocus());

        saveBtn.addActionListener(e -> {

            Date inDate = checkIn.getDate();
            Date outDate = checkOut.getDate();

            if (customerId.getText().equals("Ex: 1") ||
                    roomId.getText().equals("Ex: 101") ||
                    inDate == null || outDate == null) {

                JOptionPane.showMessageDialog(frame, "Fill all fields correctly");
                return;
            }

            controller.saveBooking(
                    customerId.getText(),
                    roomId.getText(),
                    new java.sql.Date(inDate.getTime()).toString(),
                    new java.sql.Date(outDate.getTime()).toString()
            );

            JOptionPane.showMessageDialog(frame, "Booking Saved");
            frame.dispose();
        });

        // BACK BUTTON FIX
        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.add(customerLabel);
        frame.add(customerId);
        frame.add(roomLabel);
        frame.add(roomId);
        frame.add(checkInLabel);
        frame.add(checkIn);
        frame.add(checkOutLabel);
        frame.add(checkOut);
        frame.add(saveBtn);
        frame.add(backBtn);

        frame.setSize(300, 350);
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