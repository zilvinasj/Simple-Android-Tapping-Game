package sogoh.com.zilvinasj.naujas;

import android.animation.Animator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animator
        .AnimatorListener {
    int score = 0;
    private String TAG = GameOver.class.getSimpleName();

    Random random = new Random();
    ImageButton imageButton;
    int maxX;
    int maxY;
    int lives = 3;
    String score1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.lives);
        textView.setText(String.valueOf(lives));
        imageButton = (ImageButton) findViewById(R.id.imageButton1);
        // When the button is pressed the score is incremented
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                TextView textView = (TextView) findViewById(R.id.score);
                textView.setText(String.valueOf(score));
            }
        });
        ConstraintLayout layout=(ConstraintLayout) findViewById(R.id.wtf);
        layout.setOnClickListener(new View.OnClickListener() {
        //Checks if there are lives left, if not the game ends
            @Override
            public void onClick(View v){
                if(lives>1) {
                    lives--;
                    TextView textView = (TextView) findViewById(R.id.lives);
                    textView.setText(String.valueOf(lives));
                }
                else{
                    TextView textView = (TextView) findViewById(R.id.lives);
                    textView.setText("0");
                    try {
                        endgame();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        //main thread
        imageButton.post(new Runnable() {
            @Override
            public void run() {
                maxX = imageButton.getRootView()
                        .getRight() - imageButton.getWidth();
                maxY = imageButton.getRootView()
                        .getBottom() - imageButton.getHeight();

                animateButton();
            }
        });
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        animateButton();
    }

    private void animateButton() {
        imageButton.animate()
                .x(random.nextInt(maxX))
                .y(random.nextInt(maxY))
                .setDuration(1000)
                .setListener(this);
    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
    //ends the game and moves to another screen, passing the score
    public void endgame() throws InterruptedException {

        Intent intentStart = new Intent(this, GameOver.class);
        Log.e(TAG, String.valueOf(score));
        finish();
        intentStart.putExtra("Score",score);
        startActivity(intentStart);
    }
}