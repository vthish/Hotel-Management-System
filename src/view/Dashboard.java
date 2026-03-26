package view;

import javax.swing.*;

public class Dashboard {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hotel Management Dashboard");

        // Frame settings
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        // Buttons
        JButton customerBtn = new JButton("Customer");
        JButton roomBtn = new JButton("Room");
        JButton bookingBtn = new JButton("Booking");

        // Button positions
        customerBtn.setBounds(50, 50, 200, 40);
        roomBtn.setBounds(50, 110, 200, 40);
        bookingBtn.setBounds(50, 170, 200, 40);

        // Button actions
        customerBtn.addActionListener(e -> {
            CustomerForm.main(null);
        });

        roomBtn.addActionListener(e -> {
            RoomForm.main(null);
        });

        bookingBtn.addActionListener(e -> {
            BookingForm.main(null);
        });

        // Add components
        frame.add(customerBtn);
        frame.add(roomBtn);
        frame.add(bookingBtn);

        // Final frame settings
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}