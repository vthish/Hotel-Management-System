package dao;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;

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
}