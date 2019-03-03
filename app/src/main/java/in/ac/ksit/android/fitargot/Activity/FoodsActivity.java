package in.ac.ksit.android.fitargot.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import in.ac.ksit.android.fitargot.Adapters.DietPagerAdapter;
import in.ac.ksit.android.fitargot.Adapters.HorAdapter;
import in.ac.ksit.android.fitargot.Network.FoodApiClient;
import in.ac.ksit.android.fitargot.Network.FoodApis;
import in.ac.ksit.android.fitargot.Network.Model.DietData;
import in.ac.ksit.android.fitargot.Network.Model.FoodModel;
import in.ac.ksit.android.fitargot.R;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodsActivity extends AppCompatActivity {


    ImageView camera;
    SearchableSpinner searchableSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foods);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        List<DietData> diets = new ArrayList<DietData>();
        diets.add(new DietData("Lunch", "Whole grain Roti With Mixed Vegitable curry", "Multi Grain Chapati With Mixed Vegitable curry"));

        diets.add(new DietData("Dinner", "Tofu cuury with mixed vegitables and fresh spinach salad", "Crockpot Chipotle Chicken Soup"));

        diets.add(new DietData("Breakfast", "Sambar with brown rice and idli", "Fruit bowl with with sprouts"));
        HorAdapter adapter=new HorAdapter(diets);
        RecyclerView recyclerView=findViewById(R.id.diet_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setAdapter(adapter);

        searchableSpinner=findViewById(R.id.food_searcher);
        searchableSpinner.setTitle("Select Item");
        searchableSpinner.setPositiveButton("OK");
        ArrayAdapter<CharSequence> fadapter = ArrayAdapter.createFromResource(this,
                R.array.food_arrays, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        fadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        searchableSpinner.setAdapter(fadapter);


        camera = findViewById(R.id.take_snap);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CAMERA},
                            100);
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 101);
                }
            }
        });


    }
        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
        {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 100) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                    Intent cameraIntent = new
                            Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 101);
                } else {
                    Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
                }


            }
        }


        private File saveImage(Bitmap mBitmap){


            File f3=new File(Environment.getExternalStorageDirectory()+"/inpaint/");
            if(!f3.exists())
                f3.mkdirs();
            OutputStream outStream = null;
            File file = new File(Environment.getExternalStorageDirectory() + "/inpaint/"+"seconds"+".png");
            try {
                outStream = new FileOutputStream(file);
                mBitmap.compress(Bitmap.CompressFormat.PNG, 85, outStream);
                outStream.close();

                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();

                return file;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
            @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                Log.d("FOOD ACTIVITY","detecting food");
                if (requestCode == 101 && resultCode == Activity.RESULT_OK) {
                    Bitmap photo = (Bitmap) data.getExtras().get("data");
                    camera.setImageBitmap(photo);

                    ;

//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    photo.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                    byte[] byteArray = byteArrayOutputStream .toByteArray();
//                    String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
                    File file=saveImage(photo);

                    if(file!=null) {
                        RequestBody fileReqBody = RequestBody.create(MediaType.parse("image/*"), file);

                        MultipartBody.Part part = MultipartBody.Part.createFormData("upload", file.getName(), fileReqBody);
                        RequestBody description = RequestBody.create(MediaType.parse("text/plain"), "num_tag");
                        RequestBody api_key = RequestBody.create(MediaType.parse("text/plain"), "bcfdfc16d0224d394233b572d1c4ba28301dcb8e");


                        FoodApiClient.getClient("http://api.foodai.org/v1/").create(FoodApis.class).uploadPush(part, description, api_key).enqueue(new Callback<FoodModel>() {
                            @Override
                            public void onResponse(Call<FoodModel> call, Response<FoodModel> response) {
                                if (response.code() == 200) {


                                    Log.d("Foods", response.body().getFoodResults().get(0).get(0));

                                } else {
                                    try {
                                        Log.d("fOODS", response.errorBody().string());
                                    } catch (Exception e) {

                                    }

                                    Toast.makeText(getApplicationContext(), "unable to recognize food server error", Toast.LENGTH_LONG).show();

                                }
                            }

                            @Override
                            public void onFailure(Call<FoodModel> call, Throwable t) {
                                Log.d("FoodSActivity", "error " + t.toString());
                                Toast.makeText(getApplicationContext(), "unable to recognize food", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }



}
