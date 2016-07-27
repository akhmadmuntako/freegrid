package co.zakuna.retrofit.tiket_belumjadi.Activity;

import android.content.Intent;
//import android.support.v4.app.AppCompatActivity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jraska.console.Console;

import java.util.ArrayList;
import java.util.List;

import co.zakuna.retrofit.R;
import co.zakuna.retrofit.tiket_belumjadi.API.ApiFirst;
import co.zakuna.retrofit.tiket_belumjadi.API.ApiResponse;
import co.zakuna.retrofit.tiket_belumjadi.ConnectivityReceiver;
import co.zakuna.retrofit.tiket_belumjadi.MyApplication;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class StasiunActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener{
    private String[] simpleArray;
    static List<Stasiun> stasiuns;
    List<String> namaStasiun = new ArrayList<String>();

    final View.OnClickListener clickListener = new View.OnClickListener() {
        public void onClick(View v) {
            new prefetchData().execute();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new prefetchData().execute();
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
    }

    public class prefetchData extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            checkConnection();
        }

        @Override
        protected Void doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://tiket.tokopedia.com/kereta-api/ajax/home/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();

            final ApiFirst apiInterface = retrofit.create(ApiFirst.class);

            final Call<ApiResponse> calling = apiInterface.stasiuns("");
            calling.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    List<Stasiun> list = response.body().getStasiuns();
                    stasiuns = list;
                    for (Stasiun stas : stasiuns) {
                        namaStasiun.add(stas.getNamaKota());
                    }
                    simpleArray = namaStasiun.toArray(new String[namaStasiun.size()]);

                    Intent i = new Intent(StasiunActivity.this, IsiDataActivity.class);
                    i.putExtra("simpleArray", simpleArray);

                    startActivity(i);
                    // close this activity
                    finish();
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(StasiunActivity.this,"gagal bro",Toast.LENGTH_SHORT).show();
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
//        }
    }

    // Method to manually check connection status
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        View.OnClickListener click = null;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;

        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
            click = clickListener;
        }

        Snackbar snackbar = Snackbar
                .make(findViewById(R.id.fab), message, Snackbar.LENGTH_LONG);

        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(color);
        textView.setOnClickListener(click);
        snackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    public static String getIdStasiun(String key) {
        String id = null;
        for (Stasiun st : stasiuns) {
            if (key.equals(st.getNamaKota())) {
                id = st.getId();
            }
        }
        return id;
    }

    public static String getNamaStasiun(String key) {
        String nama = null;
        for (Stasiun st : stasiuns) {
            if (key.equals(st.getNamaKota())) {
                nama = st.getFullName();
            }
        }
        return nama;
    }

}


