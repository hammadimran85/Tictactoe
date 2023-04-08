package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean active = true;
    int activePlayer = 0;
    int[] state={2,2,2,2,2,2,2,2,2};
    int[][] winstates =  {{0,1,2}, {3,4,5}, {6,7,8},
            {0,3,6}, {1,4,7}, {2,5,8},
            {0,4,8}, {2,4,6}};
    public static int counter = 0;
    public void playertap(View view)
    {
        ImageView img = (ImageView) view;
        int tapImage = Integer.parseInt(img.getTag().toString());
            if(!active)
            {
                Reset(view);
            }
            if (state[tapImage] == 2)
            {
                counter++;
                if (counter==9){
                    active=false;
                }
                state[tapImage] = activePlayer;
                img.setTranslationY(-1000f);
                if (activePlayer == 0)
                {
                    img.setImageResource(R.drawable.x);
                    activePlayer = 1;
                    TextView status = findViewById(R.id.status);
                    status.setText("0's Turn - Tap to Play");
                }
                else
                {
                    img.setImageResource(R.drawable.o);
                    activePlayer = 0;
                    TextView status = findViewById(R.id.status);
                    status.setText("X's Turn - Tap to Play");
                }
            }
            img.animate().translationYBy(1000f).setDuration(300);
            int flag = 0;
        for(int[] winstate: winstates){
            if (state[winstate[0]] == state[winstate[1]] &&
                    state[winstate[1]] == state[winstate[2]] &&
                    state[winstate[0]]!=2){
                flag=1;
                String winStr;
                active = false;
                if (state[winstate[0]] == 0){
                    winStr = "Player X wins!";
                }
                else {
                    winStr = "Player 0 wins!";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winStr);
            }
        }
        if (counter == 9 && flag == 0) {
            TextView status = findViewById(R.id.status);
            status.setText("Match Draw");
        }
    }

    private void Reset(View view) {
        active = true;
        activePlayer = 0;
        for (int i = 0; i<state.length;i++){
            state[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("Player X's Turn - Tap to Play");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}