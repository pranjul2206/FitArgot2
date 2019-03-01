package in.ac.ksit.android.fitargot.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import in.ac.ksit.android.fitargot.R;

public class redeem extends AppCompatActivity {
    ImageView i1,i2,i3,i4,i5;
    ScrollView s1,s2,s3,s4,s5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redeem);
        i1=(ImageView)findViewById(R.id.image1);
        i2=(ImageView)findViewById(R.id.image2);
        i3=(ImageView)findViewById(R.id.image3);
        i4=(ImageView)findViewById(R.id.image4);
        i5=(ImageView)findViewById(R.id.image5);
        s1=(ScrollView)findViewById(R.id.scroll1);
        s2=(ScrollView)findViewById(R.id.scroll2);
        s3=(ScrollView)findViewById(R.id.scroll3);
        s4=(ScrollView)findViewById(R.id.scroll4);
        s5=(ScrollView)findViewById(R.id.scroll5);
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(1);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(2);
            }
        });
        i3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(3);
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(4);
            }
        });
        i5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(5);
            }
        });

    }
    public void done(int i)
    {
        switch (i)
        {
            case 1: s1.setVisibility(View.VISIBLE);s2.setVisibility(View.INVISIBLE);s3.setVisibility(View.INVISIBLE);s4.setVisibility(View.INVISIBLE);s5.setVisibility(View.INVISIBLE);break;
            case 2: s1.setVisibility(View.INVISIBLE);s2.setVisibility(View.VISIBLE);s3.setVisibility(View.INVISIBLE);s4.setVisibility(View.INVISIBLE);s5.setVisibility(View.INVISIBLE);break;
            case 3: s1.setVisibility(View.INVISIBLE);s2.setVisibility(View.INVISIBLE);s3.setVisibility(View.VISIBLE);s4.setVisibility(View.INVISIBLE);s5.setVisibility(View.INVISIBLE);break;
            case 4: s1.setVisibility(View.INVISIBLE);s2.setVisibility(View.INVISIBLE);s3.setVisibility(View.INVISIBLE);s4.setVisibility(View.VISIBLE);s5.setVisibility(View.INVISIBLE);break;
            case 5: s1.setVisibility(View.INVISIBLE);s2.setVisibility(View.INVISIBLE);s3.setVisibility(View.INVISIBLE);s4.setVisibility(View.INVISIBLE);s5.setVisibility(View.VISIBLE);break;
        }
    }
}
