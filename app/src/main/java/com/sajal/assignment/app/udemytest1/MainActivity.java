package com.sajal.assignment.app.udemytest1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.sajal.assignment.app.videoPlayer.VideoPlayer;

public class MainActivity extends AppCompatActivity {
    ImageView imageView, imageView2;
    TextView winnerTextview;
    Button playAgainButton;
    GridLayout gridLayout;
    boolean isShowingImg = true;
    // 1 -- red, 0-- green
    int currentPlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winningPositions = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}
    };
    boolean gameStatus = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        winnerTextview = findViewById(R.id.winnerTextview);
        playAgainButton = findViewById(R.id.playAgainButton);
        gridLayout = findViewById(R.id.gridLayout);
//        imageView = findViewById(R.id.imageView);
//        imageView.setX(-1000);
//        imageView.animate().translationXBy(1000).rotation(3600).setDuration(1000);

    }
    public void fade(View view) {
//        imageView = findViewById(R.id.imageView);
//        imageView2 = findViewById(R.id.imageView2);

//        if(isShowingImg){
//            isShowingImg = false;
//            imageView.animate().alpha(0).setDuration(4000);
//            imageView2.animate().alpha(1).setDuration(4000);
//        }else {
//            isShowingImg = true;
//            imageView.animate().alpha(1).setDuration(2000);
//            imageView2.animate().alpha(0).setDuration(2000);
//        }
//        imageView.animate().rotationY(3600).setDuration(10000);
//            imageView.animate().rotationBy(360000).setDuration(1000);
//            imageView.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
//        imageView.animate().translationX(-10000).setDuration(1000);


    }

    public void takeBall(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameStatus) {
            gameState[tappedCounter] = currentPlayer;
            counter.setTranslationY(-1500);
            Log.i("counter", counter.getTag().toString());
            if (currentPlayer == 0) {
                counter.setImageResource(R.drawable.one);
                currentPlayer = 1;
            } else {
                counter.setImageResource(R.drawable.tow);
                currentPlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(1500).setDuration(500);

            for (int[] winningposition : winningPositions) {
                if (gameState[winningposition[0]] == gameState[winningposition[1]] && gameState[winningposition[1]] == gameState[winningposition[2]] && gameState[winningposition[0]] != 2) {
                    gameStatus = false;
                    String winner = "";
                    if (currentPlayer == 1) {
                        winner = "red";
                    } else {
                        winner = "green";
                    }
                   winnerTextview.setText(winner+" won 😎");
                    winnerTextview.setVisibility(View.VISIBLE);
                    playAgainButton.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void restartGame(View view) {
         currentPlayer = 0;
         for(int i =0;i<gameState.length;i++){
             gameState[i] = 2;
         }
         gameStatus = true;
        winnerTextview.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        for(int i = 0;i<gridLayout.getChildCount();i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }
    }

    public void goToPlayVideo(View view) {
        Intent intent = new Intent(MainActivity.this, VideoPlayer.class);
        startActivity(intent);
    }
}
