package com.github.shashi.lld.amazonlocker;

import java.util.Date;

public class LockerPackage extends Package {
    private int codeValidDays;
    private String lockerId;
    private String packageId;
    private String code;
    private Date packageDeliveryTime;

    public boolean isValidCode() {
        return false;
    }

    public boolean verifyCode(String code) {
        return false;
    }
}