package in.ac.ksit.android.fitargot.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ArgotAPI {

    @FormUrlEncoded
    @POST("/api/UserRegistration/registerUser/")
    Call<String> registerUser(@Field("email") String email,@Field("password") String password,@Field("fbid") String fbid,@Field("token") String token,@Field("weight") String weight,@Field("height") String height);

}
