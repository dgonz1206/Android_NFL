package com.example.blippinbloop.fantasy_nfl_stats.DBStuff;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Fantasy_Player")
public class FantasyPlayer {


    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Fname;
    private String Lname;
    private String abb;
    private int passYDS;
    private int passTDS;
    private int ints;
    private int rushYDS;
    private int rushTDS;
    private int rushFunb;
    private int recs;
    private int recYDS;
    private int recTDS;
    private int KoffTDS;
    private int PRTDS;
    private int twoptmade;


    public FantasyPlayer(){

    }

    @Ignore
    public FantasyPlayer(String fname, String lname, String abb, int passYDS, int passTDS, int ints,
                         int rushYDS, int rushTDS, int rushFunb, int recs, int recYDS, int recTDS, int koffTDS, int PRTDS, int twoptmade) {
        this.Fname = fname;
        this.Lname = lname;
        this.abb = abb;
        this.passYDS = passYDS;
        this.passTDS = passTDS;
        this.ints = ints;
        this.rushYDS = rushYDS;
        this.rushTDS = rushTDS;
        this.rushFunb = rushFunb;
        this.recs = recs;
        this.recYDS = recYDS;
        this.recTDS = recTDS;
        this.KoffTDS = koffTDS;
        this.PRTDS = PRTDS;
        this.twoptmade = twoptmade;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTwoptmade() {
        return twoptmade;
    }

    public void setTwoptmade(int twoptmade) {
        this.twoptmade = twoptmade;
    }

    public String getFname() {
        return Fname;
    }

    public String getAbb() {
        return abb;
    }

    public int getPassYDS() {
        return passYDS;
    }

    public void setPassYDS(int passYDS) {
        this.passYDS = passYDS;
    }

    public void setAbb(String abb) {

        this.abb = abb;
    }

    public String getLname() {
        return Lname;

    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public void setFname(String fname) {

        Fname = fname;
    }

    public int getPassTDS() {
        return passTDS;
    }

    public void setPassTDS(int passTDS) {
        this.passTDS = passTDS;
    }

    public int getInts() {
        return ints;
    }

    public void setInts(int ints) {
        this.ints = ints;
    }

    public int getRushYDS() {
        return rushYDS;
    }

    public void setRushYDS(int rushYDS) {
        this.rushYDS = rushYDS;
    }

    public int getRushTDS() {
        return rushTDS;
    }

    public int getRushFunb() {
        return rushFunb;
    }

    public void setRushFunb(int rushFunb) {
        this.rushFunb = rushFunb;
    }

    public void setRushTDS(int rushTDS) {
        this.rushTDS = rushTDS;

    }

    public int getRecs() {
        return recs;
    }

    public void setRecs(int recs) {
        this.recs = recs;
    }

    public int getRecYDS() {
        return recYDS;
    }

    public void setRecYDS(int recYDS) {
        this.recYDS = recYDS;
    }

    public int getRecTDS() {
        return recTDS;
    }

    public void setRecTDS(int recTDS) {
        this.recTDS = recTDS;
    }

    public int getKoffTDS() {
        return KoffTDS;
    }

    public void setKoffTDS(int koffTDS) {
        KoffTDS = koffTDS;
    }

    public int getPRTDS() {
        return PRTDS;
    }

    public void setPRTDS(int PRTDS) {
        this.PRTDS = PRTDS;
    }

    public double FTPS(){

        double ttl_pts = 0;
        int td_pts = ((this.KoffTDS + this.recTDS + this.rushTDS + this.PRTDS) * 6) + (this.passTDS * 4);
        int neg_pts = (this.rushFunb * -2) + (this.ints * -1);
        double yd_pts =( (double) (this.rushYDS + this.recYDS) / 10) + ((double)this.passYDS / 25);
        int two_pts = this.twoptmade * 2;

        ttl_pts = td_pts + yd_pts + neg_pts + two_pts;

        return ttl_pts;
    }
}
