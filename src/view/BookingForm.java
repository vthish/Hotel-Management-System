package view;

import controller.BookingController;
import controller.CustomerController;
import controller.RoomController;

// මේක තමයි අලුතෙන් add කරපු JCalendar library එකේ class එක
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

        JLabel customerLabel = new JLabel("Select Customer");
        JLabel roomLabel = new JLabel("Select Available Room");
        JLabel checkInLabel = new JLabel("Check-In Date");
        JLabel checkOutLabel = new JLabel("Check-Out Date");

        JComboBox<ComboItem> customerDropdown = new JComboBox<>();
        JComboBox<ComboItem> roomDropdown = new JComboBox<>();

        // Check-In Date Chooser (Calendar)
        JDateChooser checkInChooser = new JDateChooser();
        checkInChooser.setDateFormatString("yyyy-MM-dd");
        checkInChooser.setMinSelectableDate(new Date()); // අදින් කලින් දවස් select කරන්න බැරි වෙන්න block කරනවා

        // Check-Out Date Chooser (Calendar)
        JDateChooser checkOutChooser = new JDateChooser();
        checkOutChooser.setDateFormatString("yyyy-MM-dd");
        checkOutChooser.setMinSelectableDate(new Date()); // අදින් කලින් දවස් select කරන්න බැරි වෙන්න block කරනවා

        JButton saveButton = new JButton("Save Booking");
        JButton backBtn = new JButton("Back");

        // Bounds හදනවා
        customerLabel.setBounds(50, 10, 250, 20);
        customerDropdown.setBounds(50, 30, 250, 30);

        roomLabel.setBounds(50, 70, 250, 20);
        roomDropdown.setBounds(50, 90, 250, 30);

        checkInLabel.setBounds(50, 130, 250, 20);
        checkInChooser.setBounds(50, 150, 250, 30); // JDateChooser එකට ඉඩ දෙනවා

        checkOutLabel.setBounds(50, 190, 250, 20);
        checkOutChooser.setBounds(50, 210, 250, 30); // JDateChooser එකට ඉඩ දෙනවා

        saveButton.setBounds(50, 260, 130, 30);
        backBtn.setBounds(190, 260, 110, 30);

        // Controllers
        CustomerController customerController = new CustomerController();
        RoomController roomController = new RoomController();
        BookingController bookingController = new BookingController();

        // Customer සහ Room Data Dropdowns වලට Load කරනවා
        List<String[]> customers = customerController.getCustomers();
        for (String[] customer : customers) {
            customerDropdown.addItem(new ComboItem(customer[0], customer[1] + " - " + customer[2]));
        }

        List<String[]> rooms = roomController.getAvailableRooms();
        for (String[] room : rooms) {
            roomDropdown.addItem(new ComboItem(room[0], room[1] + " (Rs." + room[2] + ")"));
        }

        // Save Button Action
        saveButton.addActionListener(e -> {
            ComboItem selectedCustomer = (ComboItem) customerDropdown.getSelectedItem();
            ComboItem selectedRoom = (ComboItem) roomDropdown.getSelectedItem();

            // Dates තෝරලා නැත්නම් Error එකක් දෙනවා
            if (checkInChooser.getDate() == null || checkOutChooser.getDate() == null) {
                JOptionPane.showMessageDialog(frame, "Please select both Check-In and Check-Out dates from the calendar!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedCustomer == null || selectedRoom == null) {
                JOptionPane.showMessageDialog(frame, "Please ensure Customers and Rooms are available!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String customerId = selectedCustomer.getId();
            String roomId = selectedRoom.getId();

            // තෝරපු Date එක yyyy-MM-dd විදිහට String එකකට හරවනවා
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String checkIn = sdf.format(checkInChooser.getDate());
            String checkOut = sdf.format(checkOutChooser.getDate());

            // Controller එකට යවනවා
            String result = bookingController.saveBooking(customerId, roomId, checkIn, checkOut);

            if (result.equals("Booking successful!")) {
                JOptionPane.showMessageDialog(frame, result, "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
                Dashboard.main(null);
            } else {
                JOptionPane.showMessageDialog(frame, result, "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Back Button Action
        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        // UI එකට Components add කරනවා
        frame.add(customerLabel);
        frame.add(customerDropdown);
        frame.add(roomLabel);
        frame.add(roomDropdown);
        frame.add(checkInLabel);
        frame.add(checkInChooser);
        frame.add(checkOutLabel);
        frame.add(checkOutChooser);
        frame.add(saveButton);
        frame.add(backBtn);

        frame.setSize(370, 360);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// Dropdown Helper Class
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