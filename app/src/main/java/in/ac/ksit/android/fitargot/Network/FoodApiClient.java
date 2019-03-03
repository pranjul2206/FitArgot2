package in.ac.ksit.android.fitargot.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodApiClient {


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
