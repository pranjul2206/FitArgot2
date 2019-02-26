package in.ac.ksit.android.fitargot.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;
import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ApiClient;
import in.ac.ksit.android.fitargot.Network.GoogleApis;
import in.ac.ksit.android.fitargot.Network.Model.PlaceModel;
import in.ac.ksit.android.fitargot.Network.Model.Result;
import in.ac.ksit.android.fitargot.R;
import in.ac.ksit.android.fitargot.Util.PermissionUtil;
import me.itangqi.waveloadingview.WaveLoadingView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import technolifestyle.com.imageslider.FlipperLayout;
import technolifestyle.com.imageslider.FlipperView;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener,View.OnClickListener {
    static ArrayList<String> permissions=new ArrayList<String>();
    private DrawerLayout mDrawerLayout;
    private NavigationView navigationView;
    private SupportMapFragment mapFragment;
    private GoogleMap  gMap;
    private PermissionUtil permissionUtil;
    private GoogleApiClient googleApiClient;
    int i=0;
    private static final  String TAG=MainActivity.class.getSimpleName();
    GoogleApis googleApis=null;
    //declaration of variables pranjul1

    WaveLoadingView waveLoadingView2,waveLoadingView3,waveLoadingView4,waveLoadingGraph;
    TextView t;
    RelativeLayout r,r1,r2,r3;
    ImageView step,calories,caloriesin,improvement,SelectedPic;
    String s[]={"steps","calories","something","improvements"};
    FlipperLayout flipper;
    CardView card2;

    {
        googleApis=ApiClient.getClient(Constants.PLACE_BASE_PATH).create(GoogleApis.class);
    }
    //declaration of variables pranjul1
    private boolean isLoggedIn(){

        return true;
    }

    private void init_objects(){

    }
    public void bind_view(){

        mDrawerLayout=findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        mapFragment= (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //declaration of variables pranjul 1

        r=(RelativeLayout)findViewById(R.id.option1bubble1);
        r1=(RelativeLayout)findViewById(R.id.option2bubble2);
        r2=(RelativeLayout)findViewById(R.id.option3bubble3);
        r3=(RelativeLayout)findViewById(R.id.option4bubble4);
        r.setVisibility(View.VISIBLE);
        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        r3.setVisibility(View.INVISIBLE);
        step=(ImageView)findViewById(R.id.stepsbutton);
        calories=(ImageView)findViewById(R.id.caloriesbutton);
        caloriesin=(ImageView)findViewById(R.id.somethingbutton);
        improvement=(ImageView)findViewById(R.id.improvementsbutton);
        t=(TextView)findViewById(R.id.leftover);
        waveLoadingView2=(WaveLoadingView)findViewById(R.id.waveLoadingView2);
        waveLoadingView3=(WaveLoadingView)findViewById(R.id.waveLoadingView3);
        waveLoadingView4=(WaveLoadingView)findViewById(R.id.waveLoadingView4);
        waveLoadingGraph=(WaveLoadingView)findViewById(R.id.waveLoadingView4);
        //--------SLIDE IMAGE
        flipper=(FlipperLayout)findViewById(R.id.flipper);
        setLayout();
        //--------SLIDE IMAGE

        //declaration of variables pranjul 1


    }


    public void bind_action(){


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

        mapFragment.getMapAsync(this);
        googleApiClient = new GoogleApiClient.Builder(this).
                addApi(LocationServices.API).
                addConnectionCallbacks(this).
                addOnConnectionFailedListener(this).build();


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);

        permissionUtil=PermissionUtil.getInstance(getApplicationContext());
        ArrayList<String> permissionToRequest=permissionUtil.permissionsToRequest(permissions);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(permissionToRequest.size() > 0)
                requestPermissions(permissionToRequest.toArray(new String[permissionToRequest.size()]),100);

        }

        if(!isLoggedIn()){



        }else{

                bind_view();
                bind_action();
        }

        //java Oncreate code code pranjul 1
        PieView animatedPie = (PieView) findViewById(R.id.animated_pie_view_1);
        card2=(CardView)findViewById(R.id.card2);

        PieAngleAnimation animation = new PieAngleAnimation(animatedPie);
        animation.setDuration(1000); //This is the duration of the animation in millis
        animatedPie.startAnimation(animation);
        waveLoadingView2.setProgressValue(90);
        waveLoadingView2.setBottomTitle("");
        waveLoadingView2.setCenterTitle(String.format("%d%%",90));
        waveLoadingView2.setTopTitle("");

        step.setOnClickListener(this);
        caloriesin.setOnClickListener(this);
        calories.setOnClickListener(this);
        improvement.setOnClickListener(this);
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Exercise();
            }
        });
        //java Oncreate code code pranjul 1
        waveLoadingGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openStepGraph();
            }
        });


    }
    private void openStepGraph()
    {
        Intent intent=new Intent(this,StepsGraphs.class);
        startActivity(intent);
    }
    private void Exercise()
    {
        Intent intent=new Intent(this,Exercise.class);
        startActivity(intent);
    }
    private void setLayout()
    {
        String url[]=new String[]{
                "https://cdn-images-1.medium.com/max/1600/1*6Ak3LPymYWuXKrhPpa1SAA.png",
                "https://bjsm.bmj.com/content/bjsports/early/2017/09/04/bjsports-2017-097625/F1.large.jpg",
                "https://i.ytimg.com/vi/SKYtrKQ0-Qs/maxresdefault.jpg"
        };
        for( i=0;i<3;i++)
        {
            FlipperView view =new FlipperView(getBaseContext());
            view.setImageUrl(url[i]);
            flipper.addFlipperView(view);
            view.setOnFlipperClickListener(new FlipperView.OnFlipperClickListener() {
                @Override
                public void onFlipperClick(FlipperView flipperView) {
                    t.setText("image" + i);
                }
            });
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap=googleMap;
        gMap.getUiSettings().setCompassEnabled(false);
        gMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        try {
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style));

        }catch (Resources.NotFoundException e){

            Log.e("ERRIR","Resource not found");
        }

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Permissions ok, we get last location
        FusedLocationProviderClient locationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null)
                {
                    Log.d(MainActivity.class.getSimpleName(),"lattitude "+location.getLatitude());
                    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                    gMap.addMarker(new MarkerOptions().position(latLng).title("CurrentLocation"));

                    final CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(),location.getLongitude()))
                            .zoom(17)
                            .tilt(67.5f)
                            .build();
