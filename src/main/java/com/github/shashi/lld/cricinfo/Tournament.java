package com.github.shashi.lld.cricinfo;

import java.util.Date;
import java.util.List;

public class Tournament {
    private Date startDate;
    private List<TournamentSquad> teams;
    private List<Match> matches;
    private PointsTable points;

    public boolean addTeam(TournamentSquad team) {
        return false;
    }

    public boolean addMatch(Match match) {
        return false;
    }
}