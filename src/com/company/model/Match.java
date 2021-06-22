package com.company.model;

import com.company.model.FootballClub;

import java.util.Date;

public class Match {

    private FootballClub teamA;
    private FootballClub teamB;
    private int teamAScore;
    private int teamBScore;
    private String referee;
    private String assistantReferee;
    private String stadion;
    private Date date;
    private int redCard;
    private int yellowCard;

    public Match(FootballClub teamA, FootballClub teamB, int teamAScore, int teamBScore, String referee, String assistantReferee, String stadion, Date date, int redCard, int yellowCard) {
        this.teamA = teamA;
        this.teamB = teamB;
        this.teamAScore = teamAScore;
        this.teamBScore = teamBScore;
        this.referee = referee;
        this.assistantReferee = assistantReferee;
        this.stadion = stadion;
        this.date = date;
        this.redCard = redCard;
        this.yellowCard = yellowCard;
    }



    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public String getAssistantReferee() {
        return assistantReferee;
    }

    public void setAssistantReferee(String assistantReferee) {
        this.assistantReferee = assistantReferee;
    }

    public String getStadion() {
        return stadion;
    }

    public void setStadion(String stadion) {
        this.stadion = stadion;
    }

    public int getRedCard() {
        return redCard;
    }

    public void setRedCard(int redCard) {
        this.redCard = redCard;
    }

    public int getYellowCard() {
        return yellowCard;
    }

    public void setYellowCard(int yellowCard) {
        this.yellowCard = yellowCard;
    }

    public FootballClub getTeamA() {
        return teamA;
    }

    public FootballClub getTeamB() {
        return teamB;
    }

    public int getTeamAScore(){
        return teamAScore;
    }

    public int getTeamBScore(){
        return teamBScore;
    }

    public Date getDate() {
        return date;
    }

    public void setTeamA(FootballClub teamA) {
        this.teamA = teamA;
    }

    public void setTeamB(FootballClub teamB) {
        this.teamB = teamB;
    }

    public void setTeamAScore(int teamAScore) {
        this.teamAScore = teamAScore;
    }

    public void setTeamBScore(int teamBScore) {
        this.teamBScore = teamBScore;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
