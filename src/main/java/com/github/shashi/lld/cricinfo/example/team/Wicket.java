package com.github.shashi.lld.cricinfo.example.team;

import com.github.shashi.lld.cricinfo.example.innings.BallDetails;
import com.github.shashi.lld.cricinfo.example.innings.OverDetails;
import com.github.shashi.lld.cricinfo.example.team.player.PlayerDetails;

public class Wicket {
    public WicketType wicketType;
    public PlayerDetails takenBy;
    public OverDetails overDetails;
    public BallDetails ballDetails;

    public Wicket(WicketType wicketType, PlayerDetails takenBy, OverDetails overDetails, BallDetails ballDetails){
        this.wicketType = wicketType;
        this.takenBy = takenBy;
        this.overDetails = overDetails;
        this.ballDetails = ballDetails;
    }
}
