package com.project.cmsc436.honeyguide;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.content.Intent;

import io.chirp.connect.ChirpConnect;
import io.chirp.connect.interfaces.ConnectEventListener;
import io.chirp.connect.interfaces.ConnectSetConfigListener;
import io.chirp.connect.models.ChirpError;


public class WelcomePage extends AppCompatActivity {

    private String TAG = "Honeyguide-Debug: ";
    private final int RESULT_REQUEST_RECORD_AUDIO = 1;

    ConnectEventListener connectEventListener;
    ChirpConnect chirpConnect;

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


        String KEY = getString(R.string.chirp_application_key);
        String SECRET = getString(R.string.chirp_client_secret);

        String CONFIG = "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";

        chirpConnect = new ChirpConnect(this, KEY, SECRET);

        chirpConnect.setConfig(CONFIG, new ConnectSetConfigListener() {

            @Override
            public void onSuccess() {
                Log.i("setConfig", "Config successfully set.");
            }

            @Override
            public void onError(ChirpError setConfigError) {
                Log.e("setConfig", setConfigError.getMessage());
            }
        });

        connectEventListener = new ConnectEventListener() {

            @Override
            public void onSending(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload is being sent " + payload + " on channel: " + channel);
            }

            @Override
            public void onSent(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload has been sent " + payload  + " on channel: " + channel);
            }

            @Override
            public void onReceiving(byte channel) {
                Log.v(TAG, "This is called when the SDK is expecting a payload to be received on channel: " + channel);
            }

            @Override
            public void onReceived(byte[] payload, byte channel) {
                Log.v(TAG, "This is called when a payload has been received " + payload  + " on channel: " + channel);
            }

            @Override
            public void onStateChanged(byte oldState, byte newState) {
                Log.v(TAG, "This is called when the SDK state has changed " + oldState + " -> " + newState);
            }

            @Override
            public void onSystemVolumeChanged(int old, int current) {
                Log.d(TAG, "This is called when the Android system volume has changed " + old + " -> " + current);
            }

        };

        chirpConnect.setListener(connectEventListener);
        chirpConnect.start();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECORD_AUDIO}, RESULT_REQUEST_RECORD_AUDIO);
        }
        else {
            chirpConnect.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RESULT_REQUEST_RECORD_AUDIO: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    chirpConnect.start();
                }
                return;
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        chirpConnect.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chirpConnect.stop();
        try {
            chirpConnect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
