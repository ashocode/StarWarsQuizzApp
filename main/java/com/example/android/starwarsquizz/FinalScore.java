package com.example.android.starwarsquizz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int maxScore = intent.getIntExtra("max_score", 0);
        String side = intent.getStringExtra("side");
        displayScore(score, maxScore, side);
    }

    //Plays sound on button click
    private void playSound(){
        MediaPlayer player = MediaPlayer.create(this, R.raw.saber_sound);

        float volume = 0.3F;
        if (!player.isPlaying()){
            player.setVolume(volume, volume);
            player.start();
        }
    }

    //Displays the final score
    private void displayScore(int score, int maxScore, String side){
        String scoreString = " out of ";

        TextView scoreView = (TextView)findViewById(R.id.score);
        StringBuilder stringBuilder = new StringBuilder();

        //Score should not be a negative value
        if (score < 0){
            score = 0;
        }

        String scoreText = stringBuilder.append(score).append(scoreString).append(maxScore).toString();

        updateImage(score, side);

        scoreView.setText(scoreText);
    }

    //Updates the image depending on the score
    private void updateImage(int score, String side){
        ImageView scorePic = (ImageView)findViewById(R.id.score_picture);

        if (side.equals("jedi")){
            if (score <= 4){
                scorePic.setImageResource(R.drawable.rebel);
            } else if ((4 < score)&&(score < 7)){
                scorePic.setImageResource(R.drawable.rey);
            } else {
                scorePic.setImageResource(R.drawable.yoda);
            }
        } else if (side.equals("sith")){
            if (score <= 4){
                scorePic.setImageResource(R.drawable.trooper);
            } else if ((4 < score)&&(score < 7)){
                scorePic.setImageResource(R.drawable.kylo);
            } else {
                scorePic.setImageResource(R.drawable.darth_vader);
            }
        }
    }

    //Returns to the main page
    public void restart(View view){
        playSound();
        Intent restart = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(restart);
    }

}
