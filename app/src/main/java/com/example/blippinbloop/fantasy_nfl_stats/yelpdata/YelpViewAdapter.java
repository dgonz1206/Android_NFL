package com.example.blippinbloop.fantasy_nfl_stats.yelpdata;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blippinbloop.fantasy_nfl_stats.R;
import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class YelpViewAdapter extends RecyclerView.Adapter <YelpViewAdapter.YelpViewHolder> {

    List<YelpLocation> mLoc;
    Context mContext;

    public YelpViewAdapter(Context context, List<YelpLocation> locations){
        this.mContext = context;
        this.mLoc = locations;
    }


    @Override
    public YelpViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.yelp_locations, viewGroup, shouldAttachToParentImmediately);
        YelpViewHolder viewHolder = new YelpViewHolder(view);

        return viewHolder;
    }

    void setLoc(List<YelpLocation> locations){
        mLoc = locations;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(YelpViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mLoc.size();
    }

    class YelpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        TextView address;
        TextView phone;
        ImageView img;

        public YelpViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            address = itemView.findViewById(R.id.address);
            phone = itemView.findViewById(R.id.phone);
            img = itemView.findViewById(R.id.img);
        }

        void bind(int listIndex) {
            String thumbUrl = mLoc.get(listIndex).getImage_url();
            if(thumbUrl != null){
                Picasso.get().load(thumbUrl).into(img);
            }
            title.setText(mLoc.get(listIndex).getName());
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            String urlString = mLoc.get(getAdapterPosition()).getUrl();
            Intent intent = new Intent(mContext, YelpActivity.class);
            intent.putExtra("urlString", urlString);
            mContext.startActivity(intent);
            }
        }
}
