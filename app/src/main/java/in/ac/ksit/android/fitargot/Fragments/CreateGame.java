package in.ac.ksit.android.fitargot.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import in.ac.ksit.android.fitargot.Activity.CreateSportActivity;
import in.ac.ksit.android.fitargot.Adapters.PastActivityAdapter;
import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;
import in.ac.ksit.android.fitargot.Network.Model.PastEventModel;
import in.ac.ksit.android.fitargot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateGame extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String TAG=CreateGame.class.getSimpleName();
    private ArgotAPI argotAPI=null;
    private View root;
    private Button createActivity;
    private  RecyclerView recyclerView;
    private PastActivityAdapter adapter;
    public CreateGame() {
        // Required empty public constructor
    }

    {
        argotAPI=ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateGame.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateGame newInstance(String param1, String param2) {
        CreateGame fragment = new CreateGame();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root=inflater.inflate(R.layout.fragment_create_game, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=root.findViewById(R.id.past_events);
        createActivity=root.findViewById(R.id.create_activity);


        adapter=new PastActivityAdapter(this.getActivity(),null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        SharedPreferences preferences= getActivity().getSharedPreferences("APP_DATA",Context.MODE_PRIVATE);
        if(preferences.getBoolean("Logged",false))
        {
            String uid=preferences.getString("UID",null);
            if(uid!=null)
            argotAPI.getUsersEvent(uid).enqueue(new Callback<PastEventModel>() {
                @Override
                public void onResponse(Call<PastEventModel> call, Response<PastEventModel> response) {

                    if(response.code()==200 ) {
                        Log.d(TAG,"success full response");
                        adapter.setData(response.body().getResult());

                    }
                }

                @Override
                public void onFailure(Call<PastEventModel> call, Throwable t) {

                    Log.d(TAG,"EROOR");

                }
            });

        }


        createActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),CreateSportActivity.class);
                startActivity(intent);

            }
        });

    }
}
