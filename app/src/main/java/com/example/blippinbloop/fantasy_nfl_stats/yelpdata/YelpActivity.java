package com.example.blippinbloop.fantasy_nfl_stats.yelpdata;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.example.blippinbloop.fantasy_nfl_stats.R;

public class YelpActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_main);

        Log.d("URL", "did it enter");
        webView = (WebView)findViewById(R.id.webview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        String urlString = "";
        if(intent.hasExtra("urlString")){
            urlString = intent.getStringExtra("urlString");
            Log.d("mycode", urlString);
        }
        webView.loadUrl(urlString);
    }
}
