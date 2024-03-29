package in.ac.ksit.android.fitargot.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {


    //public static final String BASE_URL = "http://192.168.0.102:8000/tracker/";

    private static Retrofit retrofit = null;


    public static Retrofit getClient(String BASE_URL) {
        if (retrofit==null) {

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
