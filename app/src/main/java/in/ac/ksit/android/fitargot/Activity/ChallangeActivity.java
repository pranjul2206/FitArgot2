package in.ac.ksit.android.fitargot.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.ac.ksit.android.fitargot.Adapters.ChallengeAdapter;
import in.ac.ksit.android.fitargot.R;

public class ChallangeActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challange);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.challange_tab);
        mViewPager=findViewById(R.id.challange_pager);
        ChallengeAdapter adapter=new ChallengeAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(mViewPager);
        FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent launchIntent=getPackageManager().getLaunchIntentForPackage("com.dvzczx.cascac");
                Intent intent=new Intent();
                intent.setComponent(new ComponentName("com.sih.pronlwf","com.unity3d.player.UnityPlayerActivity"));
                startActivity(intent);
            }
        });



    }

}