package sogoh.com.zilvinasj.naujas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zilvinasjankunas on 10/05/2018.
 */

public class GameOver extends AppCompatActivity{
    private String TAG = GameOver.class.getSimpleName();
    int HighScore;
    String score1;
    int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey",
                Context.MODE_PRIVATE);
        if(prefs.contains("key")) {
            HighScore = prefs.getInt("key", 0);
        }
        else{
            HighScore=0;
        }
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            score = extras.getInt("Score");
            score1 = String.valueOf(score);
            Log.e(TAG, score1);
        }
        //checks if the score is higher than the High Score and updates it if needed

        if(HighScore<score) {


            int oldScore = prefs.getInt("key", 0);

            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt("key", score);
            edit.commit();
            TextView scoreText = (TextView) findViewById(R.id.textView9);
            scoreText.setText(String.valueOf(score) + "\n (New Highscore)");
        }
        else{
            TextView scoreText = (TextView) findViewById(R.id.textView9);
            scoreText.setText(score1);
        }
    }


    //Starts the game again if the button is clicked
    public void startGame(View view) {
        finish();
        Intent intentStart = new Intent(this, MainActivity.class);
        startActivity(intentStart);
    }
    boolean doubleBackToExitPressedOnce = false;
    //If go back twice - exit
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}


