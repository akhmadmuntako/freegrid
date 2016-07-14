package co.zakuna.retrofit.tiket_belumjadi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 13/07/2016.
 */
public class ApiClient {
//    public static String BASE_URL = "https://tiket.tokopedia.com/kereta-api/search/Kutoarjo/Jakarta?adult=1&infant=0&trip=departure&dep_date=15-07-2016&ori=Kutoarjo&dest=Jakarta" ;
    public static Retrofit retrofit = null;

    public static Retrofit getTiket(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://tiket.tokopedia.com/kereta-api/ajax/search/schedules/departure/Kutoarjo/city/Jakarta/city/15-07-2016/1/0/departure/Kutoarjo/Jakarta/0/city/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
