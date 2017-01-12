package in.thelabs.spotthecolor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// This is our first activity or launcher activity.
// This is the activity that gets launched when any one clicks on our App Icon.

public class MainActivity extends AppCompatActivity {

    //Creating all the elements that we have on the face (xml file) here as well.
    //So we have 2 text-views and 1 button
    TextView highScoreLabel;
    TextView highScore;
    Button playButton;

    //Internal file that we will create/or get highscore from
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences("SpotTheColor", Context.MODE_PRIVATE);
        int hScore = sp.getInt("HIGHSCORE",0);

        //let us connect the local java variable to the face ids

        highScore = (TextView) findViewById(R.id.highScore); //We are also typecasting it into TextView from View.
        highScoreLabel = (TextView) findViewById(R.id.highScoreLabel);
        playButton = (Button) findViewById(R.id.playButton);

        //Setting the last saved highScore to the textView
        highScore.setText(String.valueOf(hScore));

        //This method here sets up a listener for the click events on playButton
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //The code that begins when there is a click on playbutton
                //It will take us to the new Activity called GameActivity

                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
            }
        });

    }
}
