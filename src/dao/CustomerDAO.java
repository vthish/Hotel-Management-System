package dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public void addCustomer(String name, String phone, String email) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO customer(name, phone, email) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);

            ps.executeUpdate();

            System.out.println("Customer added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // GET ALL CUSTOMERS
    public List<String[]> getAllCustomers() {

        List<String[]> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM customer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = {
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("phone"),
                        rs.getString("email")
                };
                list.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    // UPDATE CUSTOMER
    public void updateCustomer(String id, String name, String phone, String email) {

        try {
            Connection con = DBConnection.getConnection();
            String sql = "UPDATE customer SET name=?, phone=?, email=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, email);
            ps.setString(4, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // DELETE CUSTOMER
    public void deleteCustomer(String id) {

        try {
            Connection con = DBConnection.getConnection();
            String sql = "DELETE FROM customer WHERE id=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}