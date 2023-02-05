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

import java.util.Timer;
import java.util.TimerTask;

public class AudioPlayer extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    Button btnPlay, btnPause, btnPP;
    Boolean isPlay = false;
    private SeekBar volumeSeekbar = null,musicSeekbar;
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
        musicSeekbar =(SeekBar) findViewById(R.id.musicSeekbar);

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
        // get the length of the musicseekbar
        musicSeekbar.setMax(mediaPlayer.getDuration());
        musicSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                // progress change during running
            seekBar.setProgress(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        // by using this timmer here we make the seekbar auto run
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            musicSeekbar.setProgress(mediaPlayer.getCurrentPosition());
            }
        },0,100);

    }

    public void play(View view) {
        Toast.makeText(this, "Playing Music", Toast.LENGTH_SHORT).show();
    }

    public void pause(View view) {
        Toast.makeText(this, "Pause music", Toast.LENGTH_SHORT).show();
    }
}