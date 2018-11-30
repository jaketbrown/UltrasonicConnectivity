package com.project.cmsc436.honeyguide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;


public class WelcomePage extends AppCompatActivity {

    private final String TAG = "Welcome Page";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        //get button references
        Button startButton = findViewById(R.id.start_button);
        startButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG,"click the start button");
                //after visitor click
                Intent newIntent = new Intent(WelcomePage.this,defaultPiece.class);
                startActivity(newIntent);
            }
        });



    }
}
