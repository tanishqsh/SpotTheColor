package in.thelabs.spotthecolor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    /**
     *     lets connect XML id views to java variables, we have 4 quads that we will need
     *     and we need to increase the score counter, so we will also connect (instantiate) currentScore textview
     *     and we need to change the instruction dynamically, so we will connect that too.
     */

    LinearLayout firstQuad; //Just in case you are wondering, the ids/variable names need not be same,
    // I do it so that it becomes easy to call them later on
    LinearLayout secondQuad;
    LinearLayout thirdQuad;
    LinearLayout fourthQuad;
    TextView currentScore;
    TextView instruction;

    //#4caf50 - Green
    //#1976D2 - Blue
    //#D32F2F - Red
    //ff3b3b - Yellow


    //We have some predefined arrays from which we will be populating and selecting colors.
    String[] colorArray = {"#1976D2", "#4caf50", "#D32F2F", "#FFC107"};
    String[] colorWords = {"Blue", "Green", "Red", "Yellow"};

    //We have two keepers for randomly generated numbers
    int textColor;
    int nameWord;
    int scoreCounter;


    //Internal File To Save Certain Data
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //Lets call create the internal file/or create for this app to save highScores
        sp = this.getSharedPreferences("SpotTheColor", Context.MODE_PRIVATE);
        editor = sp.edit();

        firstQuad = (LinearLayout) findViewById(R.id.firstQuad);
        secondQuad = (LinearLayout) findViewById(R.id.secondQuad);
        thirdQuad = (LinearLayout) findViewById(R.id.thirdQuad);
        fourthQuad = (LinearLayout) findViewById(R.id.fourthQuad);
        currentScore = (TextView) findViewById(R.id.currentScore);
        instruction = (TextView) findViewById(R.id.instruction);

        scoreCounter = 0;

        //Lets give them colors

        firstQuad.setBackgroundColor(Color.parseColor(colorArray[0]));
        secondQuad.setBackgroundColor(Color.parseColor(colorArray[1]));
        thirdQuad.setBackgroundColor(Color.parseColor(colorArray[2]));
        fourthQuad.setBackgroundColor(Color.parseColor(colorArray[3]));

        callGenerate();

        firstQuad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameWord==0)
                {
                    scoreCounter++; //Increases the score counter by one
                    UpdateUI(); //displays the new score onto the textview
                    callGenerate(); //Sets new instructions
                }
                else
                {
                    Toast.makeText(GameActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    SaveHighScore(); //Saves high-score and closes the game
                }
            }
        });


        secondQuad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameWord==1)
                {
                    scoreCounter++;
                    UpdateUI();
                    callGenerate();
                }
                else
                {
                    Toast.makeText(GameActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    SaveHighScore(); //Saves high-score and closes the game
                }
            }
        });

        thirdQuad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameWord==2)
                {
                    scoreCounter++;
                    UpdateUI();
                    callGenerate();
                }
                else
                {
                    Toast.makeText(GameActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    SaveHighScore(); //Saves high-score and closes the game

                }
            }
        });

        fourthQuad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameWord==3)
                {
                    scoreCounter++;
                    UpdateUI();
                    callGenerate();
                }
                else
                {
                    Toast.makeText(GameActivity.this, "Fail", Toast.LENGTH_SHORT).show();
                    SaveHighScore(); //Saves high-score and closes the game

                }
            }
        });

    }


    private int generate()
    {
        Random r = new Random();
        int Low = 0;
        int High = 4;
        int result = r.nextInt(High-Low) + Low;
        return result;
    }

    private void UpdateUI()
    {
        currentScore.setText(String.valueOf(scoreCounter));
    }

    private void callGenerate()
    {
        textColor = generate();
        instruction.setTextColor(Color.parseColor(colorArray[textColor]));
        nameWord = generate();
        String ins = "Tap the "+colorWords[nameWord]+" tile";
        instruction.setText(ins);
    }

    private void SaveHighScore()
    {
        Toast.makeText(this, "You scored "+scoreCounter, Toast.LENGTH_SHORT).show();
        int k = sp.getInt("HIGHSCORE", 0);
        if(scoreCounter>k)
        {
            //it is a high score and we will update the file in our mobile with new highscore
            editor.putInt("HIGHSCORE", scoreCounter);
            editor.apply();
        }

        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
