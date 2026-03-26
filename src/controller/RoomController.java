package controller;

import dao.RoomDAO;

public class RoomController {

    private RoomDAO roomDAO = new RoomDAO();

    public void saveRoom(String roomNumber, String type, String priceText, String status) {

        if (roomNumber.isEmpty()) {
            System.out.println("Room number required");
            return;
        }

        double price;

        try {
            price = Double.parseDouble(priceText);
        } catch (Exception e) {
            System.out.println("Invalid price");
            return;
        }

        roomDAO.addRoom(roomNumber, type, price, status);
    }
}