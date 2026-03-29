package controller;

import dao.RoomDAO;
import java.util.List;

public class RoomController {

    private RoomDAO roomDAO = new RoomDAO();

    public String saveRoom(String roomNumber, String type, String priceText, String status) {
        if (roomNumber == null || roomNumber.trim().isEmpty() || type == null || type.trim().isEmpty() || priceText == null || priceText.trim().isEmpty()) {
            return "All fields are required!";
        }
        try {
            double price = Double.parseDouble(priceText);
            if (price <= 0) return "Price must be greater than zero.";
            boolean isAdded = roomDAO.addRoom(roomNumber, type, price, status);
            return isAdded ? "Room added successfully!" : "Room number already exists!";
        } catch (NumberFormatException e) {
            return "Invalid price format.";
        }
    }

    public List<String[]> getAvailableRooms() {
        return roomDAO.getAvailableRooms();
    }

    public int getAvailableRoomCount() {
        return roomDAO.getAvailableRoomCount();
    }

    public List<String[]> getAllRooms() {
        return roomDAO.getAllRooms();
    }

    public void updateRoom(String roomNumber, String type, String priceText, String status) {
        try {
            double price = Double.parseDouble(priceText);
            roomDAO.updateRoomData(roomNumber, type, price, status);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void deleteRoom(String roomNumber) {
        roomDAO.deleteRoom(roomNumber);
    }
}