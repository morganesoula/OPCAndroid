package com.example.topquiz.model;

import java.util.List;

public class Question {

    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
        mQuestion = question;
        mChoiceList = choiceList;
        mAnswerIndex = answerIndex;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        if (question.length() < 1) {
            throw new IllegalArgumentException("Please enter a valid question...");
        } else {
            mQuestion = question;
        }

    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public void setChoiceList(List<String> choiceList) {
        if (choiceList != null) {
            mChoiceList = choiceList;
        } else {
            throw new IllegalArgumentException("Array must have one element at least");
        }

    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setAnswerIndex(int answerIndex) {
        if (answerIndex > 0 ) {
            mAnswerIndex = answerIndex;
        } else {
            throw new IllegalArgumentException("Should have at least one answer");
        }

    }
}


