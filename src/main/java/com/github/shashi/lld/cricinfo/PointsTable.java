package com.github.shashi.lld.cricinfo;

import java.util.Date;
import java.util.HashMap;

public class PointsTable {
    private HashMap<String, Float> teamPoints;
    private HashMap<Team, MatchResult> matchResults;
    private Tournament tournament;
    private Date lastUpdated;
}