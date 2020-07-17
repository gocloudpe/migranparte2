package com.example.migranparte2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.MediaController;
import android.widget.Toast;

import java.io.IOException;

public class Multimedia3  extends AppCompatActivity implements SurfaceHolder.Callback, MediaPlayer.OnPreparedListener, MediaController.MediaPlayerControl {
    private MediaController mediaController;
    static MediaPlayer mediaPlayer;
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia3);
        surfaceView = (SurfaceView)findViewById(R.id.surfaceview);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        if(mediaPlayer==null) {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setOnPreparedListener(this);
            try {
                mediaPlayer.setDataSource("https://migrandiosaappwilson.000webhostapp.com/dancing.mp3");
                mediaPlayer.prepare();
            } catch (IOException e) {
            }
            construirMediaController();
        }
        else{
            if(mediaController==null)
            {
                construirMediaController();
                mediaController.setMediaPlayer(this);
                mediaController.setAnchorView(surfaceView);
                mediaController.setEnabled(true);
                mediaController.show();
            }
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    private void construirMediaController() {
        mediaController = new MediaController(this) {
            @Override
            public void hide() { }
            @Override
            public boolean dispatchKeyEvent(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                    if (mediaPlayer != null) {
                        mediaPlayer.reset();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    finish();
                    return true;
                }
                return super.dispatchKeyEvent(event);
            }
        };
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
        Toast.makeText(Multimedia3.this,
                "onPrepared()", Toast.LENGTH_LONG).show();
        mediaController.setMediaPlayer(this);
        mediaController.setAnchorView(surfaceView);
        mediaController.setEnabled(true);
        mediaController.show();

    }

    @Override
    public void start() { mediaPlayer.start(); }
    @Override
    public void pause() { mediaPlayer.pause(); }
    @Override
    public int getDuration() {
        try { return mediaPlayer.getDuration();}
        catch (Exception e) { return 0;}
    }
    @Override
    public int getCurrentPosition() {
        try { return mediaPlayer.getCurrentPosition(); }
        catch (Exception e) { return 0; }
    }
    @Override
    public void seekTo(int pos) {mediaPlayer.seekTo(pos); }
    @Override
    public boolean isPlaying() {
        try {return mediaPlayer.isPlaying();}
        catch (Exception e) {return false;}
    }
    @Override
    public int getBufferPercentage() { return 0; }
    @Override
    public boolean canPause() { return true; }
    @Override
    public boolean canSeekBackward() { return true; }
    @Override
    public boolean canSeekForward() { return true; }
    @Override
    public int getAudioSessionId() { return mediaPlayer.getAudioSessionId(); }
}