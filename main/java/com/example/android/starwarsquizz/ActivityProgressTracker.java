package com.example.android.starwarsquizz;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class ActivityProgressTracker implements ProgressTracker{
    private static final int RESET_INT_VALUE = 0;
    private static final boolean RESET_BOOLEAN_VALUE = false;
    private static final String RESET_STRING_VALUE = "";
    private static final int VALUE_FOR_MARKED = 1;
    private static final String NULL_OBJECT_RECEIVED = "Expected DataKeeper, received NULL!";
    private DataKeeper myActivityData;

    private int calculateProgress() {
        return myActivityData.GetQuestionOneMarked() + myActivityData.GetQuestionTwoMarked()
                + myActivityData.GetQuestionThreeMarked() + myActivityData.GetQuestionFourMarked()
                + myActivityData.GetQuestionFiveMarked();
    }

    //Displays the current progress in the relevant TextView
    private void displayProgress(View textView) {
        String progressText = " out of ";
        int numberOfMarked = this.calculateProgress();
        TextView progressView = (TextView) textView;

        progressView.setText(String.format(Locale.getDefault(), "%d%s%d", numberOfMarked, progressText, myActivityData.GetNumberOfQuestions()));
    }

    ActivityProgressTracker(DataKeeper activity) {
        try {
            this.myActivityData = activity;
        } catch (NullPointerException e) {
            System.out.println(NULL_OBJECT_RECEIVED + e.getMessage());
        }
    }

    //Resets the values in the DataKeeper object
    public void resetProgress() {
        myActivityData.SetQuestionOneMarked(RESET_INT_VALUE);
        myActivityData.SetQuestionTwoMarked(RESET_INT_VALUE);
        myActivityData.SetQuestionThreeMarked(RESET_INT_VALUE);
        myActivityData.SetQuestionFourMarked(RESET_INT_VALUE);
        myActivityData.SetQuestionFiveMarked(RESET_INT_VALUE);
        myActivityData.SetCheckedBoxMarkedOne(RESET_BOOLEAN_VALUE);
        myActivityData.SetCheckedBoxMarkedTwo(RESET_BOOLEAN_VALUE);
        myActivityData.SetCheckedBoxMarkedThree(RESET_BOOLEAN_VALUE);
        myActivityData.SetCheckedBoxMarkedFour(RESET_BOOLEAN_VALUE);
        myActivityData.ResetUserAnswersToQuestionTwo();
        myActivityData.SetUserAnswerToQuestionOne(RESET_STRING_VALUE);
        myActivityData.SetUserAnswerToQuestionThree(RESET_STRING_VALUE);
        myActivityData.SetUserAnswerToQuestionFour(RESET_STRING_VALUE);
        myActivityData.SetUserAnswerToQuestionFive(RESET_STRING_VALUE);
        calculateProgress();
    }

    //Updates the progress based on the current answers selection
    public void updateProgress(View button, View textView) {

        if (myActivityData.GetCheckedBoxOneMarked() || myActivityData.GetCheckedBoxTwoMarked()
                || myActivityData.GetCheckedBoxThreeMarked() || myActivityData.GetCheckedBoxFourMarked()) {
            myActivityData.SetQuestionTwoMarked(VALUE_FOR_MARKED);
        } else {
            myActivityData.SetQuestionTwoMarked(RESET_INT_VALUE);
        }

        int numberOfMarkedQuestions = calculateProgress();

        if (myActivityData.GetNumberOfQuestions() == numberOfMarkedQuestions) {
            this.canFinish(true, myActivityData.GetColorEnabled(), button);
        } else {
            this.canFinish(false, myActivityData.GetColorDisabled(), button);
        }

        this.displayProgress(textView);
    }

    //Sets the finish button enabled/disabled depending on the current progress
    public void canFinish(boolean isReady, String color, View button) {
        Button finish = (Button) button;
        finish.setEnabled(isReady);
        finish.setTextColor(Color.parseColor(color));
    }
}
