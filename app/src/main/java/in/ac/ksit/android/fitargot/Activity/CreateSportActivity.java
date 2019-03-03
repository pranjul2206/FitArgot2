package in.ac.ksit.android.fitargot.Activity;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.Date;

import in.ac.ksit.android.fitargot.Constants;
import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;
import in.ac.ksit.android.fitargot.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateSportActivity extends AppCompatActivity implements View.OnClickListener{

    String TAG=CreateSportActivity.class.getSimpleName();
    final Calendar myCalendar = Calendar.getInstance();

    Button date;
    Button time;
    DatePicker datePicker;
    SearchableSpinner searchableSpinner;
    Button createActivity;
    TextInputEditText address=null;
    TextInputEditText num_of_player;
    String dateString;
    String timeString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sport);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


//        final DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), isVibrate());
//        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY) ,calendar.get(Calendar.MINUTE), false, false);

//         Initialize Places.
        date=findViewById(R.id.date);
        time=findViewById(R.id.time);
        address=findViewById(R.id.address);
        num_of_player=findViewById(R.id.number_of_people);
        createActivity=findViewById(R.id.create_activity);
        createActivity.setOnClickListener(this);
        date.setOnClickListener(this);
        time.setOnClickListener(this);
        searchableSpinner=findViewById(R.id.game_searcher);
        searchableSpinner.setTitle("Select Item");
        searchableSpinner.setPositiveButton("OK");
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.sports_arrays, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        searchableSpinner.setAdapter(adapter);

//        Places.initialize(getApplicationContext(), "AIzaSyCFtniv6fLKPD-Jz4DP6oKvz0g8MSsIzdY");
//
//// Create a new Places client instance.
//        PlacesClient placesClient = Places.createClient(this);
//
//
//        // Initialize the AutocompleteSupportFragment.
//        AutocompleteSupportFragment autocompleteFragment = (AutocompleteSupportFragment)
//                getSupportFragmentManager().findFragmentById(R.id.autocomplete_fragment);
//
//        autocompleteFragment.setCountry("india");
//        autocompleteFragment.setHint("Trichy India");
//// Specify the types of place data to return.
//        autocompleteFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS));
//
//// Set up a PlaceSelectionListener to handle the response.
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//                // TODO: Get info about the selected place.
//                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//            }
//
//
//
//            @Override
//            public void onError(Status status) {
//                // TODO: Handle the error.
//                Log.i(TAG, "An error occurred: " + status);
//            }
//        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.date:
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                           dateString=String.valueOf(year)+"/"+String.valueOf(monthOfYear)+"/"+String.valueOf(dayOfMonth);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;
            case R.id.time:


                final Calendar cal = Calendar.getInstance();
                int mHour = cal.get(Calendar.HOUR_OF_DAY);
                int mMinute = cal.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                timeString=hourOfDay+":"+minute;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

                break;

            case R.id.create_activity:
                Log.d(TAG,"CLICKED");
                ArgotAPI api=ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class);
                SharedPreferences preferance=getApplicationContext().getSharedPreferences("APP_DATA",MODE_PRIVATE);
                if(preferance.getBoolean("Logged",false))
                {
                    String uid=preferance.getString("UID",null);
                    Log.d(TAG,"uid is "+uid);
                    if(uid!=null)
                    {
                        String level= (String) searchableSpinner.getSelectedItem();
                        String addressString="";
                        if(address!=null)
                            addressString=address.getText().toString();

                        Log.d(TAG,"creating activity");
                        api.addEvent(uid,dateString,timeString,timeString,level,num_of_player.getText().toString(),addressString,(String)searchableSpinner.getSelectedItem()).enqueue(new Callback<in.ac.ksit.android.fitargot.Network.Model.Response>() {
                            @Override
                            public void onResponse(Call<in.ac.ksit.android.fitargot.Network.Model.Response> call, Response<in.ac.ksit.android.fitargot.Network.Model.Response> response) {
                                if(response.code()==200)
                                    onBackPressed();
                                else
                                    Toast.makeText(getApplicationContext(),"Event can not be created",Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onFailure(Call<in.ac.ksit.android.fitargot.Network.Model.Response> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Event can not be created",Toast.LENGTH_LONG).show();
                                Log.d(TAG,"creating activity failed"+t.toString());


                            }
                        });
                    }
                }

                break;
        }
    }
}
