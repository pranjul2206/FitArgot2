package in.ac.ksit.android.fitargot.Network;

import in.ac.ksit.android.fitargot.Network.Model.SignUpModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ArgotAPI {


    @GET("/api/UserRegistration/registerUser/")
    Call<SignUpModel> registerUser(@Query("email") String email, @Query("password") String password, @Query("fbid") String fbid, @Query("token") String token, @Query("weight") String weight, @Query("height") String height);


//    @GET("/api/UserRegistration/registerUser/")
//    Call<String> registerUser(@Query("email") String email, @Query("password") String password, @Query("fbid") String fbid, @Query("token") String token, @Query("weight") String weight, @Query("height") String height);

}
