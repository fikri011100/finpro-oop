package com.company.model;

public abstract class SportsClub {

    public String name;
    public String location;
    public String coach;
    public String statistics;

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((SportsClub)o).name);
    }

    public SportsClub(String name, String location, String coach, String statistics) {
        this.name = name;
        this.location = location;
        this.coach = coach;
        this.statistics = statistics;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getStatistics() {
        return statistics;
    }

    public void setLocation(String s) {
        this.location = s;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setStatistics(String s) {
        this.statistics = s;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

}
