package com.example.blippinbloop.fantasy_nfl_stats;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {

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

}
