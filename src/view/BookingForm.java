package view;

import controller.BookingController;
import controller.CustomerController;
import controller.RoomController;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Add Booking");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel customerLabel = new JLabel("Select Customer");
        JLabel roomLabel = new JLabel("Select Available Room");
        JLabel checkInLabel = new JLabel("Check-In Date");
        JLabel checkOutLabel = new JLabel("Check-Out Date");

        JComboBox<ComboItem> customerDropdown = new JComboBox<>();
        JComboBox<ComboItem> roomDropdown = new JComboBox<>();

        Date today = new Date();

        SpinnerDateModel checkInModel = new SpinnerDateModel(today, today, null, Calendar.DAY_OF_MONTH);
        JSpinner checkInSpinner = new JSpinner(checkInModel);
        JSpinner.DateEditor inEditor = new JSpinner.DateEditor(checkInSpinner, "yyyy-MM-dd");
        checkInSpinner.setEditor(inEditor);

        SpinnerDateModel checkOutModel = new SpinnerDateModel(today, today, null, Calendar.DAY_OF_MONTH);
        JSpinner checkOutSpinner = new JSpinner(checkOutModel);
        JSpinner.DateEditor outEditor = new JSpinner.DateEditor(checkOutSpinner, "yyyy-MM-dd");
        checkOutSpinner.setEditor(outEditor);

        JButton saveButton = new JButton("Save Booking");
        JButton backBtn = new JButton("Back");

        customerLabel.setBounds(50, 10, 250, 20);
        customerDropdown.setBounds(50, 30, 250, 30);

        roomLabel.setBounds(50, 70, 250, 20);
        roomDropdown.setBounds(50, 90, 250, 30);

        checkInLabel.setBounds(50, 130, 250, 20);
        checkInSpinner.setBounds(50, 150, 250, 30);

        checkOutLabel.setBounds(50, 190, 250, 20);
        checkOutSpinner.setBounds(50, 210, 250, 30);

        saveButton.setBounds(50, 260, 130, 30);
        backBtn.setBounds(190, 260, 110, 30);

        CustomerController customerController = new CustomerController();
        RoomController roomController = new RoomController();
        BookingController bookingController = new BookingController();

        List<String[]> customers = customerController.getCustomers();
        for (String[] customer : customers) {
            customerDropdown.addItem(new ComboItem(customer[0], customer[1] + " - " + customer[2]));
        }

        List<String[]> rooms = roomController.getAvailableRooms();
        for (String[] room : rooms) {
            roomDropdown.addItem(new ComboItem(room[0], room[1] + " (Rs." + room[2] + ")"));
        }

        saveButton.addActionListener(e -> {
            ComboItem selectedCustomer = (ComboItem) customerDropdown.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) roomDropdown.getSelectedItem();

            if (selectedCustomer == null || selectedRoom == null) {
                JOptionPane.showMessageDialog(frame, "Please ensure Customers and Rooms are available!");
                return;
            }

            String customerId = selectedCustomer.getId();
            String roomId = selectedRoom.getId();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String checkIn = sdf.format(checkInSpinner.getValue());
            String checkOut = sdf.format(checkOutSpinner.getValue());

            String result = bookingController.saveBooking(customerId, roomId, checkIn, checkOut);

            if (result.equals("Booking successful!")) {
                JOptionPane.showMessageDialog(frame, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                Dashboard.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.add(customerLabel);
        frame.add(customerDropdown);
        frame.add(roomLabel);
        frame.add(roomDropdown);
        frame.add(checkInLabel);
        frame.add(checkInSpinner);
        frame.add(checkOutLabel);
        frame.add(checkOutSpinner);
        frame.add(saveButton);
        frame.add(backBtn);

        frame.setSize(370, 360);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ComboItem {
    private String id;
    private String displayValue;

    public ComboItem(String id, String displayValue) {
        this.id = id;
        this.displayValue = displayValue;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return displayValue;
    }
}