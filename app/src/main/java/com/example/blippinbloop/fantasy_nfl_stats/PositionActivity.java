package com.example.blippinbloop.fantasy_nfl_stats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PositionActivity extends AppCompatActivity {

    private Button qbBtn;
    private Button rbBtn;
    private Button wrBtn;
    private Button teBtn;
    private Button kBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        qbBtn = (Button) findViewById(R.id.qb_btn);
        rbBtn = (Button) findViewById(R.id.rb_btn);
        wrBtn = (Button) findViewById(R.id.wr_btn);
        teBtn = (Button) findViewById(R.id.te_btn);
        kBtn = (Button) findViewById(R.id.k_btn);

        qbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PositionActivity.this, PlayerActivity.class);
                intent.putExtra("position", "qb");
                startActivity(intent);
            }
        });
        rbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PositionActivity.this, PlayerActivity.class);
                intent.putExtra("position", "rb");
                startActivity(intent);
            }
        });
        wrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PositionActivity.this, PlayerActivity.class);
                intent.putExtra("position", "wr");
                startActivity(intent);
            }
        });
        teBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PositionActivity.this, PlayerActivity.class);
                intent.putExtra("position", "te");
                startActivity(intent);
            }
        });
        kBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PositionActivity.this, PlayerActivity.class);
                intent.putExtra("position", "k");
                startActivity(intent);
            }
        });
    }

}
