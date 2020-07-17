package com.example.migranparte2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class Multimedia2  extends AppCompatActivity {
    private MediaController mediaController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia2);
        configureVideoView();
    }
    private void configureVideoView() {
        final VideoView videoView =findViewById(R.id.videoView1);
        videoView.setVideoPath(
                "https://www.ebookfrenzy.com/android_book/movie.mp4");
        mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}