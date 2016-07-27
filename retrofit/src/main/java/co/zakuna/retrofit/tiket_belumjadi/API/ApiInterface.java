package co.zakuna.retrofit.tiket_belumjadi.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Lenovo on 13/07/2016.
 */
public interface ApiInterface {
    @GET("availabilities/{id}/0")
    Call<ApiResponse> keretas (@Path("id")String id);

    @GET("availabilities/{id}/0")
    Observable<ApiResponse> kereta (@Path("id")String id);
}
