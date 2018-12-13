package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<GameDay> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;

    /**
     * Holds variables in a View
     */
    private static class ViewHolder {
        TextView homeTeam;
        TextView awayTeam;
        TextView startTime;
    }


    public ListAdapter(Context context, int resource, ArrayList<GameDay> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get the persons information
        String homeTeam = getItem(position).getHomeTeam();
        String awayTeam= getItem(position).getAwayTeam();
        int week= getItem(position).getWeek();
        String startTime= getItem(position).getStartTime();
        int homeScoreTotal= getItem(position).getHomeScoreTotal();
        int awayScoreTotal= getItem(position).getAwayScoreTotal();

        //Create the person object with the information
        GameDay game = new GameDay(homeTeam,awayTeam,week,startTime,homeScoreTotal,awayScoreTotal);

        //create the view result for showing the animation

        //ViewHolder object
        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.startTime = (TextView) convertView.findViewById(R.id.textView1);
            holder.homeTeam = (TextView) convertView.findViewById(R.id.textView2);
            holder.awayTeam = (TextView) convertView.findViewById(R.id.textView3);


            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }




        holder.startTime.setText(game.getStartTime());
        holder.homeTeam.setText(game.homeTeam);
        holder.awayTeam.setText(game.awayTeam);

        return convertView;
    }
}
