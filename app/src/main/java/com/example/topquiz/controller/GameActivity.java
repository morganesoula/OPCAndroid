package com.example.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import java.util.Arrays;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.topquiz.R;
import com.example.topquiz.model.Question;
import com.example.topquiz.model.QuestionBank;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mQuestionText;

    private Button mAnswerOneButton;
    private Button mAnswerTwoButton;
    private Button mAnswerThreeButton;
    private Button mAnswerFourButton;

    private QuestionBank mQuestionBank;

    private Question mCurrentQuestion;

    private int mNumberOfQuestions;
    private int mActualScore;

    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mQuestionBank = this.generateQuestions();
        mNumberOfQuestions = 3;
        mActualScore = 0;

        mQuestionText = (TextView) findViewById(R.id.activity_game_question_text);
        mAnswerOneButton = (Button) findViewById(R.id.activity_game_answer1_button);
        mAnswerTwoButton = (Button) findViewById(R.id.activity_game_answer2_button);
        mAnswerThreeButton = (Button) findViewById(R.id.activity_game_answer3_button);
        mAnswerFourButton = (Button) findViewById(R.id.activity_game_answer4_button);

        mAnswerOneButton.setTag(0);
        mAnswerTwoButton.setTag(1);
        mAnswerThreeButton.setTag(2);
        mAnswerFourButton.setTag(3);

        mAnswerOneButton.setOnClickListener(this);
        mAnswerTwoButton.setOnClickListener(this);
        mAnswerThreeButton.setOnClickListener(this);
        mAnswerFourButton.setOnClickListener(this);

        mCurrentQuestion = mQuestionBank.getQuestion();
        this.displayQuestion(mCurrentQuestion);

    }

    private QuestionBank generateQuestions() {
        Question question1 = new Question("What is Elvis Presley's middle name?", Arrays.asList("Mickael", "George", "Aaron", "Ethan"), 2);
        Question question2 = new Question("How many strings has a mandolin got?", Arrays.asList("7", "8", "9", "10"), 1);
        Question question3 = new Question("What is the house number of the Simpsons", Arrays.asList("42", "101", "666", "742"), 3);
        Question question4 = new Question("Which country is the origin of the cocktail Mojito?", Arrays.asList("Cuba", "Spain", "Brazil", "Mexico"), 0);
        Question question5 = new Question("With which fruit is Kriek beer (a Belgium brew) is flavoured?", Arrays.asList("Cherry", "Strawberry", "Blackberry", "Blueberry"), 0);
        Question question6 = new Question("In what year was Google launched on the web?", Arrays.asList("1996", "1998", "2000", "2002"), 1);
        Question question7 = new Question("What is the most spoken language in the world", Arrays.asList("French", "Spanish", "French", "Chinese"), 3);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4, question5, question6, question7));
    }

    private void displayQuestion(final Question question) {
        mQuestionText.setText(question.getQuestion());
        mAnswerOneButton.setText(question.getChoiceList().get(0));
        mAnswerTwoButton.setText(question.getChoiceList().get(1));
        mAnswerThreeButton.setText(question.getChoiceList().get(2));
        mAnswerFourButton.setText(question.getChoiceList().get(3));
    }

    @Override
    public void onClick(View v) {
        int responseIndex = (int) v.getTag();

        if (responseIndex == mCurrentQuestion.getAnswerIndex()) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mActualScore++;
        } else {
            Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show();
        }

        if (--mNumberOfQuestions == 0) {
            endGame();
        } else {
            mCurrentQuestion = mQuestionBank.getQuestion();
            displayQuestion(mCurrentQuestion);
        }
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done!")
                .setMessage("The game is over and your score is " + mActualScore)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mActualScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}