//                    gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,
//                            10));


                    if(notInKnownLocation()) {
                        googleApis.getNearbyPlaces(location.getLatitude() + "," + location.getLongitude(), 2000, "park", "AIzaSyB4KfQQbmSWuwpPgOh3Y5-AKE_uUQAZkZk").enqueue(new Callback<PlaceModel>() {
                            @Override
                            public void onResponse(Call<PlaceModel> call, Response<PlaceModel> response) {
                                Log.d(TAG, "number of response" + response.body().getResults().size());

                                int count = 0;

                                for (Result res : response.body().getResults()) {
                                    if (count > 10)
                                        break;
                                    count++;

                                    in.ac.ksit.android.fitargot.Network.Model.Location location1 = res.getGeometry().getLocation();
                                    LatLng latLng1 = new LatLng(location1.getLat(), location1.getLng());
                                    gMap.addMarker(new MarkerOptions().position(latLng1)
                                            .title(res.getName()))
                                            .setIcon(BitmapDescriptorFactory.fromResource(R.drawable.park));
                                    Log.d(TAG, "Park name" + res.getName());


                                }
                                gMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                            }

                            @Override
                            public void onFailure(Call<PlaceModel> call, Throwable t) {

                                Log.e(TAG, "PLACE API FAILURE" + t.toString());
                                Log.e(TAG, "eRROR" + call.request().url());
                            }
                        });
                    }
                    else{

                    }

                }
            }
        });


    }

    private boolean notInKnownLocation() {
    return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.stepsbutton:
                step.setImageResource(R.drawable.xsteps);
                PieView animatedPie = (PieView) findViewById(R.id.animated_pie_view_1);

                PieAngleAnimation animation = new PieAngleAnimation(animatedPie);
                animation.setDuration(1000); //This is the duration of the animation in millis
                animatedPie.startAnimation(animation);
                t.setText("52 more steps");
                r.setVisibility(View.VISIBLE);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.INVISIBLE);
                break;
            case R.id.caloriesbutton : calories.setImageResource(R.drawable.xcalories);
                t.setText("114 more calories");
                r.setVisibility(View.INVISIBLE);
                r1.setVisibility(View.VISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.INVISIBLE);
                waveLoadingView2.setProgressValue(40);
                waveLoadingView2.setBottomTitle("");
                waveLoadingView2.setCenterTitle(String.format("%d%%",40));
                waveLoadingView2.setTopTitle("");
                break;
            case R.id.somethingbutton :
                caloriesin.setImageResource(R.drawable.xsomething);
                t.setText("223 more");
                r.setVisibility(View.INVISIBLE);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.VISIBLE);
                r3.setVisibility(View.INVISIBLE);
                waveLoadingView3.setProgressValue(52);
                waveLoadingView3.setBottomTitle("");
                waveLoadingView3.setCenterTitle(String.format("%d%%",52));
                waveLoadingView3.setTopTitle("");
                break;
            case R.id.improvementsbutton :
                improvement.setImageResource(R.drawable.ximprovements);
                t.setText("DEFEAT PRANJUL");
                r.setVisibility(View.INVISIBLE);
                r1.setVisibility(View.INVISIBLE);
                r2.setVisibility(View.INVISIBLE);
                r3.setVisibility(View.VISIBLE);
                waveLoadingView4.setProgressValue(12);
                waveLoadingView4.setBottomTitle("");
                waveLoadingView4.setCenterTitle(String.format("%d",12));
                waveLoadingView4.setTopTitle("");
                break;


        }
    }
    //code------------

    @Override
    protected void onStart() {
        super.onStart();

        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

        if (googleApiClient != null  &&  googleApiClient.isConnected()) {
            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {



    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
