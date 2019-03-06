package com.example.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.User;

public class MainActivity extends AppCompatActivity {

    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mHistoricButton;
    private User mUser;

    private String firstname;

    private int textLength;
    private int score;

    private static final int GAME_ACTIVITY_REQUEST_CODE = 42;

    SharedPreferences mPreferences;
    private static final String FIRSTNAME_SAVED = "FIRSTNAME_SAVED";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGreetingText = (TextView) findViewById(R.id.activity_main_greeting_text);
        mNameInput = (EditText) findViewById(R.id.activity_main_name_input);
        mPlayButton = (Button) findViewById(R.id.activity_main_play_button);
        mHistoricButton = (Button) findViewById(R.id.activity_main_historic_button);

        mUser = new User();

        mPreferences = getPreferences(MODE_PRIVATE);

        welcomeBack();

        mPlayButton.setEnabled(false);

        if (mNameInput.getText().length() > 0) {
            mPlayButton.setEnabled(true);
        }

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


        mHistoricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historicActivity = new Intent(MainActivity.this, HistoricActivity.class);
                startActivity(historicActivity);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode)
        {
            score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            welcomeBack();
        }
    }

    protected void welcomeBack() {

        firstname = mPreferences.getString(FIRSTNAME_SAVED, null);

        if (firstname != null) {
            mGreetingText.setText("Welcome back " + firstname +"! \nYour last score recorded is " + score +".");
            mNameInput.setText(firstname);
            textLength = mNameInput.getText().length();
            mNameInput.setSelection(textLength, textLength);
        } else {
            mNameInput.setText(mUser.getFirstName());
        }

    }


}
