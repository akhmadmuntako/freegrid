package co.zakuna.retrofit.tiket_belumjadi.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.jraska.console.Console;

import java.util.ArrayList;
import java.util.List;

import co.zakuna.retrofit.R;
import co.zakuna.retrofit.tiket_belumjadi.API.ApiFull;
import co.zakuna.retrofit.tiket_belumjadi.Adapter.KeretaAdapter;
import co.zakuna.retrofit.tiket_belumjadi.Objek.DataJadwal;
import co.zakuna.retrofit.tiket_belumjadi.API.FullResponse;
import co.zakuna.retrofit.tiket_belumjadi.Objek.JadwalPerjalanan;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;
import co.zakuna.retrofit.tiket_belumjadi.Objek._Query;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class FullActivity extends AppCompatActivity {
    Retrofit retrofit;
    RecyclerView recyclerView;
    KeretaAdapter mAdapter;
    String idAsal, idTujuan, tanggal, kotaAsal, kotaTujuan;
    List<String> idPerjalanan;
    static List<JadwalPerjalanan> perjalanans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_view);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://tiket.tokopedia.com/kereta-api/ajax/search/schedules/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        Intent i = getIntent();
        idAsal = i.getStringExtra("idAsal");
        idTujuan = i.getStringExtra("idTujuan");
        tanggal = i.getStringExtra("tanggal");
        kotaAsal = i.getStringExtra("kotaAsal");
        kotaTujuan = i.getStringExtra("kotaTujuan");

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        cariTiket(idAsal, idTujuan, kotaAsal, kotaTujuan, tanggal);
    }

    public void cariTiket(String idAsal, String idTujuan, String kotaAsal, String kotaTujuan, String tanggal) {
        final ApiFull apiInterface = retrofit.create(ApiFull.class);
        final Call<FullResponse> calling = apiInterface.responseCall(idAsal, idTujuan, kotaAsal, kotaTujuan, tanggal);
        calling.enqueue(new Callback<FullResponse>() {
            @Override
            public void onResponse(Call<FullResponse> call, Response<FullResponse> response) {
                _Query query = response.body().getQuery();
                List<Stasiun> stasiuns = response.body().getStasiuns();
                perjalanans = response.body().getPerjalanans();
                List<DataJadwal> dataJadwals = response.body().getDataJadwals();
                idPerjalanan = new ArrayList<String>();
                if (perjalanans != null) {
                    for (JadwalPerjalanan jd : perjalanans) {
                        String id = jd.getId();
                        if (idPerjalanan.contains(id)) {
                            //do nothing
                        } else {
                            idPerjalanan.add(id);
                        }
                    }
                    String[] ids = idPerjalanan.toArray(new String[idPerjalanan.size()]);
                    Intent i = new Intent(FullActivity.this, SpesificActivity.class);
                    i.putExtra("ids", ids);
                    startActivity(i);
                    finish();

                    //Finally initializing our adapter
                    mAdapter = new KeretaAdapter(perjalanans, FullActivity.this);

                    //Adding adapter to recyclerview
                    recyclerView.setAdapter(mAdapter);
                } else {
                    Toast.makeText(FullActivity.this, "Maaf rute yang dipilih tidak tersedia", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FullResponse> call, Throwable t) {

            }
        });
    }

    public static String getNamaKereta(String ID, String subclass) {
        String namaKereta = null;
        for (JadwalPerjalanan jd : perjalanans) {
            if (jd.getId().equals(ID) && jd.getSubClass().equals(subclass)) {
                namaKereta = jd.getNamaKereta()+ " harga "+ jd.getAdultFare();
            }
        }
        return namaKereta;
    }
}
