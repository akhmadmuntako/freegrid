package co.zakuna.retrofit.tiket_belumjadi;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lenovo on 13/07/2016.
 */
public class TiketResponse {
    @SerializedName("schedules")
    private List<Tiket> jadwal;
    @SerializedName("total_schedules")
    private Integer totalTiket;
    @SerializedName("available")
    private Integer tiketTersedia;

    public List<Tiket> getJadwal() {
        return jadwal;
    }

    public void setJadwal(List<Tiket> jadwal) {
        this.jadwal = jadwal;
    }

    public Integer getTotalTiket() {
        return totalTiket;
    }

    public void setTotalTiket(Integer totalTiket) {
        this.totalTiket = totalTiket;
    }

    public Integer getTiketTersedia() {
        return tiketTersedia;
    }

    public void setTiketTersedia(Integer tiketTersedia) {
        this.tiketTersedia = tiketTersedia;
    }
}
