package co.zakuna.retrofit.tiket_belumjadi.Activity;

//import android.support.v4.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.jraska.console.Console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import co.zakuna.retrofit.R;
import co.zakuna.retrofit.tiket_belumjadi.API.ApiInterface;
import co.zakuna.retrofit.tiket_belumjadi.API.ApiResponse;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Kereta;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Kursi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class SpesificActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    ApiResponse api;
    final boolean found = false;
    List<String> ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consol);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://tiket.tokopedia.com/kereta-api/ajax/search/schedules/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiInterface = retrofit.create(ApiInterface.class);
        Intent i = getIntent();

        ids = Arrays.asList(i.getStringArrayExtra("ids"));
        ObserveCall(ids);

    }

    public void ObserveCall(List<String> ids) {
        for (final String id : ids) {
            final Observable<ApiResponse> observable = apiInterface.kereta(id);
            observable.subscribeOn(Schedulers.io())
                    .delay(5, TimeUnit.SECONDS)
                    .repeat(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<ApiResponse>() {
                        @Override
                        public void onStart() {
                            super.onStart();
                            Console.writeLine("mencari tiket "+id);
                        }

                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(ApiResponse apiResponse) {
                            api = apiResponse;
                            DateFormat df = new SimpleDateFormat("HH:mm");
                            String hour = df.format(Calendar.getInstance().getTime());
                            List<Kereta> keretas = api.getKeretas();
                            for (Kereta kereta : keretas) {
                                List<Kursi> kursiList = kereta.getKursis();
                                for (Kursi kursi : kursiList) {
                                    int tersedia = kursi.getTersedia();
                                    if (tersedia > 0) {
                                        String namaKereta = FullActivity.getNamaKereta(kereta.getId(),kursi.getSubClass());
                                        Console.writeLine(namaKereta+ " kelas "+ kursi.getSubClass() + "\nTersedia :" + tersedia);
                                        Console.writeLine();
//                                found = true;
                                        try {
                                            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                                            v.vibrate(2000);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(getApplicationContext(), "getar", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
//                                Console.writeLine(kereta.getId() + " tidak tersedia "+hour);
                                    }
                                }
                            }
                        }
                    });
        }
    }

    public void callGetRepos(String id) {
        Call<ApiResponse> call = apiInterface.keretas(id);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                List<Kereta> keretas = response.body().getKeretas();
                List<Kursi> kursiList = null;
                for (Kereta kereta : keretas) {
                    kursiList = kereta.getKursis();
                    for (Kursi kursi : kursiList) {
                        int tersedia = kursi.getTersedia();
                        if (tersedia > 0) {
                            Console.writeLine(kereta.getId() + " tersedia :" + tersedia);
                        } else {
                            Console.writeLine(kereta.getId() + " tidak tersedia");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Console.writeLine("gagal bro");
            }
        });
    }


//    public static void handle(Observable<ApiResponse> observable) {
//        observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(observer);
//    }
//    static Observer<ApiResponse> observer = new Observer<ApiResponse>() {
//        @Override
//        public void onCompleted() {
//        }
//
//        @Override
//        public void onError(Throwable e) {
//        }
//
//        @Override
//        public void onNext(ApiResponse apiResponse) {
//            if (context instanceof IResultHandler)
//                ((IResultHandler) context).upData(o);
//        }
//
//    };
}