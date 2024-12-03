package com.github.shashi.lld.cricinfo.example.team;

import com.github.shashi.lld.cricinfo.example.team.player.PlayerBattingController;
import com.github.shashi.lld.cricinfo.example.team.player.PlayerBowlingController;
import com.github.shashi.lld.cricinfo.example.team.player.PlayerDetails;

import java.util.List;
import java.util.Queue;

public class Team {
    public String name;
    public Queue<PlayerDetails> playing11;
    public List<PlayerDetails> bench;
    public PlayerBattingController playerBattingController;
    public PlayerBowlingController playerBowlingController;
    public boolean isWinner;

    public Team(String name, Queue<PlayerDetails> playing11, List<PlayerDetails> bench, List<PlayerDetails> bowlers){
        this.name = name;
        this.playing11 = playing11;
        this.bench = bench;
        playerBattingController = new PlayerBattingController(playing11);
        playerBowlingController = new PlayerBowlingController(bowlers);
    }

    public String getTeamName() {
        return name;
    }

    public void chooseNextBatsMan() throws Exception{
        playerBattingController.getNextPlayer();
    }

    public void chooseNextBowler(int maxOverCountPerBowler){
        playerBowlingController.getNextBowler(maxOverCountPerBowler);
    }

    public PlayerDetails getStriker() {
        return playerBattingController.getStriker();
    }

    public PlayerDetails getNonStriker() {
        return playerBattingController.getNonStriker();
    }

    public void setStriker(PlayerDetails player) {
        playerBattingController.setStriker(player);
    }

    public void setNonStriker(PlayerDetails player) {
        playerBattingController.setNonStriker(player);
    }

    public PlayerDetails getCurrentBowler() {
        return playerBowlingController.getCurrentBowler();
    }

    public void printBattingScoreCard(){

        for(PlayerDetails playerDetails : playing11){
            playerDetails.printBattingScoreCard();
        }
    }

    public void printBowlingScoreCard(){

        for(PlayerDetails playerDetails : playing11){
            if(playerDetails.bowlingScoreCard.totalOversCount > 0) {
                playerDetails.printBowlingScoreCard();
            }
        }
    }

    public int getTotalRuns(){
        int totalRun=0;
        for (PlayerDetails player :  playing11){

            totalRun+=player.battingScoreCard.totalRuns;
        }
        return totalRun;
    }








}
