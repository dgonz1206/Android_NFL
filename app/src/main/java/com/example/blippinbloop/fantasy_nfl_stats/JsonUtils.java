package com.example.blippinbloop.fantasy_nfl_stats;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

    private static final String TAG = "JsonUtils";
//    public static ArrayList<Games> parseNews(String jsonResult){
//        ArrayList<Games> newsList = new ArrayList<>();
//        try {
//            JSONObject mainJSONObject = new JSONObject(jsonResult);
//            JSONArray items = mainJSONObject.getJSONArray("articles");
//
//            for(int i = 0; i < items.length(); i++){
//                JSONObject item = items.getJSONObject(i);
//
//                newsList.add(new NewsItem(item.getString("title"),item.getString("description"),
//                        item.getString("url"),item.getString("author"),
//                        item.getString("urlToImage"),item.getString("publishedAt")));
//
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return newsList;
//    }
    public static ArrayList<GameDay> parseGames(String jsonResult){
        ArrayList<String> games = new ArrayList<>();
        ArrayList<GameDay> GameDayList = new ArrayList<>();

        try{
//            JSONObject mainJSONObject = new JSONObject(jsonResult);
//            JSONObject gamesObject = mainJSONObject.getJSONObject("games");
//            JSONArray game = mainJSONObject.getJSONArray("games");
//
//            //JSONObject scheduleObject = gamesObject.getJSONObject("schedule");
//            JSONArray scheduleArray = gamesObject.getJSONArray("schedule");
//            JSONArray scoreArray = gamesObject.getJSONArray("score");

            JSONObject mjo = new JSONObject(jsonResult);
            JSONArray ito = mjo.getJSONArray("games");
            for(int i = 0; i < ito.length(); i++){
                JSONObject item = ito.getJSONObject(i);
                JSONObject schedule = item.getJSONObject("schedule");
                JSONObject score = item.getJSONObject("score");
                GameDay gd = new GameDay(schedule.getJSONObject("homeTeam").getString("abbreviation"),
                        schedule.getJSONObject("awayTeam").getString("abbreviation"),
                        schedule.getInt("week"),
                        schedule.getString("startTime"),
                        score.getInt("homeScoreTotal"),
                        score.getInt("awayScoreTotal"));
                //JSONObject schedule = item.getJSONObject("Schedule");
                //JSONObject score = item.getJSONObject("Score");
                GameDayList.add(gd);

            }
//            for(int i = 0; i < game.length(); i++){
//                JSONObject item = game.getJSONObject(i);
//                JSONObject schedule = item.getJSONObject("Schedule");
//                JSONObject score = item.getJSONObject("Score");
//                //games.add(schedule.getString("id"));
//                //games.add(score.getString("homeScoreTotal"));
//                //games.add(item.toString());
////                GameDayList.add(new Gameday(item.getString("title"),item.getString("description"),
////                        item.getString("url"),item.getString("author"),
////                        item.getString("urlToImage"),item.getString("publishedAt")));
//
//            }
//            for(int i = 0; i < game.length(); i++){
//                JSONObject schedule = scheduleArray.getJSONObject(i);
//
//            }
//            for(int i = 0; i < scoreArray.length(); i++){
//                JSONObject score = scoreArray.getJSONObject(i);
//            }

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
