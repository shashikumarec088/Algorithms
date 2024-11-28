package com.github.shashi.lld.stockbrokeragesystem;

import java.util.Date;
import java.util.HashMap;

public abstract class Order {
    private String orderNumber;
    public boolean isBuyOrder;
    private OrderStatus status;
    private TimeEnforcementType timeEnforcement;
    private Date creationTime;
    private HashMap<Integer, OrderPart> parts;

    public void setStatus(OrderStatus status) {

    }

    public boolean saveInDatabase() {


        return false;
    }

    public void addOrderParts(OrderPart parts) {
    }
}
