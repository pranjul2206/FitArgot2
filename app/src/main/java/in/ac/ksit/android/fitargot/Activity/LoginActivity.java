package in.ac.ksit.android.fitargot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;

import in.ac.ksit.android.fitargot.Fragments.Login;
import in.ac.ksit.android.fitargot.Fragments.SignUp;
import in.ac.ksit.android.fitargot.R;
import in.ac.ksit.android.fitargot.Util.LoginUtil;

public class LoginActivity extends AppCompatActivity {

    CallbackManager manager;
    public static  final String URL = "https://graph.facebook.com/{user_id}/friends";
    private static String email = "email";
    LoginButton fbLogin;
    TextView result;

    TextView frs;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if(fragment instanceof Login)
            loginFragment.onActivityResult(requestCode,resultCode,data);
        else
            signupFragment.onActivityResult(requestCode,resultCode,data);
    }


    TabLayout authTab;
    TabLayout.Tab login;
    TabLayout.Tab signup;
    Toolbar toolbar;
    Fragment loginFragment;
    Fragment signupFragment;

    private void init_ui(){
        toolbar=findViewById(R.id.login_toolbar);
        toolbar.setTitle("FitArgot");
        setSupportActionBar(toolbar);

        authTab=(TabLayout)findViewById(R.id.auth_tab);
        loginFragment=new Login();
        signupFragment=new SignUp();

        login=authTab.newTab();
        login.setText("Login");

        replcaeFragment(0);
        Log.d("LOgin ACTIVITY","loading login");
        signup=authTab.newTab();
        signup.setText("Sign Up");
        authTab.addTab(login);
        authTab.addTab(signup);
//        FragmentManager manager=getSupportFragmentManager();
//
//        FragmentTransaction ft=manager.beginTransaction();
//        ft.add(R.id.frame_container,loginFragment);
//        ft.commit();

        authTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (authTab.getSelectedTabPosition()) {
                    case 0:
                        replcaeFragment(0);
                        break;
                    case 1:
                        replcaeFragment(1);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    private void replcaeFragment(int i) {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction ft=manager.beginTransaction();

        switch (i){
            case 0:
                ft.replace(R.id.frame_container, loginFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                break;
            case 1:
                ft.replace(R.id.frame_container, signupFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

                break;
        }
        ft.commit();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init_ui();



    }




}
