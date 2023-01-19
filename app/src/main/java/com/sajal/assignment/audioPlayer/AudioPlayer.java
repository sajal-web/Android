package com.sajal.assignment.audioPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.sajal.assignment.app.udemytest1.R;

public class AudioPlayer extends AppCompatActivity {
MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        mediaPlayer= MediaPlayer.create(this,R.raw.music);
    }

    public void play(View view) {

            mediaPlayer.start();



    }

    public void pause(View view) {
            mediaPlayer.stop();

    }
}