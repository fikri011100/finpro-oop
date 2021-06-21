package com.company.model;

public class Player extends People {

    private String team;
    private String position;
    private int redCard;
    private int yellowCard;
    private int foul;

    public Player(String name, int age) {
        super(name, age);
    }

    public Player(String name, int age, int redCard, int yellowCard, int foul) {
        super(name, age);
        this.redCard = redCard;
        this.yellowCard = yellowCard;
        this.foul = foul;
    }

    public Player(String name, int age, String team, String position) {
        super(name, age);
        this.team = team;
        this.position = position;
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

    public int getFoul() {
        return foul;
    }

    public void setFoul(int foul) {
        this.foul = foul;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
