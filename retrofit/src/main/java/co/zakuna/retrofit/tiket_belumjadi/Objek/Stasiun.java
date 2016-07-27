package co.zakuna.retrofit.tiket_belumjadi.Objek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class Stasiun {
    @SerializedName("id")
    private String id;
    @SerializedName("city")
    private String kota;
    @SerializedName("station")
    private String stasiun;
    @SerializedName("display_name")
    private String namaTampil;
    @SerializedName("name")
    private String namaKota;

    public Stasiun(String id, String kota, String stasiun, String namaTampil, String namaKota) {
        this.id = id;
        this.kota = kota;
        this.stasiun = stasiun;
        this.namaTampil = namaTampil;
        this.namaKota = namaKota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKota() {
        return kota;
    }
    public String getFullName(){
        return kota+"-"+namaTampil+"-"+id;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getStasiun() {
        return stasiun;
    }

    public void setStasiun(String stasiun) {
        this.stasiun = stasiun;
    }

    public String getNamaTampil() {
        return namaTampil;
    }

    public void setNamaTampil(String namaTampil) {
        this.namaTampil = namaTampil;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

}