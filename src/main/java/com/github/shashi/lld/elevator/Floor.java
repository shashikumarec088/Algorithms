package com.github.shashi.lld.elevator;

import java.util.List;

public class Floor {
    private List<Display> display;
    private List<HallPanel> panel;

    public boolean isBottomMost() {
        return false;
    }

    public boolean isTopMost() {
        return false;
    }
}