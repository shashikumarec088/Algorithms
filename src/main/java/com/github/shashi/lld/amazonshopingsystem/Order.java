package com.github.shashi.lld.amazonshopingsystem;

import java.util.Date;
import java.util.List;

public class Order {
    private String orderNumber;
    private OrderStatus status;
    private Date orderDate;
    private List<OrderLog> orderLog;
    private ShoppingCart shoppingCart;

    public boolean sendForShipment() {
        return false;
    }

    public boolean makePayment(Payment payment) {
        return false;
    }

    public boolean addOrderLog(OrderLog orderLog) {
        return false;
    }
}