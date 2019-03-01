package com.example.topquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    private TextView mQuestionText;
    private Button mAnswerOneButton;
    private Button mAnswerTwoButton;
    private Button mAnswerThreeButton;
    private Button mAnswerFourButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerOneButton = (Button) findViewById(R.id.activity_game_answer1_button);
        mAnswerTwoButton = (Button) findViewById(R.id.activity_game_answer2_button);
        mAnswerThreeButton = (Button) findViewById(R.id.activity_game_answer3_button);
        mAnswerFourButton = (Button) findViewById(R.id.activity_game_answer4_button);
    }
}
