package com.example.blippinbloop.fantasy_nfl_stats;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class GameDay {
    String homeTeam;
    String awayTeam;
    int week;
    String startTime;
    String homeScoreTotal;
    String awayScoreTotal;


    public GameDay(String homeTeam, String awayTeam, int week, String startTime, String homeScoreTotal, String awayScoreTotal) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.week = week;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        TimeZone tz = TimeZone.getTimeZone("PST");
        sdf.setTimeZone(tz);


        try {
            Date dt = sdf.parse(startTime);
            this.startTime = dt.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //this.startTime = startTime;
        this.homeScoreTotal = homeScoreTotal;
        this.awayScoreTotal = awayScoreTotal;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getHomeScoreTotal() {
        return homeScoreTotal;
    }

    public void setHomeScoreTotal(String homeScoreTotal) {
        this.homeScoreTotal = homeScoreTotal;
    }

    public String getAwayScoreTotal() {
        return awayScoreTotal;
    }

    public void setAwayScoreTotal(String awayScoreTotal) {
        this.awayScoreTotal = awayScoreTotal;
    }



    @Override
    public String toString() {
        return (homeTeam+", "+awayTeam+", "+week+", "+startTime+", "+homeScoreTotal+", "+awayScoreTotal);
    }
}
