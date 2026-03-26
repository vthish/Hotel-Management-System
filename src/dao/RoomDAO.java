package dao;

import dao.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class RoomDAO {

    public void addRoom(String roomNumber, String type, double price, String status) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO room(room_number, type, price, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, roomNumber);
            ps.setString(2, type);
            ps.setDouble(3, price);
            ps.setString(4, status);

            ps.executeUpdate();

            System.out.println("Room added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}