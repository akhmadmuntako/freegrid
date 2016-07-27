package co.zakuna.retrofit.tiket_belumjadi.Objek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Lenovo on 15/07/2016.
 */
public class Kursi extends Kereta{
    @SerializedName("subclass")
    private String subClass;
    @SerializedName("class")
    private String _class;
    @SerializedName("available")
    private Integer tersedia;

    public Kursi(String subClass,String _class,int tersedia ){
        this.subClass =subClass;
        this._class = _class;
        this.tersedia = tersedia;
    }
    public String getSubClass() {
        return subClass;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public Integer getTersedia() {
        return tersedia;
    }

    public void setTersedia(Integer tersedia) {
        this.tersedia = tersedia;
    }
}
