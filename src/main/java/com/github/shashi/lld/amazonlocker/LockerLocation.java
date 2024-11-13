package com.github.shashi.lld.amazonlocker;

import java.util.Date;
import java.util.List;

public class LockerLocation{
    private String name;
    private List<Locker> lockers;
    private double longitude;
    private double latitude;
    private Date openTime;
    private Date closeTime;
}