package com.example.android.starwarsquizz;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ActivityAnswersTracker implements AnswersTracker {

    private final ProgressTracker PROGRESS_TRACKER;
    private final DataKeeper MY_ACTIVITY_DATA;
    private Activity myActivity;
    private Button finish;
    private View progress;

    ActivityAnswersTracker(Context mContext, ProgressTracker progressTracker, DataKeeper activityData){
        this.myActivity = (Activity)mContext;
        this.PROGRESS_TRACKER = progressTracker;
        this.MY_ACTIVITY_DATA = activityData;
        this.finish = (Button) MY_ACTIVITY_DATA.GetFinishView();
        this.progress = MY_ACTIVITY_DATA.GetProgressView();
    }

    //Create listener for TextBox and if the text is changed and the value is > 0 it is saved and marked as answered
    public void listenTextBox(View v){
        final EditText textBox = (EditText) v;
        textBox.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if (textBox.getText().toString().length() > 0){
                    // Value 1 means it is checked. Later used for answers counter.
                    MY_ACTIVITY_DATA.SetQuestionFourMarked(1);
                    MY_ACTIVITY_DATA.SetUserAnswerToQuestionFour(textBox.getText().toString());
                    PROGRESS_TRACKER.updateProgress(finish, progress);
                } else {
                    MY_ACTIVITY_DATA.SetQuestionFourMarked(0);
                    PROGRESS_TRACKER.updateProgress(finish, progress);
                }

            }
        });

        textBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    //Clear focus from edittext
                    textBox.clearFocus();
                    InputMethodManager imm = (InputMethodManager)myActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(textBox.getWindowToken(), 0);
                }
                return false;
            }
        });
    }

    //Creates listener for RadioGroup and if an option is marked the value is saved and marked as answered
    public void listenRadioGroups(View v){
        final RadioGroup radioGroup = (RadioGroup)v;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton checkedRadioButton = (RadioButton)group.findViewById(checkedId);
                boolean isChecked = checkedRadioButton.isChecked();
                if (isChecked)
                {
                    // Value 1 means it is checked. Later used for answers counter.
                    saveRadioSelection(radioGroup,checkedRadioButton);
                    PROGRESS_TRACKER.updateProgress(finish, progress);
                }
            }
        });
    }

    //Creates listener for CheckBox and if an option is marked the value is saved and marked as answered
    public void listenCheckboxes(View checkbox){
        final CheckBox markedUserAnswer = (CheckBox)checkbox;
        markedUserAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String markedValue = markedUserAnswer.getText().toString();
                if (markedUserAnswer.isChecked()){
                    if (!MY_ACTIVITY_DATA.GetUserAnswersToQuestionTwo().contains(markedValue)){
                        MY_ACTIVITY_DATA.GetUserAnswersToQuestionTwo().add(markedValue);
                    }

                    saveCheckboxSelection(markedUserAnswer,markedUserAnswer.isChecked());
                    PROGRESS_TRACKER.updateProgress(finish, progress);
                } else {
                    int position = MY_ACTIVITY_DATA.GetUserAnswersToQuestionTwo().indexOf(markedValue);
                    MY_ACTIVITY_DATA.GetUserAnswersToQuestionTwo().remove(position);

                    saveCheckboxSelection(markedUserAnswer,markedUserAnswer.isChecked());
                    PROGRESS_TRACKER.updateProgress(finish, progress);
                }
            }
        });
    }

    //Saves the checkbox selection to be used later for score calculation
    private void saveCheckboxSelection(View v, boolean isChecked){
        if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionTwoAnswerOne().getId()){
            MY_ACTIVITY_DATA.SetCheckedBoxMarkedOne(isChecked);
        } else if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionTwoAnswerTwo().getId()){
            MY_ACTIVITY_DATA.SetCheckedBoxMarkedTwo(isChecked);
        } else if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionTwoAnswerThree().getId()){
            MY_ACTIVITY_DATA.SetCheckedBoxMarkedThree(isChecked);
        } else if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionTwoAnswerFour().getId()){
            MY_ACTIVITY_DATA.SetCheckedBoxMarkedFour(isChecked);
        }
    }

    //Saves the radiobutton selection to be used later for score calculation
    private void saveRadioSelection(View v, View radioButton){
        RadioButton checkedRadioButton = (RadioButton)radioButton;
        if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionOne().getId()){
            MY_ACTIVITY_DATA.SetQuestionOneMarked(1);
            MY_ACTIVITY_DATA.SetUserAnswerToQuestionOne(checkedRadioButton.getText().toString());
        } else if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionThree().getId()){
            MY_ACTIVITY_DATA.SetQuestionThreeMarked(1);
            MY_ACTIVITY_DATA.SetUserAnswerToQuestionThree(checkedRadioButton.getText().toString());
        } else if (v.getId() == MY_ACTIVITY_DATA.GetViewQuestionFive().getId()){
            MY_ACTIVITY_DATA.SetQuestionFiveMarked(1);
            MY_ACTIVITY_DATA.SetUserAnswerToQuestionFive(checkedRadioButton.getText().toString());
        }
    }
}
