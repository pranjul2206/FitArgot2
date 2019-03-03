package in.ac.ksit.android.fitargot.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import in.ac.ksit.android.fitargot.Adapters.GamePagerAdapter;
import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;
import in.ac.ksit.android.fitargot.Network.Model.PastEventModel;
import in.ac.ksit.android.fitargot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SportsActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public TabLayout.Tab create_event;
    public TabLayout.Tab search_event;
    public ViewPager gamePager;
    public ArgotAPI argotAPI;
    {
        argotAPI= ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.sports_tab_layout);

        argotAPI.getUsersEvent("124536").enqueue(new Callback<PastEventModel>() {
            @Override
            public void onResponse(Call<PastEventModel> call, Response<PastEventModel> response) {
                response.body().getResult();

            }

            @Override
            public void onFailure(Call<PastEventModel> call, Throwable t) {


            }
        });

        GamePagerAdapter adapter=new GamePagerAdapter(getSupportFragmentManager());
        gamePager = (ViewPager) findViewById(R.id.game_pager);

        gamePager.setAdapter(adapter);
        tabLayout.setupWithViewPager(gamePager);


    }

}
