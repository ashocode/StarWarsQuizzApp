package com.example.android.starwarsquizz;

import java.util.List;

public interface ScoreTracker {
    void checkSingleAnswers(List<SingleAnswersKeeper> listOfAnswers);
    void checkMultiAnswers(CheckBoxAnswersKeeper listOfAnswers);
    int calculateScore();
}
