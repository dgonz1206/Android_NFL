package com.example.blippinbloop.fantasy_nfl_stats;

import android.app.Activity;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.common.util.Strings;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class FragmentGames extends Fragment implements AdapterView.OnItemSelectedListener {
    View v;

    public FragmentGames(){

    }
    private RecyclerView mRecyclerView;
    private GameRecyclerViewAdapter mAdapter;
    private ArrayList<GameDay> games = new ArrayList<>();
    private String weeks[]  = new String[17];

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
        populateList(weeks);
        Spinner dropdown = v.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, weeks);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        return v;
    }

    public void populateList(String weeks[]){
        for(int x =0;x<17;x++){
            weeks[x] = "Week "+(x+1);
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        queryTask task = new queryTask();
        task.execute(NetworkCall.buildURL(pos+1));
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

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
                searchResults = NetworkCall.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();

            }
            return  searchResults;
        }

        @Override
        protected void onPostExecute(String searchResults) {

            if (searchResults != null && !searchResults.equals("")) {
                mAdapter.mGameItems.clear();

                ArrayList<GameDay> game = JsonUtils.parseGames(searchResults);
                mAdapter.mGameItems.addAll(game);
                mAdapter.notifyDataSetChanged();


            }
        }
    }

}
