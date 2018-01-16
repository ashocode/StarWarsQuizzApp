package com.example.android.starwarsquizz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class JediActivity extends AppCompatActivity{

    private static final String SIDE_STRING = "side";
    private static final String JEDI_STRING = "jedi";
    private static final String MAX_SCORE_STRING = "max_score";
    private static final String SCORE_STRING = "score";
    private static final String ANSWER_TO_FIRST_QUESTION = "Nearly 900 years";
    private static final String ANSWER_ONE_TO_SECOND_QUESTION = "Yoda";
    private static final String ANSWER_TWO_TO_SECOND_QUESTION = "Mace Windu";
    private static final List<String> CORRECT_ANSWERS_FOR_SECOND_QUESTION = Arrays.asList(ANSWER_ONE_TO_SECOND_QUESTION,ANSWER_TWO_TO_SECOND_QUESTION);
    private static final String ANSWER_TO_THIRD_QUESTION = "A new hope";
    private static final String ANSWER_TO_FOURTH_QUESTION = "tatooine";
    private static final String ANSWER_TO_FIFTH_QUESTION = "Millennium Falcon";
    private MediaPlayer mp;
    private DataKeeper activityData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jedi_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        //Create a media player for sounds
        mp = MediaPlayer.create(this, R.raw.saber_sound);

        //Create object to store the Activity's data
        activityData = new ActivityDataKeeper();

        //Add Activity's views to the activityData Object
        activityData.SetFinishView((Button)findViewById(R.id.finish));
        activityData.SetProgressView((TextView)findViewById(R.id.progress));

        activityData.SetViewQuestionOne((RadioGroup)findViewById(R.id.jedi_question_one));
        activityData.SetViewQuestionTwoAnswerOne((CheckBox)findViewById(R.id.jedi_question_two_answer_one));
        activityData.SetViewQuestionTwoAnswerTwo((CheckBox)findViewById(R.id.jedi_question_two_answer_two));
        activityData.SetViewQuestionTwoAnswerThree((CheckBox)findViewById(R.id.jedi_question_two_answer_three));
        activityData.SetViewQuestionTwoAnswerFour((CheckBox)findViewById(R.id.jedi_question_two_answer_four));
        activityData.SetViewQuestionThree((RadioGroup)findViewById(R.id.jedi_question_three));
        activityData.SetViewQuestionFour((EditText) findViewById(R.id.jedi_answer_question_four));
        activityData.SetViewQuestionFive((RadioGroup)findViewById(R.id.jedi_question_five));

        //Create tracker to update the progress on the display
        ProgressTracker myProgressTracker = new ActivityProgressTracker(activityData);

        //Create tracker for listeners - used below
        AnswersTracker myAnswerTracker = new ActivityAnswersTracker(this,myProgressTracker, activityData);

        //Check if conditions to proceed are met
        myProgressTracker.canFinish(false, activityData.GetColorDisabled(), activityData.GetFinishView());

        //Reset variables on create. If there is a saved state the listeners will populate the data.
        myProgressTracker.resetProgress();

        //Create listeners
        myAnswerTracker.listenCheckboxes(activityData.GetViewQuestionTwoAnswerOne());
        myAnswerTracker.listenCheckboxes(activityData.GetViewQuestionTwoAnswerTwo());
        myAnswerTracker.listenCheckboxes(activityData.GetViewQuestionTwoAnswerThree());
        myAnswerTracker.listenCheckboxes(activityData.GetViewQuestionTwoAnswerFour());
        myAnswerTracker.listenRadioGroups(activityData.GetViewQuestionOne());
        myAnswerTracker.listenRadioGroups(activityData.GetViewQuestionThree());
        myAnswerTracker.listenTextBox(activityData.GetViewQuestionFour());
        myAnswerTracker.listenRadioGroups(activityData.GetViewQuestionFive());

    }

    //Plays sound unless the object is already playing.
    private void playSound(MediaPlayer player){
        float volume = 0.3F;
        if (!player.isPlaying()){
            player.setVolume(volume, volume);
            player.start();
        }
    }

    //Populates answers for single-answer questions to be used for the score calculation
    private List<SingleAnswersKeeper> populateSingleAnswersList(){
        //Creating a list to store the answers
        List<SingleAnswersKeeper> answersList = new ArrayList<>();

        SingleAnswersKeeper firstAnswer = new SingleAnswersKeeper(ANSWER_TO_FIRST_QUESTION, activityData.GetUserAnswerToQuestionOne());
        answersList.add(firstAnswer);
        SingleAnswersKeeper thirdAnswer = new SingleAnswersKeeper(ANSWER_TO_THIRD_QUESTION, activityData.GetUserAnswerToQuestionThree());
        answersList.add(thirdAnswer);
        SingleAnswersKeeper fourthAnswer = new SingleAnswersKeeper(ANSWER_TO_FOURTH_QUESTION, activityData.GetUserAnswerToQuestionFour());
        answersList.add(fourthAnswer);
        SingleAnswersKeeper fifthAnswer = new SingleAnswersKeeper(ANSWER_TO_FIFTH_QUESTION, activityData.GetUserAnswerToQuestionFive());
        answersList.add(fifthAnswer);

        return answersList;
    }

    //Populates answers for multi-answer questions to be used for the score calculation
    private CheckBoxAnswersKeeper populateMultipleAnswersList(){
        return new CheckBoxAnswersKeeper(CORRECT_ANSWERS_FOR_SECOND_QUESTION, activityData.GetUserAnswersToQuestionTwo());
    }

    //Returns to the main page
    public void goBack(View view){
        playSound(mp);
        Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToNextActivity);
    }

    //Moves to the final page with the user's score
    public void finish(View view){
        playSound(mp);
        CheckBoxAnswersKeeper checkboxAnswers = populateMultipleAnswersList();
        List<SingleAnswersKeeper> singleAnswers = populateSingleAnswersList();

        ScoreTracker tracker = new ActivityScoreTracker(activityData.GetPointsForSingleAnswerQuestions()
                , activityData.GetPointsForMultiAnswerQuestions());

        tracker.checkSingleAnswers(singleAnswers);
        tracker.checkMultiAnswers(checkboxAnswers);
        int score = tracker.calculateScore();

        Intent finish = new Intent(getApplicationContext(), FinalScore.class);
        finish.putExtra(SCORE_STRING, score);
        finish.putExtra(MAX_SCORE_STRING, activityData.GetMaxScore());
        finish.putExtra(SIDE_STRING, JEDI_STRING);
        startActivity(finish);
    }
}
