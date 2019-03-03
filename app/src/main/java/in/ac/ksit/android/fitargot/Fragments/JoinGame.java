package in.ac.ksit.android.fitargot.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.ac.ksit.android.fitargot.Adapters.AllEventsAdapter;
import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;
import in.ac.ksit.android.fitargot.Network.GoogleApis;
import in.ac.ksit.android.fitargot.Network.Model.PastEventModel;
import in.ac.ksit.android.fitargot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class JoinGame extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    View root;
    String TAG=JoinGame.class.getSimpleName();
    private RecyclerView recyclerView;
    private AllEventsAdapter allEventsAdapter;

    ArgotAPI argotAPI;

    {
        argotAPI=ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class);
    }


    public JoinGame() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        allEventsAdapter =  new AllEventsAdapter(this.getActivity(),null);
        root=inflater.inflate(R.layout.fragment_join_game, container, false);
        recyclerView=root.findViewById(R.id.all_activites);
        recyclerView.setAdapter(allEventsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        Log.d(TAG,"getting all events");
        argotAPI.getAllEvent().enqueue(new Callback<PastEventModel>() {
            @Override
            public void onResponse(Call<PastEventModel> call, Response<PastEventModel> response) {
                if(response.code()==200){

                    allEventsAdapter.setData(response.body().getResult());

                }else {
                    Log.d(TAG,"Wrong response");
                }

            }

            @Override
            public void onFailure(Call<PastEventModel> call, Throwable t) {

                Log.d(TAG,"ERROR");
            }
        });

        return root;

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
