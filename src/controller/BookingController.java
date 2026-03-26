package controller;

import dao.BookingDAO;
import java.sql.Date;

public class BookingController {

    private BookingDAO dao = new BookingDAO();

    public void saveBooking(String customerIdText, String roomIdText, String checkInText, String checkOutText) {

        int customerId;
        int roomId;

        try {
            customerId = Integer.parseInt(customerIdText);
            roomId = Integer.parseInt(roomIdText);
        } catch (Exception e) {
            System.out.println("Invalid IDs");
            return;
        }

        try {
            Date checkIn = Date.valueOf(checkInText);
            Date checkOut = Date.valueOf(checkOutText);

            dao.addBooking(customerId, roomId, checkIn, checkOut);

        } catch (Exception e) {
            System.out.println("Invalid date format (use YYYY-MM-DD)");
        }
    }
}