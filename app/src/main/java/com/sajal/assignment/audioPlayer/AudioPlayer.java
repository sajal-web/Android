package com.sajal.assignment.audioPlayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.sajal.assignment.app.udemytest1.R;

public class AudioPlayer extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button btnPlay, btnPause, btnPP;
    Boolean isPlay = false;
    private SeekBar volumeSeekbar = null;
    private AudioManager audioManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_player);
        mediaPlayer = MediaPlayer.create(this, R.raw.music);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        btnPause = findViewById(R.id.btnPause);
        btnPlay = findViewById(R.id.btnPlay);
        btnPP = findViewById(R.id.btnPP);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay == false) {
                    mediaPlayer.start();
                    isPlay = true;
                } else {
                    Toast.makeText(AudioPlayer.this, "Music is already playing", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay == true) {
                    mediaPlayer.pause();
                    isPlay = false;
                } else {
                    Toast.makeText(AudioPlayer.this, "Music is already paused...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnPP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isPlay == true) {
                    mediaPlayer.pause();
                    isPlay = false;
                } else {
                    mediaPlayer.start();
                    isPlay = true;
                }
            }
        });
        volumeSeekbar = (SeekBar) findViewById(R.id.volumeSeekBar);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeSeekbar.setMax(maxVolume);
        volumeSeekbar.setProgress(currentVol);
        volumeSeekbar.setProgress(audioManager
                .getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    public void play(View view) {
        Toast.makeText(this, "Playing Music", Toast.LENGTH_SHORT).show();
    }

    public void pause(View view) {
        Toast.makeText(this, "Pause music", Toast.LENGTH_SHORT).show();
    }
}