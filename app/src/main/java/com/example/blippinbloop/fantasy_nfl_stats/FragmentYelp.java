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

import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpJsonUtils;
import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpLocation;
import com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpViewAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.example.blippinbloop.fantasy_nfl_stats.yelpdata.YelpJsonUtils.parseLocations;

public class FragmentYelp extends Fragment {

    private final static String TAG = "YELP_FRAGMENT";
    View v;
    private RecyclerView mRecycler;
    private static List<YelpLocation> mLoc = new ArrayList<>();
    private static YelpViewAdapter mAdapter;
    private static String results = "";
    public static String url = "https://api.yelp.com/v3/businesses/search?term=sports-bars&latitude=34.70668&longitude=-118.1671";

    public FragmentYelp(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.yelp_fragment, container, false);

        mRecycler = (RecyclerView) v.findViewById(R.id.yelp_recycler);
        mAdapter = new YelpViewAdapter(getContext(), mLoc);
        mRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecycler.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute();

    }

    public static class OkHttpHandler extends AsyncTask<Void, Void, Void> {

        OkHttpClient client = new OkHttpClient();

        @Override
        protected Void doInBackground(Void... params) {
            Log.d(TAG, "Enter background execute...");
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .addHeader("Authorization", "Bearer qE1GuxNEzPGR5DOud5urAH32BhOtKsqIDroDPMpPDFuHo-5zH523tpoArwzE2gyEVpFbRj_GZrHx04kulPIwjDj1eO2bNqQWhFFh7453ztfyYRt4Gu8_Uzm2vNURXHYx")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("Postman-Token", "e9e5808d-953c-42df-b6af-3564facbc15f")
                    .build();
            try {
                Log.d(TAG, "Calling for response Object...");
                Response response = client.newCall(request).execute();
                ResponseBody responseBodyCopy = response.peekBody(Long.MAX_VALUE);
                results = responseBodyCopy.string();
                Log.d("TAG", results);
                mLoc = parseLocations(results);
            } catch (Exception e) {
                Log.d(TAG, e.toString());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Log.d(TAG, "Enter Post Execute...");

            super.onPostExecute(aVoid);
            mAdapter.setLoc(mLoc);
        }
    }
}

