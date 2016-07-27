package co.zakuna.retrofit.tiket_belumjadi.Objek;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 14/07/2016.
 */
public class Kereta implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("availability")
    private List<Kursi> kursis = new ArrayList<>();
    @SerializedName("redis")
    private boolean redis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Kursi> getKursis() {
        return kursis;
    }

    public void setKursis(List<Kursi> kursis) {
        this.kursis = kursis;
    }

    public boolean isRedis() {
        return redis;
    }

    public void setRedis(boolean redis) {
        this.redis = redis;
    }
}
