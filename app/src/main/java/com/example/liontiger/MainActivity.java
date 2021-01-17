package com.example.liontiger;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    enum player {
        ONE, TWO, No
    }

    player cur = player.ONE;

    player[] plCho = new player[9];

    int[][] arr = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}};

    private Boolean go = false;
    private Button btnReset;
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plCho[0] = player.No;
        plCho[1] = player.No;
        plCho[2] = player.No;
        plCho[3] = player.No;
        plCho[4] = player.No;
        plCho[5] = player.No;
        plCho[6] = player.No;
        plCho[7] = player.No;
        plCho[8] = player.No;

        btnReset = findViewById(R.id.btnReset);
        gridLayout = findViewById(R.id.gridLayout);

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTheGame();
            }
        });
    }

    public void imageViewIsTapped(View tapped) {
        ImageView ta = (ImageView) tapped;
        int tiTag = Integer.parseInt(ta.getTag().toString());
        if (plCho[tiTag] == player.No && go == false) {
            ta.setTranslationX(-2000);

            plCho[tiTag] = cur;
            if (cur == player.ONE) {
                ta.setImageResource(R.drawable.tiger);
                cur = player.TWO;
            } else {
                ta.setImageResource(R.drawable.lion);
                cur = player.ONE;
            }

            ta.animate().translationXBy(2000).alpha(1).rotation(3600).setDuration(2000);
            Toast.makeText(this, ta.getTag().toString(), Toast.LENGTH_SHORT).show();

            for (int[] winner : arr) {
                if (plCho[winner[0]] == plCho[winner[1]] && plCho[winner[1]] == plCho[winner[2]] && plCho[winner[0]] != player.No) {
                    btnReset.setVisibility(View.VISIBLE);
                    go = true;
                    String a = "";
                    if (cur == player.ONE) {
                        a = "Player Two";
                    } else {
                        a = "Player One";
                    }
                    Toast.makeText(this, a + " is The Winner", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

    private void resetTheGame() {

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);
        }
         cur = player.ONE;

        plCho[0] = player.No;
        plCho[1] = player.No;
        plCho[2] = player.No;
        plCho[3] = player.No;
        plCho[4] = player.No;
        plCho[5] = player.No;
        plCho[6] = player.No;
        plCho[7] = player.No;
        plCho[8] = player.No;

        go = false;

        btnReset.setVisibility(View.INVISIBLE);
    }
}
