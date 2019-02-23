package in.ac.ksit.android.fitargot.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.ac.ksit.android.fitargot.R;

public class MainActivity extends AppCompatActivity {


    private boolean isLoggedIn(){

        return true;
    }


    public void bind_view(){



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
