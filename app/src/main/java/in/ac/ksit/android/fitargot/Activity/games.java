package in.ac.ksit.android.fitargot.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import in.ac.ksit.android.fitargot.R;

public class games extends AppCompatActivity {
    CardView c1,c2,c3,c4,c5,c6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);
        c1=(CardView)findViewById(R.id.card1);
        c2=(CardView)findViewById(R.id.card2);
        c3=(CardView)findViewById(R.id.card3);
        c4=(CardView)findViewById(R.id.card4);
        c5=(CardView)findViewById(R.id.card5);
        c6=(CardView)findViewById(R.id.card6);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(1);
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(2);
            }
        });
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(3);
            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(4);
            }
        });
        c5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(5);
            }
        });
        c6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                done(6);
            }
        });
    }
    public void done(int n)
    {
        Intent intent;
        switch (n)
        {
            case 1:
                 intent=new Intent(this,gamesDaily.class);
                startActivity(intent); break;
            case 2: intent=new Intent(this,nearbyChallanges.class);
                startActivity(intent);  break;
            case 3: intent=new Intent(this,createChallange.class);
                startActivity(intent);  break;
            case 4: intent=new Intent(this,pokemongo.class);
                startActivity(intent);  break;
            case 5: intent=new Intent(this,selfChallange.class);
                startActivity(intent);  break;
            case 6: intent=new Intent(this,gameArgot.class);
                startActivity(intent);  break;
        }
    }
}
