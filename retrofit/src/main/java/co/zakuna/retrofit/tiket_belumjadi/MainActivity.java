package co.zakuna.retrofit.tiket_belumjadi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import co.zakuna.retrofit.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        APIInterface apiInterface =
        ApiClient.getTiket().create(APIInterface.class);
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.tiket_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Call<TiketResponse> call = apiInterface.getAllSchedules();
        call.enqueue(new Callback<TiketResponse>() {
            @Override
            public void onResponse(Call<TiketResponse> call, Response<TiketResponse> response) {
                List<Tiket> tikets = response.body().getJadwal();
                recyclerView.setAdapter(new TiketAdapter(tikets,R.layout.list_item,getApplicationContext()));
            }

            @Override
            public void onFailure(Call<TiketResponse> call, Throwable t) {

            }
        });
    }


}


