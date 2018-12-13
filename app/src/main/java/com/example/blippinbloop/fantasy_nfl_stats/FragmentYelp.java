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

import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpLocation;
import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpViewAdapter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FragmentYelp extends Fragment {

    View v;
    private RecyclerView mRecycler;
    private List<YelpLocation> mLoc = new ArrayList<>();
    public static String url = "https://api.yelp.com/v3/businesses/search?term=sports-bars&latitude=37.786882&longitude=-122.399972";

    public FragmentYelp(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.yelp_fragment, container, false);

        mRecycler = (RecyclerView) v.findViewById(R.id.yelp_recycler);
        YelpViewAdapter mAdapter = new YelpViewAdapter(getContext(), mLoc);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoc.add(new YelpLocation("SPORTS BAR",
                "https://s3-media1.fl.yelpcdn.com/bphoto/3XQZSEnkjwTXNX_L3YgRmg/o.jpg",
                "https://www.yelp.com/biz/the-lark-bar-san-francisco?adjust_creative=WOlfaz4vzUNvNxCScXeYrg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=WOlfaz4vzUNvNxCScXeYrg",
                "(415) 952-7504"));
        mLoc.add(new YelpLocation("SPORTS BAR",
                "https://s3-media1.fl.yelpcdn.com/bphoto/3XQZSEnkjwTXNX_L3YgRmg/o.jpg",
                "https://www.yelp.com/biz/the-lark-bar-san-francisco?adjust_creative=WOlfaz4vzUNvNxCScXeYrg&utm_campaign=yelp_api_v3&utm_medium=api_v3_business_search&utm_source=WOlfaz4vzUNvNxCScXeYrg",
                "(415) 952-7504"));
        /*
        okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute(url);
        */
    }


    public static class OkHttpHandler extends AsyncTask<String, String, String> {

        /*if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions( this, new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    LocationService.MY_PERMISSION_ACCESS_COURSE_LOCATION );
        }*/

        OkHttpClient client = new OkHttpClient();
        /*
        String locationProvider = LocationManager.NETWORK_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);

        double latVal = lastKnownLocation.getLatitude();
        double longVal = lastKnownLocation.getLongitude();
        */


        @Override
        protected String doInBackground(String...params) {
            Request request = new Request.Builder()
                    .url(params[0])
                    .get()
                    .addHeader("Authorization", "Bearer qE1GuxNEzPGR5DOud5urAH32BhOtKsqIDroDPMpPDFuHo-5zH523tpoArwzE2gyEVpFbRj_GZrHx04kulPIwjDj1eO2bNqQWhFFh7453ztfyYRt4Gu8_Uzm2vNURXHYx")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Postman-Token", "e9e5808d-953c-42df-b6af-3564facbc15f")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.d("ONPOST EXECUTE STR:", s);
        }
    }
}

