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

public class NetworkCall {

    final static  String Players_Api_Url = "https://api.mysportsfeeds.com/v2.0/pull/nfl/players.json?rosterstatus=assigned-to-roster,assigned-to-injury-list";


    final static  String News_Api_Url = "https://api.mysportsfeeds.com/v2.0/pull/nfl/2018-regular/week/";


    final static String Player = "todd-gurley";
    final static String PARAM_Player = "player";

    final static String limit ="1";
    final static String PARAM_Limit = "limit";

    final static String key = "24f2e9c8-7320-4657-b04e-807e4d";
    final static String pw ="MYSPORTSFEEDS";



    public static URL buildURL(int week){
        Uri builtUri = Uri.parse(News_Api_Url+week+"/games.json").buildUpon()
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {

        try {
            String originalInput = "24f2e9c8-7320-4657-b04e-807e4d:MYSPORTSFEEDS";
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
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
        return null;
    }
    public static URL buildPlayerURL(String pos){
        Uri builtUri = Uri.parse(Players_Api_Url + "&position=" + pos).buildUpon()
                //.appendQueryParameter(PARAM_Player, Player)
                .build();

        URL url = null;
        try{
            url = new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponseFromHttpUrlPlayer(String pos) throws IOException {

        try {
            URL url = buildPlayerURL(pos);
            //HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            String originalInput = "24f2e9c8-7320-4657-b04e-807e4d:MYSPORTSFEEDS";
            String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());

            //String to_encode = key + ":" + pw;
            //String encoded = null;


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(false);
            connection.setRequestProperty("Authorization", "Basic " + encodedString);

            InputStream content = (InputStream) connection.getInputStream();
            BufferedReader in =
                    new BufferedReader(new InputStreamReader(content));
            String line;
            line = in.readLine();
            return line;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

