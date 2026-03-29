package controller;

import dao.BookingDAO;
import java.sql.Date;

public class BookingController {

    private BookingDAO dao = new BookingDAO();

    public String saveBooking(String customerIdText, String roomIdText, String checkInText, String checkOutText) {

        if (customerIdText == null || customerIdText.trim().isEmpty() ||
                roomIdText == null || roomIdText.trim().isEmpty() ||
                checkInText == null || checkInText.trim().isEmpty() ||
                checkOutText == null || checkOutText.trim().isEmpty()) {
            return "All fields are required!";
        }

        int customerId;
        int roomId;

        try {
            customerId = Integer.parseInt(customerIdText);
            roomId = Integer.parseInt(roomIdText);
        } catch (Exception e) {
            return "Invalid Customer or Room Selection.";
        }

        try {
            Date checkIn = Date.valueOf(checkInText);
            Date checkOut = Date.valueOf(checkOutText);

            if (checkOut.before(checkIn) || checkOut.equals(checkIn)) {
                return "Check-out date must be after Check-in date!";
            }

            boolean isSaved = dao.addBooking(customerId, roomId, checkIn, checkOut);

            if(isSaved) {
                return "Booking successful!";
            } else {
                return "Failed to save booking. Please try again.";
            }

        } catch (IllegalArgumentException e) {
            return "Invalid date format. Use YYYY-MM-DD.";
        }
    }
}