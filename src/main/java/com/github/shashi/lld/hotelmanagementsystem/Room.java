package com.github.shashi.lld.hotelmanagementsystem;

import java.util.List;

public class Room {
    private String roomNumber;
    private RoomStyle style;
    private RoomStatus status;
    private double bookingPrice;
    private boolean isSmoking;
    private List<RoomKey> keys;
    private List<RoomHousekeeping> housekeepingLog;

    public boolean isRoomAvailable() {
        return false;
    }

    public boolean checkin() {
        return false;
    }

    public boolean checkout() {
        return false;
    }
}