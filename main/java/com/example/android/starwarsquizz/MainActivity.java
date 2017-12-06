package com.example.android.starwarsquizz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String jediSay = "May the Force be with you!";
    private static final String sithSay = "May the Force serve you well!";
    private static final String jediColor = "#84FFFF";
    private static final String sithColor = "#E53935";
    private static final String selectedBackgroundColor = "#37474F";
    private static final String defaultBackgroundColor = "#000000";
    private static final String KEY_CURRENT_VALUE = "";

    private String selectedSide = "";


    //Displays a different message depending on which side is selected
    private void displaySelectedSide(String side){
        TextView view = (TextView)findViewById(R.id.selected_side);

        if (side == jediSay){;
            view.setTextColor(Color.parseColor(jediColor));
            view.setText(String.valueOf(jediSay));
        } else if (side == sithSay){
            view.setTextColor(Color.parseColor(sithColor));
            view.setText(String.valueOf(sithSay));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        displaySelectedSide(selectedSide);
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
    }

    //Changes the background of the Sith button, resets the background of the Jedi button and displays a message.
    public void selectSith(View view){
        setSithBackground(selectedBackgroundColor);
        setJediBackground(defaultBackgroundColor);
        displaySelectedSide(sithSay);
        selectedSide = sithSay;
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
    }


}
