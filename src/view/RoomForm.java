package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.RowFilter;
import java.awt.*;
import java.awt.event.*;

public class RoomForm {

    private JFrame frame;
    private JTextField searchField;
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;

    public RoomForm() {
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Hotel Management System");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Top panel with search
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        topPanel.add(searchField);

        // Table
        String[] columns = {"Room No", "Type", "Price", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        roomTable = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);
        roomTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(roomTable);

        // Dummy data
        addRoom("101", "Single", "1000", "Available");
        addRoom("102", "Double", "1500", "Occupied");
        addRoom("103", "Suite", "2500", "Available");

        // Bottom panel with buttons
        JPanel bottomPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton backBtn = new JButton("Back");

        bottomPanel.add(addBtn);
        bottomPanel.add(updateBtn);
        bottomPanel.add(deleteBtn);
        bottomPanel.add(backBtn);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Search feature
        searchField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String query = searchField.getText();
                if (query.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
                }
            }
        });

        // Add Room with Dropdowns
        addBtn.addActionListener(e -> {
            String roomNo = JOptionPane.showInputDialog(frame, "Enter Room No:");
            if (roomNo == null || roomNo.isEmpty()) return;

            String[] types = {"Single", "Double", "Suite"};
            String type = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select Room Type:",
                    "Room Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    types,
                    types[0]
            );
            if (type == null) return;

            String price = JOptionPane.showInputDialog(frame, "Enter Price:");
            if (price == null || price.isEmpty()) return;

            String[] statuses = {"Available", "Occupied"};
            String status = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select Status:",
                    "Room Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statuses,
                    statuses[0]
            );
            if (status == null) return;

            addRoom(roomNo, type, price, status);
        });

        // Update Room with Dropdowns
        updateBtn.addActionListener(e -> {
            int row = roomTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Select a row first!");
                return;
            }
            int modelRow = roomTable.convertRowIndexToModel(row);

            String roomNo = JOptionPane.showInputDialog(frame, "Enter Room No:", tableModel.getValueAt(modelRow, 0));
            if (roomNo == null) return;

            String[] types = {"Single", "Double", "Suite"};
            String type = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select Room Type:",
                    "Room Type",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    types,
                    tableModel.getValueAt(modelRow, 1)
            );
            if (type == null) return;

            String price = JOptionPane.showInputDialog(frame, "Enter Price:", tableModel.getValueAt(modelRow, 2));
            if (price == null) return;

            String[] statuses = {"Available", "Occupied"};
            String status = (String) JOptionPane.showInputDialog(
                    frame,
                    "Select Status:",
                    "Room Status",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    statuses,
                    tableModel.getValueAt(modelRow, 3)
            );
            if (status == null) return;

            tableModel.setValueAt(roomNo, modelRow, 0);
            tableModel.setValueAt(type, modelRow, 1);
            tableModel.setValueAt(price, modelRow, 2);
            tableModel.setValueAt(status, modelRow, 3);
        });

        // Delete Room
        deleteBtn.addActionListener(e -> {
            String roomNo = JOptionPane.showInputDialog(frame, "Enter Room No to delete:");
            if (roomNo == null || roomNo.isEmpty()) return;

            boolean found = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).toString().equals(roomNo)) {
                    tableModel.removeRow(i);
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "Room No not found!");
            }
        });

        // Back button
        backBtn.addActionListener(e -> {
            frame.dispose();
            new Dashboard();
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void addRoom(String no, String type, String price, String status) {
        tableModel.addRow(new Object[]{no, type, price, status});
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomForm::new);
    }
}