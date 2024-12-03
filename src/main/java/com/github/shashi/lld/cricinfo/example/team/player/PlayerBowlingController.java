package com.github.shashi.lld.cricinfo.example.team.player;

import java.util.*;

public class PlayerBowlingController {
    Deque<PlayerDetails> bowlersList;
    Map<PlayerDetails,Integer> bowlerVsOverCount;
    PlayerDetails currentBowler;

    public PlayerBowlingController(List<PlayerDetails> bowlers){
        bowlersList = new LinkedList<>();
        bowlerVsOverCount = new HashMap<>();
        for(PlayerDetails playerDetail : bowlers){
            bowlerVsOverCount.put(playerDetail,0);
            bowlersList.add(playerDetail);
        }
    }

    public void getNextBowler(int maxOverCountPerBowler){
        PlayerDetails playerDetails = bowlersList.poll();
        if(bowlerVsOverCount.get(playerDetails)+1 == maxOverCountPerBowler){
            currentBowler=playerDetails;
        }else{
            bowlerVsOverCount.put(playerDetails,bowlerVsOverCount.get(playerDetails)+1);
            currentBowler=playerDetails;
            bowlersList.addLast(playerDetails);
        }
    }

    public PlayerDetails getCurrentBowler(){
        return currentBowler;
    }
}
