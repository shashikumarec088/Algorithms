package com.github.shashi.lld.jigsawpuzzle;

import java.util.Arrays;
import java.util.List;

public class Piece {
    private List<Side> sides = Arrays.asList();
    public boolean checkCorner() {
        return false;
    }
    public boolean checkEdge() {
        return false;
    }
    public boolean checkMiddle() {
        return false;
    }
}