package view;

import db.DBConnection;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReportView {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Booking Reports");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        String[] columns = {"ID", "Customer", "Room", "Check-In", "Check-Out"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        JTable table = new JTable(model);

        try {
            Connection con = DBConnection.getConnection();
            String sql = "SELECT b.id, c.name, r.room_number, b.check_in, b.check_out FROM booking b " +
                    "JOIN customer c ON b.customer_id = c.id " +
                    "JOIN room r ON b.room_id = r.id";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                model.addRow(new Object[]{rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)});
            }
        } catch (Exception e) { e.printStackTrace(); }

        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(e -> { frame.dispose(); Dashboard.main(null); });

        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.add(backBtn, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}