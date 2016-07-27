package co.zakuna.retrofit.tiket_belumjadi.API;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import co.zakuna.retrofit.tiket_belumjadi.Objek.Kereta;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class ApiResponse {
    @SerializedName("stations")
    private List<Stasiun> stasiuns = new ArrayList<>();

    @SerializedName("availabilities")
    private List<Kereta> keretas = new ArrayList<>();

    public List<Stasiun> getStasiuns() {
        return stasiuns;
    }

    public void setStasiuns(List<Stasiun> stasiuns) {
        this.stasiuns = stasiuns;
    }

    public  List<Kereta> getKeretas() {
        return keretas;
    }

    public void setKeretas(List<Kereta> keretas) {
        this.keretas = keretas;
    }
}
