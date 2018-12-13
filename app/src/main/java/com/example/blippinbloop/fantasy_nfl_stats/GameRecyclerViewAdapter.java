package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class GameRecyclerViewAdapter  extends RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder> {

    Context mContext;
    ArrayList<GameDay> mNewsItems;

    public GameRecyclerViewAdapter(Context context, ArrayList<GameDay> newItems){
        this.mContext = context;
        this.mNewsItems = newItems;
    }

    @Override
    public GameRecyclerViewAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.game_item, parent, false);
        GameViewHolder viewHolder = new GameViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GameRecyclerViewAdapter.GameViewHolder holder, int position) {
        holder.bind(position);

    }



    @Override
    public int getItemCount() {
        return mNewsItems.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder{
        TextView homeTeam;
        TextView awayTeam;
        TextView week;
        TextView startTime;
        TextView homeScoreTotal;
        TextView awayScoreTotal;

        public GameViewHolder(View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.textView1);
            homeTeam = (TextView) itemView.findViewById(R.id.textView2);
            awayTeam = (TextView) itemView.findViewById(R.id.textView3);
//            url = (TextView) itemView.findViewById(R.id.url);
//            author = (TextView) itemView.findViewById(R.id.author);
//            img = (TextView) itemView.findViewById(R.id.img);
        }

        void bind(final int listIndex) {

            startTime.setText(String.format(mNewsItems.get(listIndex).getStartTime()));
            homeTeam.setText(String.format(mNewsItems.get(listIndex).getHomeTeam()));
            awayTeam.setText(String.format(mNewsItems.get(listIndex).getAwayTeam()));
//            url.setText(String.format("URL: %s", mNewsItems.get(listIndex).getImg()));
//            author.setText(String.format("Author: %s", mNewsItems.get(listIndex).getAuthor()));
//            img.setText(String.format("Image: %s", mNewsItems.get(listIndex).getUrl()));

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    String urlString = mNewsItems.get(listIndex).getUrl();
//                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNewsItems.get(listIndex).getUrl()));
//                    intent.putExtra("urlString", urlString);
//                    mContext.startActivity(intent);
//
//                }
//            });
        }

    }

}
