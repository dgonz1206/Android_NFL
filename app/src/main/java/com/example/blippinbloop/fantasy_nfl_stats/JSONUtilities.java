package com.example.blippinbloop.fantasy_nfl_stats;

import android.util.Log;


import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONUtilities {
    //public static ArrayList<FantasyPlayer> fantasy_players = new ArrayList<>();
    public static FantasyPlayer parsePlayer(String jsonResults){

        FantasyPlayer player = new FantasyPlayer();

        try{
            JSONObject mainJSONObject = new JSONObject(jsonResults);
            JSONObject references = mainJSONObject.getJSONObject("references");
            if (references != null ) {

                JSONArray items = mainJSONObject.getJSONArray("gamelogs");

                JSONObject player_info = items.getJSONObject(0);
                JSONObject game = player_info.getJSONObject("game");
                JSONObject players = player_info.getJSONObject("player");

                JSONObject stats = player_info.getJSONObject("stats");
                JSONObject team = player_info.getJSONObject("team");


                JSONObject passing = stats.getJSONObject("passing");
                JSONObject rushing = stats.getJSONObject("rushing");
                JSONObject receiving = stats.getJSONObject("receiving");
                JSONObject kickoffreturns = stats.getJSONObject("kickoffReturns");
                JSONObject puntReturns = stats.getJSONObject("puntReturns");
                JSONObject twopointAttempts = stats.getJSONObject("twoPointAttempts");

                //set player info
                player.setFname(players.getString("firstName"));
                player.setLname(players.getString("lastName"));
                player.setAbb(team.getString("abbreviation"));

                //set player special teams' stats
                //player.setPRTDS(puntReturns.getInt("prTD"));
                //player.setKoffTDS(kickoffreturns.getInt("krTD"));
                //player.setTwoptmade(twopointAttempts.getInt("twoPTMade"));

                //set passing stats
                player.setPassTDS(passing.getInt("passTD"));
                player.setPassYDS(passing.getInt("passYards"));
                player.setInts(passing.getInt("passInt"));

                //set rushing stats
                player.setRushTDS(rushing.getInt("rushTD"));
                player.setRushYDS(rushing.getInt("rushYards"));
                player.setRushFunb(rushing.getInt("rushFumbles"));

                //set receiving yds
                player.setRecTDS(receiving.getInt("recTD"));
                player.setRecYDS(receiving.getInt("recYards"));
                player.setRecs(receiving.getInt("receptions"));
            }
            else{
                return null;
            }
            //
        }catch (JSONException e){
            e.printStackTrace();
        }

        return player;
    }
}




