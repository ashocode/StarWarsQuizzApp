package com.example.android.starwarsquizz;


import android.view.View;


public interface ProgressTracker {

    void resetProgress();
    void updateProgress(View button, View textView);
    void canFinish(boolean isReady, String color, View button);
}
