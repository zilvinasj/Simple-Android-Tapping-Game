package sogoh.com.zilvinasj.naujas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by zilvinasjankunas on 10/05/2018.
 */

public class StartActivity extends AppCompatActivity {
    //Just a simple class that allows the user to start the game, fetches the high score if it exists
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey",
                Context.MODE_PRIVATE);
        if(prefs.contains("key")){
            TextView highScore = (TextView) findViewById(R.id.textView3);
            highScore.setText(String.valueOf(prefs.getInt("key",0)));
        }
        else{
            SharedPreferences.Editor edit = prefs.edit();
            edit.putInt("key", 0);
            edit.commit();
            TextView highScore = (TextView) findViewById(R.id.textView3);
            highScore.setText("0");
        }

    }
    //Starts the game once the START button is pressed
    public void startGame(View view) {
        finish();
        Intent intentStart = new Intent(this, MainActivity.class);
        startActivity(intentStart);
    }


}
