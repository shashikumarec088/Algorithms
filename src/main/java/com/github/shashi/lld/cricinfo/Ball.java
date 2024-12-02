package com.github.shashi.lld.cricinfo;

import java.util.List;

public class Ball {
    private Player balledBy;
    private Player playedBy;
    private BallType type;
    private List<Run> runs;
    private Wicket wicket;

    public boolean addCommentary(Commentary commentary) {
        return false;
    }
}