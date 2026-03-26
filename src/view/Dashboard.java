package view;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Hotel Management Dashboard");

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50)); // padding

        // Buttons
        JButton customerBtn = new JButton("Customer");
        JButton roomBtn = new JButton("Room");
        JButton bookingBtn = new JButton("Booking");
        JButton viewCustomerBtn = new JButton("View Customers");

        // Button size fix
        Dimension btnSize = new Dimension(200, 40);

        customerBtn.setMaximumSize(btnSize);
        roomBtn.setMaximumSize(btnSize);
        bookingBtn.setMaximumSize(btnSize);
        viewCustomerBtn.setMaximumSize(btnSize);

        // Center align
        customerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        roomBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        bookingBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewCustomerBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Actions
        customerBtn.addActionListener(e -> CustomerForm.main(null));
        roomBtn.addActionListener(e -> RoomForm.main(null));
        bookingBtn.addActionListener(e -> BookingForm.main(null));
        viewCustomerBtn.addActionListener(e -> CustomerTable.main(null));

        // Add with spacing
        panel.add(customerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(roomBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(bookingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));

        panel.add(viewCustomerBtn);

        // Add panel to frame
        frame.add(panel);

        frame.setSize(350, 350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}