package model;

import java.sql.Date;

public class Booking {

    private int id;
    private int customerId;
    private int roomId;
    private Date checkIn;
    private Date checkOut;

    public Booking() {}

    public Booking(int customerId, int roomId, Date checkIn, Date checkOut) {
        this.customerId = customerId;
        this.roomId = roomId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomId() {
        return roomId;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }
}