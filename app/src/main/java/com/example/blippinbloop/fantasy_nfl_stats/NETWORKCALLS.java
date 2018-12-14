package com.example.blippinbloop.fantasy_nfl_stats;


import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.*;

public class NETWORKCALLS {

    final static  String News_Api_Url_part1 = "https://api.mysportsfeeds.com/v2.0/pull/nfl/current/week/";
    final static String News_Api_url_part2 = "/player_gamelogs.json";
    private static String Player = "";
    final static String PARAM_Player = "player";

    final static String limit ="1";
    final static String PARAM_Limit = "limit";

    final static String key = "24f2e9c8-7320-4657-b04e-807e4d";
    final static String pw ="MYSPORTSFEEDS";



    public static URL buildURL(String name){

        Date d1 = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        int week = c1.getWeekYear();
        week -= 35;

        String weeks = String.valueOf(week);


        Player = name;
        Uri builtUri = Uri.parse(News_Api_Url_part1 + "15" + News_Api_url_part2).buildUpon()
                .appendQueryParameter(PARAM_Player, Player)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(String name) throws IOException {

        try {
            URL url = buildURL(name);
            //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            String originalInput = "24f2e9c8-7320-4657-b04e-807e4d:MYSPORTSFEEDS";
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

            //String to_encode = key + ":" + pw;
            //String encoded = null;


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setRequestProperty("Authorization", "Basic " + encodedString);

            InputStream content = (InputStream)connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            String line;
            line = in.readLine();
            return line;

        }catch(Exception e) {
            e.printStackTrace();
        }
        //return  url.toString();/*
        /*try{
            InputStream in = (InputStream) urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput){
                return scanner.next();
            }
            else{
                return null;
            }
        }
        finally {
            urlConnection.disconnect();
        }
    }*/
        return null;
    }
}
