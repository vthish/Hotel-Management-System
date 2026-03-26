package view;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hotel Management Dashboard");

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton customerBtn = new JButton("Customer");
        JButton roomBtn = new JButton("Room");
        JButton bookingBtn = new JButton("Booking");
        JButton viewCustomerBtn = new JButton("View Customers");
        JButton exitBtn = new JButton("Exit"); // NEW

        Dimension btnSize = new Dimension(200, 40);

        customerBtn.setMaximumSize(btnSize);
        roomBtn.setMaximumSize(btnSize);
        bookingBtn.setMaximumSize(btnSize);
        viewCustomerBtn.setMaximumSize(btnSize);
        exitBtn.setMaximumSize(btnSize);

        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCustomerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        customerBtn.addActionListener(e -> CustomerForm.main(null));
        roomBtn.addActionListener(e -> RoomForm.main(null));
        bookingBtn.addActionListener(e -> BookingForm.main(null));
        viewCustomerBtn.addActionListener(e -> CustomerTable.main(null));

        // EXIT ACTION
        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit?",
                    "Exit Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(customerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(roomBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(bookingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(viewCustomerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        panel.add(exitBtn); // ADD HERE

        frame.add(panel);

        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}