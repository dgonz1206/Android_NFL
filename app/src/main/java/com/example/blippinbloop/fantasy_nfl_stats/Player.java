package com.example.blippinbloop.fantasy_nfl_stats;
public class Player {
    private String team;
    private String firstName;
    private String lastName;
    private String pos;

    public Player(String team, String firstName, String lastName, String pos) {
        this.team = team;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pos = pos;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }
}