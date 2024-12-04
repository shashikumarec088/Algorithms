package com.github.shashi.lld.cricinfo.example.scoreupdater;

import com.github.shashi.lld.cricinfo.example.innings.BallDetails;

public interface ScoreUpdaterObserver {
    public void update(BallDetails ballDetails);
}
