package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;

import java.util.ArrayList;
import java.util.List;

public class PlayersRecyclerViewAdapter extends RecyclerView.Adapter<PlayersRecyclerViewAdapter.PlayersHolder> {

    Context mContext;
    List<Player> mPlayers;
    String addedPlayer;
    PlayerActivity mActivity;

    private static final String TAG = "PlayersRecyclerViewAdap";

    public PlayersRecyclerViewAdapter(PlayerActivity activity, Context context, List<Player> players){
        this.mContext = context;
        this.mPlayers = players;
        this.mActivity = activity;
    }

    @NonNull
    @Override
    public PlayersRecyclerViewAdapter.PlayersHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.player_list, parent,false);
        PlayersHolder playerHolder = new PlayersHolder(view);

        return playerHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersRecyclerViewAdapter.PlayersHolder playersHolder, int i) {
        playersHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return mPlayers.size();
    }
    public class PlayersHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView position;
        TextView team;

        public PlayersHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.name);
            position = (TextView) itemView.findViewById(R.id.position);
            team = (TextView) itemView.findViewById(R.id.team);

            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            final String NAME = mPlayers.get(listIndex).getFirstName() + " " + mPlayers.get(listIndex).getLastName();
            final String POS = mPlayers.get(listIndex).getPos();
            final String TEAM = mPlayers.get(listIndex).getTeam();

            name.setText(NAME);
            position.setText(POS);
            team.setText(TEAM);

        }

        @Override
        public void onClick(View view) {
//            Log.d(TAG, "onClick: " + mPlayers.get(getAdapterPosition()).getFirstName());
            addedPlayer = mPlayers.get(getAdapterPosition()).getFirstName() + "-" + mPlayers.get(getAdapterPosition()).getLastName();

            Intent intent = new Intent(mContext, MainActivity.class);
            intent.putExtra("player_name", addedPlayer);
            mContext.startActivity(intent);

        }
    }
}