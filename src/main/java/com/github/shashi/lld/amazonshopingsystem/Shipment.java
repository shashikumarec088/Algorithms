package com.github.shashi.lld.amazonshopingsystem;

import java.util.Date;
import java.util.List;

public class Shipment {
    private String shipmentNumber;
    private Date shipmentDate;
    private Date estimatedArrival;
    private String shipmentMethod;
    private List<ShipmentLog> shipmentLogs;

    public boolean addShipmentLog(ShipmentLog shipmentLog) {
        return false;
    }
}