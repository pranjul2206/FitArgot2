package in.ac.ksit.android.fitargot.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import in.ac.ksit.android.fitargot.R;
import in.ac.ksit.android.fitargot.Util.LoginUtil;

import static com.facebook.FacebookSdk.getApplicationContext;
import static in.ac.ksit.android.fitargot.Activity.LoginActivity.URL;


public class Login extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private View root;
    private CallbackManager manager;


    public Login() {
        // Required empty public constructor
    }

    private Button Login;
    private TextInputEditText name;
    private TextInputEditText password;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        manager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

        
        Toast.makeText(this.getActivity(), "Login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         root=inflater.inflate(R.layout.fragment_login, container, false);
         return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        name=root.findViewById(R.id.login_email);
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

                Log.d("FACEBOOK", loginResult.getAccessToken().getToken());

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
