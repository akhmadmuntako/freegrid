package co.zakuna.retrofit.tiket_belumjadi;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 13/07/2016.
 */
public interface APIInterface {
    @GET("tiket/{id}/daftar_tiket")
    Call<TiketResponse> getAllSchedules();
}
