package com.example.blippinbloop.fantasy_nfl_stats;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
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
import android.widget.Button;

import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FragmentFantasy extends Fragment implements View.OnClickListener {
    View v;

    public FragmentFantasy(){

    }

    private FantasyPlayerViewModel fantasyPlayerViewModel;
    private FFPlayerAdapter mFFPAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<FantasyPlayer> ffp = new ArrayList<>();
    String extras = "";

    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        v = inflater.inflate(R.layout.fantasy_fragment,container,false);

        Button gurleyButton = (Button) v.findViewById(R.id.api_call);
        gurleyButton.setOnClickListener(this);


        //activity.


        //Log.d("!!!!!!!!!!!!!!!!!!", "?????????????????????????????????????????????????");



        mRecyclerView = (RecyclerView) v.findViewById(R.id.ff_players);
        //LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mFFPAdapter = new FFPlayerAdapter(getContext(), ffp);
        mRecyclerView.setAdapter(mFFPAdapter);

        /*mRecyclerView = (RecyclerView)v.findViewById(R.id.game_recyclerview);
        mAdapter = new GameRecyclerViewAdapter(getContext(), games);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        queryTask task = new queryTask();*/

        fantasyPlayerViewModel = ViewModelProviders.of(this).get(FantasyPlayerViewModel.class);

        MainActivity activity = (MainActivity)getActivity();

        if (activity != null) {
            Bundle results = activity.getMyPlayer();
            if (results.getString("player") != null) {
                String player_insert = results.getString("player");
                //Log.d("!!!!!!!!!!!!!!!!!!?????????????????????????????????????????????????", player_insert);
                insertSelectedplayer(player_insert);
            }
        }
        fantasyPlayerViewModel.getFantasyplayers().observe(this, new Observer<List<FantasyPlayer>>() {
            @Override
            public void onChanged(@Nullable List<FantasyPlayer> fantasyPlayers) {
                //fantasyPlayers = fantasyPlayerViewModel.getFantasyplayers();
                //mFFPAdapter.mFFPlayers.clear();
                int d = fantasyPlayers.size() - 1;
                mFFPAdapter.mFFPlayers.add(fantasyPlayers.get(d));
                mFFPAdapter.notifyDataSetChanged();
            }
        });
        return v;


    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getContext(), PositionActivity.class);
        startActivity(intent);
    }

    public void insertSelectedplayer(String name){
        fantasyPlayerViewModel.insert(name);
    }
}
