//package com.example.blippinbloop.fantasy_nfl_stats;
//
//import android.net.Uri;
//import android.util.Log;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.net.HttpURLConnection;
//import java.net.MalformedURLException;
//import java.net.URI;
//import java.net.URL;
//import java.util.Base64;
//import java.util.Scanner;
//
//import javax.net.ssl.HttpsURLConnection;
//
//public class NetworkUtils {
//    final static String BASE_URL =
//            "api.mysportsfeeds.com/v2.0/pull/nfl/2018-regular/week/15/games.json";
//
//    final static String username = "24f2e9c8-7320-4657-b04e-807e4d";
//    final static String pw = "MYSPORTSFEEDS";
//    final static String cre = "https://24f2e9c8-7320-4657-b04e-807e4d:MYSPORTSFEEDS@";
//
//    public static URL buildUrl() {
//
//        Uri builtUri = Uri.parse(cre+BASE_URL);
//        URL url = null;
//        try {
//            url = new URL(builtUri.toString());
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }
//
//
//        return url;
//    }
//
//
//    public static String getResponseFromHttpUrl(URL url) throws IOException {
//        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//        try {
//            InputStream in = urlConnection.getInputStream();
//
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//
//            boolean hasInput = scanner.hasNext();
//            if (hasInput) {
//                return scanner.next();
//            } else {
//                return null;
//            }
//        } finally {
//            urlConnection.disconnect();
//        }
//    }
//
//}