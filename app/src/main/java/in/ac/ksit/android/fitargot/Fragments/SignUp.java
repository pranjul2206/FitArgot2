package in.ac.ksit.android.fitargot.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.Arrays;

import in.ac.ksit.android.fitargot.R;

import static com.facebook.FacebookSdk.getApplicationContext;


public class SignUp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    private CallbackManager manager;

    public SignUp() {
        // Required empty public constructor
    }


    View root;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_sign_up, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        manager = CallbackManager.Factory.create();

        AndroidNetworking.initialize(getApplicationContext());

        LoginButton fbLogin = (LoginButton) root.findViewById(R.id.login_button);
        //fbLogin.setReadPermissions(Arrays.asList(email));
        fbLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
//
//        result = (TextView)findViewById(R.id.response);
//        frs = findViewById(R.id.friends);

        fbLogin.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                loginResult.getAccessToken().getUserId();



            }

            @Override
            public void onCancel()
            {

            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FACEBOOK", error.toString());
            }



        });
    }
}
