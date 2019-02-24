package in.ac.ksit.android.fitargot.Network;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;

public interface GoogleApis {

//    https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=3000&types=park&key=AIzaSyB4KfQQbmSWuwpPgOh3Y5-AKE_uUQAZk
@GET
Call<String> getNearbyPlaces(@Field("location")String location,@Field("radius") int radius,@Field("types") String type,@Field("key") String apikey);

}
