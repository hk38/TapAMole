package android.lifeistech.tapamole;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TextView scoreTxt;
    TextView timeTxt;
    int[] imgRes = {R.id.imageView0, R.id.imageView1, R.id.imageView2, R.id.imageView3, R.id.imageView4, R.id.imageView5, R.id.imageView6, R.id.imageView7, R.id.imageView8};

    Mole[] moles;

    int time;
    int score;

    Timer timer;
    TimerTask timerTask;
    Handler h;
    Random rand = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTxt = findViewById(R.id.scoreTxt);
        timeTxt = findViewById(R.id.timeTxt);

        moles = new Mole[9];
        for(int i = 0; i < moles.length; i++){
            ImageView iv = findViewById(imgRes[i]);
            moles[i] = new Mole(iv);
        }

        h = new Handler();
    }

    public void start(View v){
        time = 60;
        score = 0;
        timeTxt.setText(String.valueOf(time));
        scoreTxt.setText(String.valueOf(score));

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        int r = rand.nextInt(9);
                        moles[r].start();

                        time -= 1;
                        timeTxt.setText(String.valueOf(time));
                        if(time <= 0){
                            timer.cancel();
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 1000);
    }

    public void tapMole(View v){
        int tag = Integer.valueOf((String) v.getTag());
        score += moles[tag].tapMole();
        scoreTxt.setText(String.valueOf(score));
    }
}
