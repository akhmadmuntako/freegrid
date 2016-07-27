package co.zakuna.retrofit.tiket_belumjadi.Objek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class _Query {
    @SerializedName("ori")
    private String asal;
    @SerializedName("dest")
    private  String tujuan;
    @SerializedName("adul")
    private String dewasa;
    @SerializedName("infant")
    private String anak;
    @SerializedName("trip")
    private String trip;
    @SerializedName("dep_date")
    private String tanggalBrg;
    @SerializedName("totalPassenger")
    private Integer penumpang;

    public String getAsal() {
        return asal;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getDewasa() {
        return dewasa;
    }

    public void setDewasa(String dewasa) {
        this.dewasa = dewasa;
    }

    public String getAnak() {
        return anak;
    }

    public void setAnak(String anak) {
        this.anak = anak;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTanggalBrg() {
        return tanggalBrg;
    }

    public void setTanggalBrg(String tanggalBrg) {
        this.tanggalBrg = tanggalBrg;
    }

    public Integer getPenumpang() {
        return penumpang;
    }

    public void setPenumpang(Integer penumpang) {
        this.penumpang = penumpang;
    }
}
