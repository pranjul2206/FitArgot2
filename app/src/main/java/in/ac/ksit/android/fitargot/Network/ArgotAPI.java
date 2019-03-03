package in.ac.ksit.android.fitargot.Network;

import in.ac.ksit.android.fitargot.Network.Model.PastEventModel;
import in.ac.ksit.android.fitargot.Network.Model.Response;
import in.ac.ksit.android.fitargot.Network.Model.SignUpModel;
import in.ac.ksit.android.fitargot.Network.Model.SocialChallengeStatus;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ArgotAPI {


    @GET("/api/UserRegistration/registerUser/")
    Call<SignUpModel> registerUser(@Query("email") String email, @Query("password") String password, @Query("fbid") String fbid, @Query("token") String token, @Query("weight") String weight, @Query("height") String height);


    @GET("/api/Sports/getUserGames/")
    Call<PastEventModel> getUsersEvent(@Query("id") String user_id);

    @GET("/api/Sports/getAllGames/")
    Call<PastEventModel> getAllEvent();

    @GET("/api/Sports/addEvent/")
    Call<Response> addEvent(@Query("uid") String uid, @Query("pdate") String date, @Query("stime") String time, @Query("etime") String etime, @Query("level") String level, @Query("participants") String nop, @Query("address") String address, @Query("gname") String gname);


    @GET("/api/GPSLocation/update/")
    Call<Response> geoPush(@Query("lat") String lat,@Query("lang") String lang,@Query("id") String uid);


    @GET("/api/SC/SCS")
    Call<SocialChallengeStatus> getScs(@Query("sid") String uid);

    @GET("/api/SC/SC/")
    Call<SocialChallengeStatus> getSc(@Query("sid") String uid);


}
