package com.example.blippinbloop.fantasy_nfl_stats.yelpdata;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class YelpJsonUtils {

    public static List<YelpLocation> parseLocations (String jsonResult){
        List<YelpLocation> yelpLoc = new ArrayList<>();
        try {
            JSONObject mainJSONObject = new JSONObject(jsonResult);
            JSONArray items = mainJSONObject.getJSONArray("businesses");

            for(int i = 0; i < items.length(); i++){
                JSONObject item = items.getJSONObject(i);

                yelpLoc.add(new YelpLocation(item.getString("name"),
                        item.getString("image_url"),
                        item.getString("url"),
                        item.getString("phone")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return yelpLoc;
    }
}
