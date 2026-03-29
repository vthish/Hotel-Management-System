package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    public boolean addRoom(String roomNumber, String type, double price, String status) {
        try {
            Connection con = DBConnection.getConnection();
            String checkSql = "SELECT * FROM room WHERE room_number=?";
            PreparedStatement checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, roomNumber);
            ResultSet rs = checkPs.executeQuery();
            if(rs.next()) return false;

            String sql = "INSERT INTO room(room_number, type, price, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNumber);
            ps.setString(2, type);
            ps.setDouble(3, price);
            ps.setString(4, status);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<String[]> getAvailableRooms() {
        List<String[]> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT id, room_number, type, price FROM room WHERE status='Available'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = {
                        rs.getString("id"),
                        rs.getString("room_number") + " - " + rs.getString("type"),
                        rs.getString("price")
                };
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getAvailableRoomCount() {
        int count = 0;
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT COUNT(*) AS total FROM room WHERE status='Available'";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public List<String[]> getAllRooms() {
        List<String[]> list = new ArrayList<>();
        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT room_number, type, price, status FROM room";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = {rs.getString("room_number"), rs.getString("type"), rs.getString("price"), rs.getString("status")};
                list.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void updateRoomStatus(int roomId, String status) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE room SET status=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, status);
            ps.setInt(2, roomId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateRoomData(String roomNumber, String type, double price, String status) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE room SET type=?, price=?, status=? WHERE room_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, type);
            ps.setDouble(2, price);
            ps.setString(3, status);
            ps.setString(4, roomNumber);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(String roomNumber) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM room WHERE room_number=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, roomNumber);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}