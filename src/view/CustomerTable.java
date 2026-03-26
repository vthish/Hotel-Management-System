package view;

import controller.CustomerController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class CustomerTable {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Customer List");

        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        String[] columns = {"ID", "Name", "Phone", "Email"};

        DefaultTableModel model = new DefaultTableModel(columns, 0);

        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 20, 450, 200);

        CustomerController controller = new CustomerController();

        List<String[]> data = controller.getCustomers();

        for (String[] row : data) {
            model.addRow(row);
        }

        frame.add(scrollPane);

        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}