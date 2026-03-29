package controller;

import dao.RoomDAO;
import java.util.List;

public class RoomController {

    private RoomDAO roomDAO = new RoomDAO();

    public String saveRoom(String roomNumber, String type, String priceText, String status) {
        if (roomNumber == null || roomNumber.trim().isEmpty() ||
                type == null || type.trim().isEmpty() ||
                priceText == null || priceText.trim().isEmpty()) {
            return "All fields are required!";
        }

        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0) {
                return "Price must be greater than zero.";
            }

            boolean isAdded = roomDAO.addRoom(roomNumber, type, price, status);
            if (isAdded) {
                return "Room added successfully!";
            } else {
                return "Room number already exists!";
            }
        } catch (NumberFormatException e) {
            return "Invalid price format. Please enter a valid number.";
        }
    }

    public List<String[]> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    public List<String[]> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public void updateRoom(String roomNumber, String type, String priceText, String status) {
        try {
            double price = Double.parseDouble(priceText);
            roomDAO.updateRoomData(roomNumber, type, price, status);
        } catch (NumberFormatException e) {
            System.out.println("Invalid price format for update.");
        }
    }

    public void deleteRoom(String roomNumber) {
        roomDAO.deleteRoom(roomNumber);
    }
}