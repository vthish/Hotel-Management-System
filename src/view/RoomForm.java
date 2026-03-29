package view;

import controller.RoomController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class RoomForm {

    private JFrame frame;
    private JTextField searchField;
    private JTable roomTable;
    private DefaultTableModel tableModel;
    private TableRowSorter<DefaultTableModel> sorter;
    private RoomController roomController;

    public RoomForm() {
        roomController = new RoomController();
        createAndShowGUI();
        loadRoomsFromDB();
    }

    private void createAndShowGUI() {
        frame = new JFrame("Manage Rooms");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Search:"));
        searchField = new JTextField(20);
        topPanel.add(searchField);

        String[] columns = {"Room No", "Type", "Price", "Status"};
        tableModel = new DefaultTableModel(columns, 0);
        roomTable = new JTable(tableModel);

        sorter = new TableRowSorter<>(tableModel);
        roomTable.setRowSorter(sorter);

        JScrollPane scrollPane = new JScrollPane(roomTable);

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

        addBtn.addActionListener(e -> {
            String roomNo = JOptionPane.showInputDialog(frame, "Enter Room No:");
            if (roomNo == null || roomNo.trim().isEmpty()) return;

            String[] types = {"Single", "Double", "Suite"};
            String type = (String) JOptionPane.showInputDialog(frame, "Select Room Type:", "Room Type", JOptionPane.QUESTION_MESSAGE, null, types, types[0]);
            if (type == null) return;

            String price = JOptionPane.showInputDialog(frame, "Enter Price:");
            if (price == null || price.trim().isEmpty()) return;

            String[] statuses = {"Available", "Occupied"};
            String status = (String) JOptionPane.showInputDialog(frame, "Select Status:", "Room Status", JOptionPane.QUESTION_MESSAGE, null, statuses, statuses[0]);
            if (status == null) return;

            String result = roomController.saveRoom(roomNo, type, price, status);
            JOptionPane.showMessageDialog(frame, result);
            loadRoomsFromDB();
        });

        updateBtn.addActionListener(e -> {
            int row = roomTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Select a row first!");
                return;
            }
            int modelRow = roomTable.convertRowIndexToModel(row);

            String roomNo = tableModel.getValueAt(modelRow, 0).toString();

            String[] types = {"Single", "Double", "Suite"};
            String type = (String) JOptionPane.showInputDialog(frame, "Select Room Type:", "Room Type", JOptionPane.QUESTION_MESSAGE, null, types, tableModel.getValueAt(modelRow, 1));
            if (type == null) return;

            String price = JOptionPane.showInputDialog(frame, "Enter Price:", tableModel.getValueAt(modelRow, 2));
            if (price == null || price.trim().isEmpty()) return;

            String[] statuses = {"Available", "Occupied"};
            String status = (String) JOptionPane.showInputDialog(frame, "Select Status:", "Room Status", JOptionPane.QUESTION_MESSAGE, null, statuses, tableModel.getValueAt(modelRow, 3));
            if (status == null) return;

            roomController.updateRoom(roomNo, type, price, status);
            JOptionPane.showMessageDialog(frame, "Room updated successfully!");
            loadRoomsFromDB();
        });

        deleteBtn.addActionListener(e -> {
            int row = roomTable.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(frame, "Select a row first!");
                return;
            }
            int modelRow = roomTable.convertRowIndexToModel(row);
            String roomNo = tableModel.getValueAt(modelRow, 0).toString();

            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete Room " + roomNo + "?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                roomController.deleteRoom(roomNo);
                JOptionPane.showMessageDialog(frame, "Room deleted successfully!");
                loadRoomsFromDB();
            }
        });

        backBtn.addActionListener(e -> {
            frame.dispose();
            Dashboard.main(null);
        });

        frame.setVisible(true);
    }

    private void loadRoomsFromDB() {
        tableModel.setRowCount(0);
        List<String[]> rooms = roomController.getAllRooms();
        for (String[] room : rooms) {
            tableModel.addRow(room);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomForm::new);
    }
}