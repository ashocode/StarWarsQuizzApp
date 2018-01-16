package com.example.android.starwarsquizz;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String jediSay = "May the Force be with you!";
    private static final String sithSay = "May the Force serve you well!";
    private static final String jediColor = "#84FFFF";
    private static final String sithColor = "#E53935";
    private static final String selectedBackgroundColor = "#37474F";
    private static final String defaultBackgroundColor = "#000000";
    private static final String KEY_CURRENT_VALUE = "selected side";
    private static final String COLOR_ENABLED = "#FFEA00";
    private static final String COLOR_DISABLED = "#9E9E9E";

    private static String selectedSide = "";
    private MediaPlayer playOnSelectSide;
    private MediaPlayer playOnStart;
    private boolean hasToPlay = true;


    //Displays a different message depending on which side is selected
    private void displaySelectedSide(String side){
        TextView view = (TextView)findViewById(R.id.selected_side);

        if (side.equals(jediSay)){;
            view.setTextColor(Color.parseColor(jediColor));
            view.setText(String.valueOf(jediSay));
        } else if (side.equals(sithSay) ){
            view.setTextColor(Color.parseColor(sithColor));
            view.setText(String.valueOf(sithSay));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectedSide = "";
        canFinish();
        playOnSelectSide = MediaPlayer.create(this, R.raw.select_side);
        playOnStart = MediaPlayer.create(this, R.raw.start);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        // Save the current value of the variable.
        savedInstanceState.putString(KEY_CURRENT_VALUE, selectedSide);

        // Always call the super class so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        // Passing the saved state value to the variable
        selectedSide = savedInstanceState.getString(KEY_CURRENT_VALUE);

        if (selectedSide == jediSay){
            hasToPlay = false;
            selectJedi(findViewById(R.id.jedi_button));
            hasToPlay = true;
        } else if (selectedSide == sithSay){
            hasToPlay = false;
            selectSith(findViewById(R.id.sith_button));
            hasToPlay = true;
        }
    }

    //Plays sounds on touch
    private void playSound(MediaPlayer player){
        float volume = 0.3F;
        if (!player.isPlaying()){
            player.setVolume(volume, volume);
            player.start();
        }
    }

    //Enables/disables the start button depending if a side is selected or not.
    private void canFinish(){
        Button finish = (Button)findViewById(R.id.start);

        if (selectedSide.isEmpty()){
            finish.setEnabled(false);
            finish.setTextColor(Color.parseColor(COLOR_DISABLED));
        } else {
            finish.setEnabled(true);
            finish.setTextColor(Color.parseColor(COLOR_ENABLED));
        }


    }

    //Changes the background of the Jedi button
    private void setJediBackground(String color){
        ImageButton jediButton = (ImageButton)findViewById(R.id.jedi_button);
        jediButton.setBackgroundColor(Color.parseColor(color));
    }

    //Changes the background of the Sith button
    private void setSithBackground(String color){
        ImageButton sithButton = (ImageButton)findViewById(R.id.sith_button);
        sithButton.setBackgroundColor(Color.parseColor(color));
    }

    //Changes the background of the Jedi button, resets the background of the Sith button and displays a message.
    public void selectJedi(View view){
        setJediBackground(selectedBackgroundColor);
        setSithBackground(defaultBackgroundColor);
        displaySelectedSide(jediSay);
        selectedSide = jediSay;

        if (hasToPlay){
            playSound(playOnSelectSide);
        }

        canFinish();
    }

    //Changes the background of the Sith button, resets the background of the Jedi button and displays a message.
    public void selectSith(View view){
        setSithBackground(selectedBackgroundColor);
        setJediBackground(defaultBackgroundColor);
        displaySelectedSide(sithSay);
        selectedSide = sithSay;

        if (hasToPlay){
            playSound(playOnSelectSide);
        }

        canFinish();
    }

    //Loads the next activity based on the selected side.
    public void startQuizz(View view){
        if (selectedSide == jediSay){
            Intent goToNextActivity = new Intent(getApplicationContext(), JediActivity.class);
            startActivity(goToNextActivity);
        } else if  (selectedSide == sithSay){
            Intent goToNextActivity = new Intent(getApplicationContext(), SithActivity.class);
            startActivity(goToNextActivity);
        }
        playSound(playOnStart);
    }


}
