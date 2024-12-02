package com.github.shashi.lld.cricinfo;

import java.util.Date;
import java.util.List;

public class Innings {
    private Playing11 bowling;
    private Playing11 batting;
    private Date startTime;
    private Date endTime;
    private int totalScores;
    private int totalWickets;
    private List<Over> overs;

    public boolean addOver(Over over) {
        return false;
    }
}