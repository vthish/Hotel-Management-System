package dao;

import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

public class BookingDAO {

    public void addBooking(int customerId, int roomId, Date checkIn, Date checkOut) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO booking(customer_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setInt(2, roomId);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);

            ps.executeUpdate();

            System.out.println("Booking added");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}