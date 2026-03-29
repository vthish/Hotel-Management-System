package view;

import controller.BookingController;
import controller.CustomerController;
import controller.RoomController;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class BookingForm {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Booking");
        frame.setLayout(null);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        JLabel customerLabel = new JLabel("Select Customer (Name - Phone)");
        JLabel roomLabel = new JLabel("Select Available Room");
        JLabel checkInLabel = new JLabel("Check-In Date");
        JLabel checkOutLabel = new JLabel("Check-Out Date");

        JComboBox<ComboItem> customerDropdown = new JComboBox<>();
        JComboBox<ComboItem> roomDropdown = new JComboBox<>();

        JDateChooser checkInChooser = new JDateChooser();
        checkInChooser.setDateFormatString("yyyy-MM-dd");
        checkInChooser.setMinSelectableDate(new Date());

        JDateChooser checkOutChooser = new JDateChooser();
        checkOutChooser.setDateFormatString("yyyy-MM-dd");
        checkOutChooser.setMinSelectableDate(new Date());

        JButton saveButton = new JButton("Save Booking");
        JButton backBtn = new JButton("Back");

        customerLabel.setBounds(50, 10, 250, 20);
        customerDropdown.setBounds(50, 30, 250, 30);
        roomLabel.setBounds(50, 70, 250, 20);
        roomDropdown.setBounds(50, 90, 250, 30);
        checkInLabel.setBounds(50, 130, 250, 20);
        checkInChooser.setBounds(50, 150, 250, 30);
        checkOutLabel.setBounds(50, 190, 250, 20);
        checkOutChooser.setBounds(50, 210, 250, 30);
        saveButton.setBounds(50, 260, 130, 30);
        backBtn.setBounds(190, 260, 110, 30);

        CustomerController customerController = new CustomerController();
        RoomController roomController = new RoomController();
        BookingController bookingController = new BookingController();

        // Showing Phone Number in Dropdown
        List<String[]> customers = customerController.getCustomers();
        for (String[] customer : customers) {
            customerDropdown.addItem(new ComboItem(customer[0], customer[1] + " (" + customer[2] + ")", 0));
        }

        List<String[]> rooms = roomController.getAvailableRooms();
        for (String[] room : rooms) {
            double price = Double.parseDouble(room[2]);
            roomDropdown.addItem(new ComboItem(room[0], room[1] + " (Rs." + price + ")", price));
        }

        saveButton.addActionListener(e -> {
            ComboItem selectedCustomer = (ComboItem) customerDropdown.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) roomDropdown.getSelectedItem();

            if (checkInChooser.getDate() == null || checkOutChooser.getDate() == null) {
                JOptionPane.showMessageDialog(frame, "Please select dates!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date inDate = checkInChooser.getDate();
            Date outDate = checkOutChooser.getDate();
            if (outDate.before(inDate) || outDate.equals(inDate)) {
                JOptionPane.showMessageDialog(frame, "Check-Out must be after Check-In!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            long diff = Math.abs(outDate.getTime() - inDate.getTime());
            long totalDays = diff / (24 * 60 * 60 * 1000);
            double totalAmount = selectedRoom.getPrice() * totalDays;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String res = bookingController.saveBooking(selectedCustomer.getId(), selectedRoom.getId(), sdf.format(inDate), sdf.format(outDate));

            if (res.equals("Booking successful!")) {
                JOptionPane.showMessageDialog(frame, res);
                new BillView(selectedCustomer.getDisplayValue(), selectedRoom.getDisplayValue(), sdf.format(inDate), sdf.format(outDate), totalDays, totalAmount);
                frame.dispose();
                Dashboard.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, res, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backBtn.addActionListener(e -> { frame.dispose(); Dashboard.main(null); });

        frame.add(customerLabel); frame.add(customerDropdown);
        frame.add(roomLabel); frame.add(roomDropdown);
        frame.add(checkInLabel); frame.add(checkInChooser);
        frame.add(checkOutLabel); frame.add(checkOutChooser);
        frame.add(saveButton); frame.add(backBtn);

        frame.setSize(370, 360);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

class ComboItem {
    private String id;
    private String displayValue;
    private double price;

    public ComboItem(String id, String displayValue, double price) {
        this.id = id;
        this.displayValue = displayValue;
        this.price = price;
    }

    public String getId() { return id; }
    public String getDisplayValue() { return displayValue; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return displayValue;
    }
}