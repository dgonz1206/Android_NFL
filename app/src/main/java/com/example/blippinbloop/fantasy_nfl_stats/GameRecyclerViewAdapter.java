package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class GameRecyclerViewAdapter  extends RecyclerView.Adapter<GameRecyclerViewAdapter.GameViewHolder> {

    Context mContext;
    ArrayList<GameDay> mGameItems;

    public GameRecyclerViewAdapter(Context context, ArrayList<GameDay> gameItem){
        this.mContext = context;
        this.mGameItems = gameItem;
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
        return mGameItems.size();
    }

    public class GameViewHolder extends RecyclerView.ViewHolder{
        TextView homeTeam;
        TextView awayTeam;
        TextView week;
        TextView startTime;
        TextView homeScoreTotal;
        TextView awayScoreTotal;
        ImageView img;

        public GameViewHolder(View itemView) {
            super(itemView);
            startTime = (TextView) itemView.findViewById(R.id.textView1);
            homeTeam = (TextView) itemView.findViewById(R.id.textView2);
            awayTeam = (TextView) itemView.findViewById(R.id.textView3);
            homeScoreTotal = (TextView) itemView.findViewById(R.id.score1);
            awayScoreTotal = (TextView) itemView.findViewById(R.id.score2);
//            url = (TextView) itemView.findViewById(R.id.url);
//            author = (TextView) itemView.findViewById(R.id.author);
            img = (ImageView) itemView.findViewById(R.id.imageView1);
        }

        void bind(final int listIndex) {

            startTime.setText(String.format(mGameItems.get(listIndex).getStartTime()));
            homeTeam.setText(String.format(mGameItems.get(listIndex).getHomeTeam()));
            awayTeam.setText(String.format(mGameItems.get(listIndex).getAwayTeam()));
            homeScoreTotal.setText(String.format(String.valueOf(mGameItems.get(listIndex).getHomeScoreTotal())));
            awayScoreTotal.setText(String.format(String.valueOf(mGameItems.get(listIndex).getAwayScoreTotal())));
//            url.setText(String.format("URL: %s", mNewsItems.get(listIndex).getImg()));
//            author.setText(String.format("Author: %s", mNewsItems.get(listIndex).getAuthor()));
//            img.setText(String.format("Image: %s", mNewsItems.get(listIndex).getUrl()));
            //img.setImageResource(R.drawable.cardinals);

            Picasso.get()
                    .load(R.drawable.cardinals)
                    .into(img);

        }

    }

}
