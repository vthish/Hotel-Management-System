package dao;

import java.sql.*;

public class UserDAO {

    public boolean login(String username, String password) {

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            return rs.next(); // true if found

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}