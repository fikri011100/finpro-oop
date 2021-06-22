package com.company.model;

public abstract class SportsClub {

    public String name;
    public String location;
    public String coach;

    @Override
    public boolean equals(Object o) {
        return this.name.equals(((SportsClub)o).name);
    }

    public SportsClub(String name, String location, String coach) {
        this.name = name;
        this.location = location;
        this.coach = coach;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String s) {
        this.location = s;
    }

    public void setName(String s) {
        this.name = s;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

}
