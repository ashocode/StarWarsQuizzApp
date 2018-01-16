package com.example.android.starwarsquizz;


import android.view.View;


public interface AnswersTracker {
    void listenTextBox(View v);

    void listenRadioGroups(View v);

    void listenCheckboxes(View checkbox);
}
