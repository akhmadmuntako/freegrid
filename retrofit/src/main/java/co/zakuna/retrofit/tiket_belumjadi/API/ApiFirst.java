package co.zakuna.retrofit.tiket_belumjadi.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lenovo on 13/07/2016.
 */
public interface ApiFirst {
    @GET("stations/{id}")
    Call<ApiResponse> stasiuns (@Path("id")String id);
}
