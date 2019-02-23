package in.ac.ksit.android.fitargot.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import in.ac.ksit.android.fitargot.R;

public class MainActivity extends AppCompatActivity {


    private DrawerLayout mDrawerLayout;


    private boolean isLoggedIn(){

        return true;
    }


    public void bind_view(){

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

    }


    public void bind_data(){


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isLoggedIn()){

            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);

        }else{



        }


    }
}
