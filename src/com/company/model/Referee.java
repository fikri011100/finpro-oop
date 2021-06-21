package com.company.model;

public class Referee extends People{

    private String since;
    private String role;

    public Referee(String name, int age) {
        super(name, age);
    }

    public Referee(String name, int age, String since, String role) {
        super(name, age);
        this.since = since;
        this.role = role;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
