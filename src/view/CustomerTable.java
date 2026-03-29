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

        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        updateBtn.setBounds(50, 230, 100, 30);
        deleteBtn.setBounds(180, 230, 100, 30);
        backBtn.setBounds(310, 230, 100, 30);

        CustomerController controller = new CustomerController();

        List<String[]> data = controller.getCustomers();
        for (String[] row : data) {
            model.addRow(row);
        }

        updateBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row first");
                return;
            }

            String id = model.getValueAt(selectedRow, 0).toString();
            String name = JOptionPane.showInputDialog("Enter Name:", model.getValueAt(selectedRow, 1));
            if (name == null) return;

            String phone = JOptionPane.showInputDialog("Enter Phone:", model.getValueAt(selectedRow, 2));
            if (phone == null) return;

            String email = JOptionPane.showInputDialog("Enter Email:", model.getValueAt(selectedRow, 3));
            if (email == null) return;

            String result = controller.updateCustomer(id, name, phone, email);

            if (result.equals("Customer updated successfully!")) {
                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(phone, selectedRow, 2);
                model.setValueAt(email, selectedRow, 3);
                JOptionPane.showMessageDialog(frame, result, "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, result, "Validation Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(frame, "Select a row first");
                return;
            }

            String id = model.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(frame, "Delete this customer?", "Confirm", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                controller.deleteCustomer(id);
                model.removeRow(selectedRow);
                JOptionPane.showMessageDialog(frame, "Deleted Successfully");
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.add(scrollPane);
        frame.add(updateBtn);
        frame.add(deleteBtn);
        frame.add(backBtn);

        frame.setSize(500, 320);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}