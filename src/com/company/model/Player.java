package com.company.model;

import com.company.interfaces.Card;

public class Player extends People implements Card {

    private int number;
    private String team;
    private String position;
    private int redCard;
    private int yellowCard;

    public Player(String name, int age) {
        super(name, age);
    }

    public Player(String name, int age, int redCard, int yellowCard) {
        super(name, age);
        this.redCard = redCard;
        this.yellowCard = yellowCard;
    }

    public Player(String name, int age, int number, String team, String position, int redCard, int yellowCard) {
        super(name, age);
        this.number = number;
        this.team = team;
        this.position = position;
        this.redCard = redCard;
        this.yellowCard = yellowCard;
    }

    public Player(String name, int age, String team, String position, int number) {
        super(name, age);
        this.team = team;
        this.position = position;
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
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

    @Override
    public void redCard() {
        redCard++;
    }

    @Override
    public void yellowCard() {
        yellowCard++;
    }
}
