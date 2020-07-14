package com.example.littlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static boolean change = true;
    LinearLayout playAgain;
    public static int counter = 0;
    TextView gameTextView;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int[][] winningPositions ={{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void click(View view)
    {
        ImageView button = (ImageView) view;

        int tag = Integer.parseInt(button.getTag().toString());
        if(gameState[tag-1]==2){
            counter++;

            button.setTranslationY(-1000);

            if(change==true){
                button.setImageResource(R.drawable.red);
                gameState[tag-1] = 0;
                change = false;
            }else{
                button.setImageResource(R.drawable.yellow);
                gameState[tag-1] = 1;
                change = true;
            }
            button.animate().translationYBy(1000f).setDuration(300);
            print(gameState);
            checkWinning();
        }

        if(counter == 9 && playAgain.getAlpha() == 0){
            gameTextView.setText("Game Over");
            end();
        }
    }

    public void print(int[] tab){
        for(int i=0;i<tab.length;i++)
            System.out.print(tab[i] + " ");
        System.out.println();
    }

    public void checkWinning(){
        for(int[] winningPosition : winningPositions){
            if(playAgain.getAlpha() == 0 && gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[0]] == gameState[winningPosition[2]] && gameState[winningPosition[0]]!= 2){
                if(gameState[winningPosition[0]] == 0)  gameTextView.setText("Red wins");
                else             gameTextView.setText("Yellow wins");

                end();
            }
        }
    }

    public void end(){
        playAgain.setVisibility(View.VISIBLE);
        playAgain.animate().alpha(1f).setDuration(1000).rotation(360f);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
    }

    public void reset(){
        playAgain.setVisibility(View.INVISIBLE);
        playAgain.setAlpha(0f);
        counter = 0;
        gameTextView.setText("Game");
        for(int i =0;i<gameState.length;i++)
            gameState[i]=2;

        GridLayout layout = findViewById(R.id.gridLayout);
        System.out.println(layout.getChildCount());

        for(int i=0;i<layout.getChildCount();i++)
            ((ImageView)layout.getChildAt(i)).setImageResource(0);

        //for(int i)
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gameTextView = findViewById(R.id.textView);
        playAgain = findViewById(R.id.linearLayout);

    }
}
