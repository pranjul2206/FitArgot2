package in.ac.ksit.android.fitargot.Fragments;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import in.ac.ksit.android.fitargot.R;

import static android.content.Context.SENSOR_SERVICE;
import static android.support.v4.content.ContextCompat.getSystemService;


public class SelfChallenge extends Fragment {
    ImageView  c1i2,  c2i2,  c3i2,  c4i2;
    RelativeLayout r1, r2, r3, r4;
    TextView t1,t2,t3,t4,c1;
    int i = 0,leftover=0;
    Float blabla;
    int caloriarray[][]={{55,64,73,82},{33,38,44,49}};

    private boolean running;
    private Chronometer chronometer1;
    private long pauseOffset;
    //sensors
    SensorManager stepm;
    SensorManager lightm;
    Sensor step;
    Sensor light;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_self_challenge, container, false);
        c1i2 = (ImageView) v.findViewById(R.id.card1image2);
        c2i2 = (ImageView) v.findViewById(R.id.card2image2);
        c3i2 = (ImageView) v.findViewById(R.id.card3image2);
        r1 = (RelativeLayout) v.findViewById(R.id.layout1);
        r2 = (RelativeLayout) v.findViewById(R.id.layout2);
        r3 = (RelativeLayout) v.findViewById(R.id.layout3);
        t1=(TextView)v.findViewById(R.id.total1);
        t2=(TextView)v.findViewById(R.id.total2);
        t3=(TextView)v.findViewById(R.id.total3);
        t4=(TextView)v.findViewById(R.id.total4);
        c1=(TextView)v.findViewById(R.id.calories1);
        //snsors
        stepm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

        lightm = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        light = lightm.getDefaultSensor(Sensor.TYPE_LIGHT);
        step = stepm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        stepm.registerListener(listener_light, light, SensorManager.SENSOR_DELAY_FASTEST);

        //chronometer1;
        chronometer1 = v.findViewById(R.id.chronometer1);
        chronometer1.setFormat("%s");
        chronometer1.setBase(SystemClock.elapsedRealtime());
        c1i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.template);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c1i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    c1i2.setImageBitmap(bitMap);
                    r1.setVisibility(View.VISIBLE);
                    startwatch();
                    i = 1;
                } else {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.skipping);
                    c1i2.setImageBitmap(bitMap);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c1i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    stopwatch();
                    i = 0;
                }

            }
        });
        //flip 2
        c2i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.template);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c2i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    c2i2.setImageBitmap(bitMap);
                    r2.setVisibility(View.VISIBLE);
                    startwatch();
                    i = 1;
                } else {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.run);
                    c2i2.setImageBitmap(bitMap);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c2i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    stopwatch();
                    i = 0;
                }

            }
        });
        c3i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (i == 0) {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.template);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c3i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    c3i2.setImageBitmap(bitMap);
                    r3.setVisibility(View.VISIBLE);
                    i = 1;
                } else {
                    Bitmap bitMap = BitmapFactory.decodeResource(SelfChallenge.this.getResources(), R.drawable.aerobics);
                    c1i2.setImageBitmap(bitMap);
                    ObjectAnimator flip = ObjectAnimator.ofFloat(c3i2, "rotationY", 0f, 180f);
                    flip.setDuration(300);
                    flip.start();
                    i = 0;
                }

            }
        });
        return v;

    }
    SensorEventListener listener_light = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {

            t2.setText(event.values[0]+"");
            c1.setText(((caloriarray[1][1]/1000)*event.values[0])+"");
            if(event.values[0] <= 1.0f) {
                if(step == null);
                else stepm.registerListener(listner_step, step, SensorManager.SENSOR_DELAY_FASTEST);
            }else if(event.values[0] >= 5.0f) {
                stepm.unregisterListener(listner_step);
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };

    SensorEventListener listner_step = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(leftover==0)
            {
                blabla= event.values[0];
                leftover=1;
            }
            else {
                t1.setText((event.values[0]-blabla) + "");
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    public void startwatch()
    {
        if (!running) {
            chronometer1.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer1.start();
            running = true;
        }
    }
    public void stopwatch()
    {
        if (running) {
            chronometer1.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer1.getBase();
            running = false;
        }
    }
}





