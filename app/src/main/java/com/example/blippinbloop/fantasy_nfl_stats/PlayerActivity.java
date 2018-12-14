package com.example.blippinbloop.fantasy_nfl_stats;

import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PlayersRecyclerViewAdapter mAdapter;
    private ArrayList<Player> players = new ArrayList<>();
    private String position;

    private static final String TAG = "PlayerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            position = extras.getString("position");
        }

        mRecyclerView = (RecyclerView)findViewById(R.id.players_recyclerview);
        mAdapter = new PlayersRecyclerViewAdapter(PlayerActivity.this,this, players);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        PlayerActivity.queryTask task = new PlayerActivity.queryTask();
        task.execute(NetworkCall.buildPlayerURL(position));


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
                searchResults = NetworkCall.getResponseFromHttpUrlPlayer(position);
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
