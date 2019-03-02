package in.ac.ksit.android.fitargot.Activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import in.ac.ksit.android.fitargot.R;

public class SportsActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public TabLayout.Tab create_event;
    public TabLayout.Tab search_event;
    public ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.sports_tab_layout);
//        create_event=new TabLayout.Tab();
//        search_event=new TabLayout.Tab();
//        create_event.setText("Create Event");
//        search_event.setText("Join Game");

        ViewPager viewPager = (ViewPager) findViewById(R.id.game_pager);

        viewPager.setAdapter();
    }

}
