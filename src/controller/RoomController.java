package controller;

import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

public class RoomController {

    // Example room list
    private List<String[]> rooms = new ArrayList<>();

    public RoomController() {
        // dummy data
        rooms.add(new String[]{"101", "Single", "1000", "Available"});
        rooms.add(new String[]{"102", "Double", "1500", "Occupied"});
        rooms.add(new String[]{"103", "Suite", "2500", "Available"});
    }

    public List<String[]> getRooms() {
        return rooms;
    }

    // method to update status
    public void updateRoomStatus(String roomId, String status) {
        for (String[] room : rooms) {
            if (room[0].equals(roomId)) {
                room[3] = status; // 3rd index = Status
                break;
            }
        }
    }
}