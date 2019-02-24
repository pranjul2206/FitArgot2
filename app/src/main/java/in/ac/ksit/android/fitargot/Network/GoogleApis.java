package in.ac.ksit.android.fitargot.Network;

import in.ac.ksit.android.fitargot.Network.Model.PlaceModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoogleApis {

//    https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=3000&types=park&key=AIzaSyB4KfQQbmSWuwpPgOh3Y5-AKE_uUQAZk
@GET("nearbysearch/json")
Call<PlaceModel> getNearbyPlaces(@Query("location") String location, @Query("radius") int radius, @Query("types") String type, @Query("key") String apikey);

}
