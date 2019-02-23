package in.ac.ksit.android.fitargot.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
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
        manager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        manager = CallbackManager.Factory.create();

        AndroidNetworking.initialize(getApplicationContext());

        fbLogin = (LoginButton)findViewById(R.id.login_button);
        //fbLogin.setReadPermissions(Arrays.asList(email));
        fbLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));

        result = (TextView)findViewById(R.id.response);
        frs = findViewById(R.id.friends);

        fbLogin.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                String profileID = Profile.getCurrentProfile().getId();

                String accessToken = loginResult.getAccessToken().getToken();


                AndroidNetworking.get(URL).addPathParameter("user_id", profileID).addQueryParameter("access_token", accessToken)
                        .build().getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            StringBuffer buffer = new StringBuffer();
                            JSONArray array = response.getJSONArray("data");
                            for(int i = 0; i < array.length(); i++) {
                                JSONObject object = array.getJSONObject(i);

                                String json = object.toString();

                                LoginUtil friend = new Gson().fromJson(json, LoginUtil.class);

                                buffer.append("Name : "+friend.id+"\n"+friend.name+"\n");

                                buffer.append("\n---------\n");


                            }

                            updateList(buffer);


                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });


            }

            @Override
            public void onCancel() {
                result.setText("User cancelled login");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("FACEBOOK", error.toString());
            }

            

        });

    }


    void updateList(StringBuffer buffer) {
        frs.setText(buffer.toString());
    }


}
