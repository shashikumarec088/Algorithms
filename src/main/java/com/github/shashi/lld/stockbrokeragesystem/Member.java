package com.github.shashi.lld.stockbrokeragesystem;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Member extends Account {
    private double availableFundsForTrading;
    private Date dateOfMembership;
    private HashMap<String, StockPosition> stockPositions;
    private HashMap<Integer, Order> activeOrders;

    public ErrorCode placeSellLimitOrder(String stockId, float quantity, int limitPrice, TimeEnforcementType enforcementType) {
        return null;
    }

    public ErrorCode placeBuyLimitOrder(String stockId, float quantity, int limitPrice, TimeEnforcementType enforcementType) {


        return null;
    }

    public void callbackStockExchange(int orderId, List<OrderPart> orderParts, OrderStatus status) {
    }

    public boolean resetPassword(){
        // definition
        return false;
    }
}