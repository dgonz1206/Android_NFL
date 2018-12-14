package com.example.blippinbloop.fantasy_nfl_stats;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PositionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);
    }

    class queryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL searchUrl = urls[0];
            String searchResults = null;
            Log.d("myprint5", urls[0].toString());

            Log.d("myprint5", searchUrl.toString());

            try {
                searchResults = NetworkCall.getResponseFromHttpUrlPlayer();
            } catch (IOException e) {
                e.printStackTrace();
                //Log.d("myprintback", e.toString());

            }
            return searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {

            if (searchResults != null && !searchResults.equals("")) {

                ArrayList<Player> players = JsonUtils.parsePlayers(searchResults);

                Log.d("myprint4", players.size() + "");
                mAdapter.mPlayers.addAll(players);
                mAdapter.notifyDataSetChanged();

            }
        }
    }
}
