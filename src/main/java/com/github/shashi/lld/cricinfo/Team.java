package com.github.shashi.lld.cricinfo;

import java.util.List;

public class Team {
    private String name;
    private List<Player> players;
    private Coach coach;
    private List<News> news;
    private TeamStat stats;

    public boolean addSquad(TournamentSquad squad) {
        return false;
    }

    public boolean addPlayer(Player player) {
        return false;
    }

    public boolean addNews(News news) {
        return false;
    }
}
