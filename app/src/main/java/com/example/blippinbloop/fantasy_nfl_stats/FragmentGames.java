package com.example.blippinbloop.fantasy_nfl_stats;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentGames extends Fragment {
    View v;

    public FragmentGames(){

    }
    private RecyclerView mRecyclerView;
    private GameRecyclerViewAdapter mAdapter;
    private ArrayList<GameDay> games = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.game_fragment,container,false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.game_recyclerview);
        mAdapter = new GameRecyclerViewAdapter(getContext(), games);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        queryTask task = new queryTask();
        task.execute(NetworkCall.buildURL());
        return v;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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


            try {
                searchResults = NetworkCall.getResponseFromHttpUrl();
            } catch (IOException e) {
                e.printStackTrace();

            }
            return  searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {

            if (searchResults != null && !searchResults.equals("")) {

                ArrayList<GameDay> game = JsonUtils.parseGames(searchResults);
                mAdapter.mGameItems.addAll(game);
                mAdapter.notifyDataSetChanged();


            }
        }
    }

}
