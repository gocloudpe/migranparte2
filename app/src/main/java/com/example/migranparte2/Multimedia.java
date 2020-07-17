package com.example.migranparte2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Multimedia  extends AppCompatActivity {
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);
        mp = MediaPlayer.create(this, R.raw.musica);
        mp.start();
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mp.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mp.pause();
    }

    public void multimedia2(View view) {
        startActivity(new Intent(this, Multimedia2.class));
    }

    public void multimedia3(View view) {
        startActivity(new Intent(this, Multimedia3.class));
    }
}