package com.github.shashi.lld.hotelmanagementsystem;

import java.util.Date;
import java.util.List;

public interface Search {
    public List<Room> search(RoomStyle style, Date date, int duration);
}
