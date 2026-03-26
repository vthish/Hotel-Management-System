package view;

import controller.RoomController;

import javax.swing.*;

public class RoomForm {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Room Form");

        JTextField roomNumber = new JTextField();
        roomNumber.setBounds(50, 30, 200, 30);

        JTextField type = new JTextField();
        type.setBounds(50, 70, 200, 30);

        JTextField price = new JTextField();
        price.setBounds(50, 110, 200, 30);

        JTextField status = new JTextField();
        status.setBounds(50, 150, 200, 30);

        JButton saveBtn = new JButton("Save");
        saveBtn.setBounds(50, 200, 100, 30);

        RoomController controller = new RoomController();

        saveBtn.addActionListener(e -> {
            controller.saveRoom(
                    roomNumber.getText(),
                    type.getText(),
                    price.getText(),
                    status.getText()
            );
        });

        frame.add(roomNumber);
        frame.add(type);
        frame.add(price);
        frame.add(status);
        frame.add(saveBtn);

        frame.setSize(300, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}