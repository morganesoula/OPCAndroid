package com.example.topquiz.controller;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.topquiz.R;
import com.example.topquiz.adapter.HistoricTableAdapter;

import java.util.HashMap;
import java.util.List;

public class HistoricActivity extends AppCompatActivity {

    private Button mBackButton;
    private ListView mListView;

    private HashMap<String, List<Integer>> tabScores;

    private String player;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mBackButton = (Button) findViewById(R.id.activity_historic_back_button);
        mListView = (ListView) findViewById(R.id.activity_historic_table_listview);

        // player = getIntent().getStringExtra(MainActivity.PLAYER_NAME_SAVED);
        // score = getIntent().getIntExtra(MainActivity.SCORE_SAVED, 0);

        tabScores = (HashMap<String, List<Integer>>) getIntent().getSerializableExtra(MainActivity.TREE_MAP_SAVED);

        HistoricTableAdapter adapter = new HistoricTableAdapter(this, tabScores);
        mListView.setAdapter(adapter);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



}


