package in.ac.ksit.android.fitargot.Fragments;
import android.app.Notification;
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

import com.androidnetworking.AndroidNetworking;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ApiClient;
import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;
import in.ac.ksit.android.fitargot.Network.GoogleApis;
import in.ac.ksit.android.fitargot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class SignUp extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
String password;
String confirm_password;
String weight;
String height;
String age;
String email;
ArgotAPI argotAPI;
String token=null;
String fbid=null;
    {
        argotAPI= ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class);
    }

    private String TAG=SignUp.class.getSimpleName();
    private CallbackManager manager;
    private Button submit;
    private TextInputEditText mHeight;
    private TextInputEditText mWeight;
    private TextInputEditText mAge;
    private TextInputEditText mEmail;
    private TextInputEditText mPassword;
    private TextInputEditText mConfirm_password;

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
        manager.onActivityResult(requestCode,resultCode,data);
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

        submit=root.findViewById(R.id.submit);
        mEmail=root.findViewById(R.id.sign_up_email);
        mPassword=root.findViewById(R.id.password);
        mConfirm_password=root.findViewById(R.id.confirm_password);
        mWeight=root.findViewById(R.id.weight);
        mHeight=root.findViewById(R.id.height);
        mAge=root.findViewById(R.id.age);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"sending data to server");
                email=mEmail.getText().toString();
                password=mPassword.getText().toString();
                weight=mWeight.getText().toString();
                height=mHeight.getText().toString();
                age=mAge.getText().toString();
                Log.d(TAG,"sigining up"+email+password+weight+height+age);
                argotAPI.registerUser(email,password,fbid,token,weight,height).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                            Log.d(TAG,"response receivced");
                            if(response.code()==200){

                                Log.d(TAG,"response done"+call.request().body().toString());
                            }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d(TAG,"api error"+t.toString()+"url "+call.request().url());
                    }
                });


            }
        });
        manager = CallbackManager.Factory.create();


        LoginButton fbLogin = (LoginButton) root.findViewById(R.id.facebook_signup);
        //fbLogin.setReadPermissions(Arrays.asList(email));
        fbLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends"));
//
//        result = (TextView)findViewById(R.id.response);
//        frs = findViewById(R.id.friends);
        Log.d("SignUp","logging to facebook");
        fbLogin.registerCallback(manager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                token=loginResult.getAccessToken().getToken();
                fbid=loginResult.getAccessToken().getUserId();

                Log.d("SignUp","got token"+token);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("SignupFragment", response.toString());

                                // Application code
                                try {
                                    String email = object.getString("email");
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
                                    mEmail.setText(email);
                                    mPassword.setText("null");
                                    mAge.setText(String.valueOf(2019-Integer.valueOf(birthday.split("/")[2])));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "email,birthday");
                request.setParameters(parameters);
                request.executeAsync();


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
