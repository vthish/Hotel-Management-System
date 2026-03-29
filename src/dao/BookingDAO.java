package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Date;

public class BookingDAO {

    private RoomDAO roomDAO = new RoomDAO();

    public boolean addBooking(int customerId, int roomId, Date checkIn, Date checkOut) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO booking(customer_id, room_id, check_in, check_out) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setInt(1, customerId);
            ps.setInt(2, roomId);
            ps.setDate(3, checkIn);
            ps.setDate(4, checkOut);

            int result = ps.executeUpdate();

            if (result > 0) {
                roomDAO.updateRoomStatus(roomId, "Occupied");
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}