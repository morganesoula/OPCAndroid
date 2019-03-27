package com.example.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mHistoricButton;
    private User mUser;

    private String firstname;

    private String playerName;
    private int playerScore;

    private int textLength;
    private int score;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    private SharedPreferences mPreferences;
    private static final String FIRSTNAME_SAVED = "FIRSTNAME_SAVED";
    public static final String PLAYER_NAME_SAVED = "PLAYER_NAME_SAVED";
    public static final String SCORE_SAVED = "SCORE_SAVED";
    public static final String TREE_MAP_SAVED = "TREE_MAP_SAVED";

    public HashMap<String, List<Integer>> savedTabScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUser = new User();
        mPreferences = getPreferences(MODE_PRIVATE);
        savedTabScores = new HashMap<>();

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_text);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_button);
        mHistoricButton = (Button) findViewById(R.id.activity_main_historic_button);

        mPlayButton.setEnabled(false);
        mHistoricButton.setVisibility(View.INVISIBLE);

        mNameInput.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mPlayButton.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firstname = mNameInput.getText().toString();
                mUser.setFirstName(firstname);

                mPreferences.edit().putString(FIRSTNAME_SAVED, mUser.getFirstName()).apply();

                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity, GAME_ACTIVITY_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
            score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            mPreferences.edit().putString(PLAYER_NAME_SAVED, firstname).apply();
            mPreferences.edit().putInt(SCORE_SAVED, score).apply();

            welcomeBack();
        }
    }


    protected void welcomeBack() {

        firstname = mPreferences.getString(FIRSTNAME_SAVED, null);

        if (firstname != null) {
            mGreetingText.setText("Welcome back " + firstname +"! \nYour last score recorded is " + score +". \nWill you do better this time?");
            mNameInput.setText(firstname);
            textLength = mNameInput.getText().length();
            mNameInput.setSelection(textLength, textLength);
            mHistoricButton.setVisibility(View.VISIBLE);

            getHistoric();
        } else {
            mNameInput.setText(mUser.getFirstName());
        }

    }


    protected void getHistoric() {

        playerName = mPreferences.getString(PLAYER_NAME_SAVED, null);
        playerScore = mPreferences.getInt(SCORE_SAVED, 0);

        if (!savedTabScores.containsKey(playerName)) {
            savedTabScores.put(playerName, new ArrayList<Integer>(Arrays.asList(playerScore)));
        } else {
            savedTabScores.get(playerName).add(playerScore);
        }

        if (playerName != null) {
            mHistoricButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent historicActivity = new Intent(MainActivity.this, HistoricActivity.class);

                    historicActivity.putExtra(TREE_MAP_SAVED, (Serializable) savedTabScores);
                    historicActivity.putExtra(PLAYER_NAME_SAVED, playerName);
                    historicActivity.putExtra(SCORE_SAVED, playerScore);

                    startActivity(historicActivity);
                }
            });

        }

    }




}
