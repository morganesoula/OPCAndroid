package com.example.topquiz.controller;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HistoricActivity extends AppCompatActivity {

    private Button mBackButton;
    private ListView mNameListView;
    private ListView mScoreListView;

    private String test;

    private HashMap<String, ArrayList<Integer>> mapScores;
    private ArrayList<String> mListOfNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historic);

        mBackButton = (Button) findViewById(R.id.activity_historic_back_button);
        mNameListView = (ListView) findViewById(R.id.activity_historic_name_listview);
        mScoreListView = (ListView) findViewById(R.id.activity_historic_score_listview);

        Intent intent = getIntent();
        mapScores = (HashMap<String, ArrayList<Integer>>) intent.getSerializableExtra(MainActivity.BUNDLE_TAB_SCORE);

        Set<String> keySet = mapScores.keySet();
        mListOfNames = new ArrayList<>(keySet);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoricActivity.this, android.R.layout.simple_list_item_1, mListOfNames);
        mNameListView.setAdapter(adapter);


        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}


