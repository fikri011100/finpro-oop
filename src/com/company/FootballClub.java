package com.company;

public class FootballClub extends SportsClub {

    public int winCount;
    public int drawCount;
    public int defeatCount;
    public int scoredGoalsCount;
    public int receivedGoalsCount;
    public int points;
    public int matchesPlayed;

    public int getWinCount(){
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getDefeatCount(){
        return defeatCount;
    }

    public int getScoredGoalsCount() {
        return scoredGoalsCount;
    }

    public int getReceivedGoalsCount() {
        return receivedGoalsCount;
    }

    public int getPoints() {
        return points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setWinCount(int i) {
        winCount=i;
    }

    public void setDrawCount(int i){
        drawCount = i;
    }

    public void setDefeatCount(int i) {
        defeatCount=i;
    }

    public void setScoredGoalsCount(int i){
        scoredGoalsCount = i;
    }

    public void setRecievedGoalsCount(int i){
        receivedGoalsCount = i;
    }

    public void setPoints(int i){
        points = i;
    }

    public void setMatchesPlayed(int i){
        matchesPlayed = i;
    }

}

