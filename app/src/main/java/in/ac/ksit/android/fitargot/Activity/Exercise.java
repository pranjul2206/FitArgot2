package in.ac.ksit.android.fitargot.Activity;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.RelativeLayout;

import com.wajahatkarim3.easyflipview.EasyFlipView;

import in.ac.ksit.android.fitargot.R;

public class Exercise extends AppCompatActivity {
    RelativeLayout r1,r2,r3,r4;
    EasyFlipView ef1,ef2,ef3,ef4;
    int f=1;
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        ef1 = (EasyFlipView) findViewById(R.id.flip1);
        ef2 = (EasyFlipView) findViewById(R.id.flip2);
        ef3 = (EasyFlipView) findViewById(R.id.flip3);
        ef4 = (EasyFlipView) findViewById(R.id.flip4);
        r1=(RelativeLayout)findViewById(R.id.layout1);
        r2=(RelativeLayout)findViewById(R.id.layout2);
        r3=(RelativeLayout)findViewById(R.id.layout3);
        r4=(RelativeLayout)findViewById(R.id.layout4);
        chronometer = findViewById(R.id.chronometer1);
        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime());
        ef1.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                r1.setVisibility(View.VISIBLE);
                if(f==1)
                {
                    startwatch();
                    f=2;
                }
                else{
                    stopwatch();
                    f=1;
                }

            }
        });
        //second
        ef2.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                ef2.setFlipEnabled(false);
                r2.setVisibility(View.VISIBLE);

            }
        });
        //third
        ef3.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                ef3.setFlipEnabled(false);
                r3.setVisibility(View.VISIBLE);

            }
        });
        //fourth
        ef4.setOnFlipListener(new EasyFlipView.OnFlipAnimationListener() {
            @Override
            public void onViewFlipCompleted(EasyFlipView flipView, EasyFlipView.FlipState newCurrentSide)
            {

                ef4.setFlipEnabled(false);
                r4.setVisibility(View.VISIBLE);

            }
        });
    }
    //stopwatch functions
    public void startwatch()
    {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }
    public void stopwatch()
    {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }
}
