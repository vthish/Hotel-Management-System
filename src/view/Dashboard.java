package view;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Management Dashboard");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        JButton customerBtn = new JButton("Add Customer");
        JButton viewCustomerBtn = new JButton("View Customers");
        JButton roomBtn = new JButton("Manage Rooms");
        JButton bookingBtn = new JButton("Add Booking");
        JButton exitBtn = new JButton("Exit");

        Dimension btnSize = new Dimension(200, 40);
        JButton[] buttons = {customerBtn, viewCustomerBtn, roomBtn, bookingBtn, exitBtn};

        for(JButton btn : buttons) {
            btn.setMaximumSize(btnSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        customerBtn.addActionListener(e -> {
            frame.dispose();
            CustomerForm.main(null);
        });

        viewCustomerBtn.addActionListener(e -> {
            frame.dispose();
            CustomerTable.main(null);
        });

        roomBtn.addActionListener(e -> {
            frame.dispose();
            RoomForm.main(null);
        });

        bookingBtn.addActionListener(e -> {
            frame.dispose();
            BookingForm.main(null);
        });

        exitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        panel.add(customerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(viewCustomerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(roomBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(bookingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(exitBtn);

        frame.add(panel);
        frame.setSize(350, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}