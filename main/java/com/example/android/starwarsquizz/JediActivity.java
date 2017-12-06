package com.example.android.starwarsquizz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import 	android.webkit.WebView;
import android.widget.TextView;

public class JediActivity extends AppCompatActivity {



    public void goBackJedi(View view){
        Intent goToNextActivity = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goToNextActivity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jedi_layout);
    }

}
