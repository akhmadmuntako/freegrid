package co.zakuna.retrofit.tiket_belumjadi.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Lenovo on 15/07/2016.
 */
public interface ApiFull {
    //departure/KA/station/JNG/station/23-07-2016/1/0/departure/Kebumen-Karanganyar-KA/Jakarta-Jatinegara-JNG/0/station
    @GET("departure/{kode_asal}/station/{kode_tujuan}/station/{tanggal}/1/0/departure/{asal}/{tujuan}/0/station")
    Call<FullResponse> responseCall(
            @Path("kode_asal") String kodeAsal,
            @Path("kode_tujuan") String kodeTujuan,
            @Path("asal") String asal,//format : nama kota-nama stasiun-kode stasiun
            @Path("tujuan") String tujuan,//format : nama kota-nama stasiun-kode stasiun
            @Path("tanggal") String tanggal//format tanggal-bulan-tahun
    );
}
