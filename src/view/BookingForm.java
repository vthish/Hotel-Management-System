package view;

import controller.BookingController;
import controller.CustomerController;
import controller.RoomController;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Date;
import java.util.List;

public class BookingForm {

    private JFrame frame;
    private JComboBox<String> customerCombo;
    private JComboBox<String> roomCombo;
    private JDateChooser checkIn;
    private JDateChooser checkOut;

    private CustomerController customerController;
    private RoomController roomController;
    private BookingController bookingController;

    public BookingForm() {
        customerController = new CustomerController();
        roomController = new RoomController();
        bookingController = new BookingController();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Booking Form");
        frame.setLayout(null);
        frame.setSize(350, 400);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel customerLabel = new JLabel("Customer:");
        JLabel roomLabel = new JLabel("Room:");
        JLabel checkInLabel = new JLabel("Check-In:");
        JLabel checkOutLabel = new JLabel("Check-Out:");

        customerCombo = new JComboBox<>();
        roomCombo = new JComboBox<>();

        checkIn = new JDateChooser();
        checkOut = new JDateChooser();

        JButton saveBtn = new JButton("Save");
        JButton backBtn = new JButton("Back");

        customerLabel.setBounds(30, 20, 100, 20);
        customerCombo.setBounds(130, 20, 180, 25);

        roomLabel.setBounds(30, 60, 100, 20);
        roomCombo.setBounds(130, 60, 180, 25);

        checkInLabel.setBounds(30, 100, 100, 20);
        checkIn.setBounds(130, 100, 180, 25);

        checkOutLabel.setBounds(30, 140, 100, 20);
        checkOut.setBounds(130, 140, 180, 25);

        saveBtn.setBounds(50, 200, 100, 30);
        backBtn.setBounds(180, 200, 100, 30);

        frame.add(customerLabel);
        frame.add(customerCombo);
        frame.add(roomLabel);
        frame.add(roomCombo);
        frame.add(checkInLabel);
        frame.add(checkIn);
        frame.add(checkOutLabel);
        frame.add(checkOut);
        frame.add(saveBtn);
        frame.add(backBtn);

        populateCustomers();
        populateAvailableRooms();

        // Set check-in min date as today
        checkIn.setMinSelectableDate(new Date());

        // Save button action
        saveBtn.addActionListener(e -> saveBooking());

        // Back button
        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void populateCustomers() {
        customerCombo.removeAllItems();
        List<String[]> customers = customerController.getCustomers();
        for (String[] c : customers) {
            // Format: ID - Name
            customerCombo.addItem(c[0] + " - " + c[1]);
        }
    }

    private void populateAvailableRooms() {
        roomCombo.removeAllItems();
        List<String[]> rooms = roomController.getRooms();
        for (String[] r : rooms) {
            if (r[3].equalsIgnoreCase("Available")) {
                // Format: RoomNo - Type
                roomCombo.addItem(r[0] + " - " + r[1]);
            }
        }
    }

    private void saveBooking() {
        if (customerCombo.getItemCount() == 0 || roomCombo.getItemCount() == 0) {
            JOptionPane.showMessageDialog(frame, "No customer or room available!");
            return;
        }

        String custItem = (String) customerCombo.getSelectedItem();
        String customerId = custItem.split(" - ")[0];

        String roomItem = (String) roomCombo.getSelectedItem();
        String roomId = roomItem.split(" - ")[0];

        Date inDate = checkIn.getDate();
        Date outDate = checkOut.getDate();

        if (inDate == null || outDate == null || inDate.after(outDate)) {
            JOptionPane.showMessageDialog(frame, "Select valid check-in and check-out dates!");
            return;
        }

        // Save booking
        bookingController.saveBooking(customerId, roomId,
                new java.sql.Date(inDate.getTime()).toString(),
                new java.sql.Date(outDate.getTime()).toString());

        // Update room status
        roomController.updateRoomStatus(roomId, "Occupied");

        JOptionPane.showMessageDialog(frame, "Booking saved successfully!");

        // Refresh room combo to remove occupied room
        populateAvailableRooms();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BookingForm::new);
    }
}