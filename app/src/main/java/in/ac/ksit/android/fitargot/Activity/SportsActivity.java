package in.ac.ksit.android.fitargot.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.ac.ksit.android.fitargot.Adapters.GamePagerAdapter;
import in.ac.ksit.android.fitargot.R;

public class SportsActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public TabLayout.Tab create_event;
    public TabLayout.Tab search_event;
    public ViewPager gamePager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.sports_tab_layout);


        GamePagerAdapter adapter=new GamePagerAdapter(getSupportFragmentManager());
        ViewPager gamePager = (ViewPager) findViewById(R.id.game_pager);

        gamePager.setAdapter(adapter);
        tabLayout.setupWithViewPager(gamePager);

    }

}
