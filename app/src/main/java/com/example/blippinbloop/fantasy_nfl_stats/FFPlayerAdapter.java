package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.blippinbloop.fantasy_nfl_stats.DBStuff.FantasyPlayer;

import java.util.ArrayList;
import java.util.List;

public class FFPlayerAdapter extends RecyclerView.Adapter<FFPlayerAdapter.FFPViewHolder> {

    Context context;
    List<FantasyPlayer> mFFPlayers;
    //worry about onclick later

    public FFPlayerAdapter(Context context, ArrayList<FantasyPlayer> players/*onclick/listener*/){
        this.context = context;
        this.mFFPlayers = players;
    }

    @NonNull
    @Override
    public FFPlayerAdapter.FFPViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.fantasy_player, viewGroup, shouldAttachToParentImmediately);
        FFPViewHolder viewHolder = new FFPViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FFPlayerAdapter.FFPViewHolder ffpViewHolder, int i) {
        ffpViewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        if (mFFPlayers == null) return 0;
        return mFFPlayers.size();
    }

    public class FFPViewHolder extends RecyclerView.ViewHolder/*implements view.onclick*/{

        TextView fname;
        TextView lname;
        TextView passing;
        TextView rushing;
        TextView receiving;
        TextView fpts;

        public FFPViewHolder(View itemView){
            super(itemView);
            fname = itemView.findViewById(R.id.fname);
            lname = itemView.findViewById(R.id.lname);
            passing = itemView.findViewById(R.id.passing);
            rushing = itemView.findViewById(R.id.rushing);
            receiving = itemView.findViewById(R.id.receiving);
            fpts = itemView.findViewById(R.id.fantasy_points);
            //item.viewsetonclick
        }

        void bind(final int index){

            String pass = "Pass YDS:" + mFFPlayers.get(index).getPassYDS() + "  Pass TDS:" + mFFPlayers.get(index).getPassTDS();
            String rush = "Rush YDS:" + mFFPlayers.get(index).getRushYDS() + "  Rush TDS:" + mFFPlayers.get(index).getRushTDS();
            String rec = "Pass YDS:" + mFFPlayers.get(index).getRecYDS()+ "  Rec TDS:" + mFFPlayers.get(index).getRecTDS();
            Double ftps = mFFPlayers.get(index).FTPS();
            String fptss = "FTPS for this past week:" + ftps.toString();

            fname.setText(mFFPlayers.get(index).getFname());
            lname.setText(mFFPlayers.get(index).getLname());
            passing.setText(pass);
            rushing.setText(rush);
            receiving.setText(rec);
            fpts.setText(fptss);
        }

        //override onclick
    }
}