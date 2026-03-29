package view;

import controller.RoomController;
import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hotel Management Dashboard");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        RoomController roomController = new RoomController();
        int availableCount = roomController.getAvailableRoomCount();

        JLabel statusLabel = new JLabel("Available Rooms: " + availableCount);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(new Color(0, 128, 0));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton customerBtn = new JButton("Add Customer");
        JButton viewCustomerBtn = new JButton("View Customers");
        JButton roomBtn = new JButton("Manage Rooms");
        JButton bookingBtn = new JButton("Add Booking");
        JButton reportBtn = new JButton("View Reports");
        JButton exitBtn = new JButton("Exit");

        Dimension btnSize = new Dimension(220, 40);
        JButton[] buttons = {customerBtn, viewCustomerBtn, roomBtn, bookingBtn, reportBtn, exitBtn};
        for(JButton btn : buttons) {
            btn.setMaximumSize(btnSize);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        }

        customerBtn.addActionListener(e -> { frame.dispose(); CustomerForm.main(null); });
        viewCustomerBtn.addActionListener(e -> { frame.dispose(); CustomerTable.main(null); });
        roomBtn.addActionListener(e -> { frame.dispose(); RoomForm.main(null); });
        bookingBtn.addActionListener(e -> { frame.dispose(); BookingForm.main(null); });
        reportBtn.addActionListener(e -> { frame.dispose(); ReportView.main(null); });
        exitBtn.addActionListener(e -> System.exit(0));

        panel.add(statusLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(customerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(viewCustomerBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(roomBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(bookingBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(reportBtn);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(exitBtn);

        frame.add(panel);
        frame.setSize(400, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}