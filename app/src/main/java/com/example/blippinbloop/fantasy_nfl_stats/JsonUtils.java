package com.example.blippinbloop.fantasy_nfl_stats;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static ArrayList<GameDay> parseGames(String jsonResult){
        ArrayList<GameDay> GameDayList = new ArrayList<>();

        try{

            JSONObject mjo = new JSONObject(jsonResult);
            JSONArray ito = mjo.getJSONArray("games");
            for(int i = 0; i < ito.length(); i++){
                JSONObject item = ito.getJSONObject(i);
                JSONObject schedule = item.getJSONObject("schedule");
                JSONObject score = item.getJSONObject("score");
                String homeTemp ="TBA";
                String awayTemp = "TBA";
                if(!score.getString("homeScoreTotal").equals("null"))
                    homeTemp = score.getString("homeScoreTotal");
                if(!score.getString("awayScoreTotal").equals("null"))
                    awayTemp = score.getString("awayScoreTotal");

                GameDay gd = new GameDay(schedule.getJSONObject("homeTeam").getString("abbreviation"),
                        schedule.getJSONObject("awayTeam").getString("abbreviation"),
                        schedule.getInt("week"),
                        schedule.getString("startTime"),
                        homeTemp,
                        awayTemp);

                GameDayList.add(gd);

            }


        }catch (JSONException e) {
            e.printStackTrace();
        }

        return GameDayList;
    }

    public static ArrayList<Player> parsePlayers(String jsonResult){
        ArrayList<Player> playerList = new ArrayList<>();
        try{
            JSONObject mainJSONObject = new JSONObject(jsonResult);
            JSONArray players = mainJSONObject.getJSONArray("players");

            Log.d(TAG, "parsePlayers: " + players);
            for(int i = 0; i < players.length(); i++){
                JSONObject playerInfo = players.getJSONObject(i);
                JSONObject player = playerInfo.getJSONObject("player");

                if(player.isNull("currentTeam")){
                    continue;
                }

                Log.d(TAG, "parsePlayers: " + playerInfo);
                Log.d(TAG, "parsePlayers: " + player.getString("firstName"));
                JSONObject jsonTeam = player.getJSONObject("currentTeam");

                Log.d(TAG, "parsePlayers: TEAM: " + jsonTeam);

                String fname = player.getString("firstName");
                String lname = player.getString("lastName");
                String pos = player.getString("primaryPosition");
                String team = jsonTeam.getString("abbreviation");

                Player newPlayer = new Player(team, fname, lname, pos);

                Log.d(TAG, "parsePlayers: " + fname + lname + pos + team);

                playerList.add(newPlayer);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return playerList;
    }

}
