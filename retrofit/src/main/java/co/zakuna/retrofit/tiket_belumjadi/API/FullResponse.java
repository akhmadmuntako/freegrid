package co.zakuna.retrofit.tiket_belumjadi.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import co.zakuna.retrofit.tiket_belumjadi.Objek.DataJadwal;
import co.zakuna.retrofit.tiket_belumjadi.Objek.JadwalPerjalanan;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;
import co.zakuna.retrofit.tiket_belumjadi.Objek._Query;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class FullResponse {
    @SerializedName("query")
    private _Query query;
    @SerializedName("stations")
    private List<Stasiun> stasiuns = new ArrayList<>();
    @SerializedName("schedules")
    private List<JadwalPerjalanan> perjalanans= new ArrayList<>();
    @SerializedName("schedulesData")
    private List<DataJadwal> dataJadwals = new ArrayList<>();
//    @SerializedName("hashMap")
//    private String  has

    public _Query getQuery() {
        return query;
    }

    public void setQuery(_Query query) {
        this.query = query;
    }

    public List<Stasiun> getStasiuns() {
        return stasiuns;
    }

    public void setStasiuns(List<Stasiun> stasiuns) {
        this.stasiuns = stasiuns;
    }

    public List<JadwalPerjalanan> getPerjalanans() {
        return perjalanans;
    }

    public void setPerjalanans(List<JadwalPerjalanan> perjalanans) {
        this.perjalanans = perjalanans;
    }

    public List<DataJadwal> getDataJadwals() {
        return dataJadwals;
    }

    public void setDataJadwals(List<DataJadwal> dataJadwals) {
        this.dataJadwals = dataJadwals;
    }
}
