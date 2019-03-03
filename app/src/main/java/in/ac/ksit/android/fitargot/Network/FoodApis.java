package in.ac.ksit.android.fitargot.Network;

import in.ac.ksit.android.fitargot.Network.Model.FoodModel;
import in.ac.ksit.android.fitargot.Network.Model.Response;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface FoodApis {

    @Multipart
    @POST("classify")
    Call<FoodModel> uploadPush(@Part MultipartBody.Part file, @Part("num_tag") RequestBody tags, @Part("api_key") RequestBody api_key);


}
