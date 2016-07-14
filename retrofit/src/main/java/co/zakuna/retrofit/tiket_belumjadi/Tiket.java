package co.zakuna.retrofit.tiket_belumjadi;


import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by Lenovo on 13/07/2016.
 */
public class Tiket {

    @SerializedName("id")
    private String id;
    @SerializedName("origin")
    private String asal;
    @SerializedName("destination")
    private String tujuan;
    @SerializedName("train_no")
    private String nomorKereta;
    @SerializedName("train_name")
    private String namaKereta;
    @SerializedName("departure_date")
    private String jamBrg;
    @SerializedName("arrival_date")
    private String jamDtg;
    @SerializedName("departure_time")
    private String tanggalBrg;
    @SerializedName("arrival_time")
    private String tanggalDtg;
    @SerializedName("sub_class")
    private String subClass;
    @SerializedName("class")
    private String _class;
    @SerializedName("availabilities")
    private List<String> availabilities = new ArrayList<String>() ;
    @SerializedName("fares")
    private List<String> fares = new ArrayList<String>() ;
    @SerializedName("origin_name")
    private String kotaAsal;
    @SerializedName("destination_name")
    private String  kotaTujuan;
    @SerializedName("lowest_fare")
    private Integer lowestFare;
    @SerializedName("adult_fare")
    private Integer AdultFare;

    public Tiket(String ID, String origin,String destination, String train_no,String train_name,
                 String departure_date,String arrival_date,String sub_class,String Class,
                 List<String> availabilities, List<String> fares,String origin_name, String deestination_name){
        this.id = ID;
        this.asal = origin;
        this.tujuan = destination;
        this.nomorKereta = train_no;
        this.namaKereta = train_name;
        this.tanggalBrg = departure_date;
        this.tanggalDtg = arrival_date;
        this.subClass = sub_class;
        this._class = Class;
        this.availabilities = availabilities;
        this.fares = fares;
        this.kotaAsal = origin_name;
        this.kotaTujuan = deestination_name;
    }

    public String getId(){
        return id;
    }
    public String getAsal(){
        return asal;
    }

    public String getTujuan(){
        return  tujuan;
    }

    public String getNomorKereta(){
        return nomorKereta;
    }

    public String getNamaKereta(){
        return namaKereta;
    }

    public String getTanggalBrg(){
        return tanggalBrg;
    }

    public String getTanggalDtg(){
        return tanggalDtg;
    }

    public String getSubClass(){
        return subClass;
    }

    public String get_class(){
        return _class;
    }

    public String getKotaAsal(){
        return kotaAsal;
    }

    public String getKotaTujuan(){
        return kotaTujuan;
    }

    public List<String> getAvailabilities(){
        return availabilities;
    }

    public List<String> getFares(){
        return fares;
    }

    public String getJamBrg() {
        return jamBrg;
    }

    public String getJamDtg() {
        return jamDtg;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAsal(String asal) {
        this.asal = asal;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public void setNomorKereta(String nomorKereta) {
        this.nomorKereta = nomorKereta;
    }

    public void setNamaKereta(String namaKereta) {
        this.namaKereta = namaKereta;
    }

    public void setTanggalBrg(String tanggalBrg) {
        this.tanggalBrg = tanggalBrg;
    }

    public void setTanggalDtg(String tanggalDtg) {
        this.tanggalDtg = tanggalDtg;
    }

    public void setSubClass(String subClass) {
        this.subClass = subClass;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public void setAvailabilities(List<String> availabilities) {
        this.availabilities = availabilities;
    }

    public void setFares(List<String> fares) {
        this.fares = fares;
    }

    public void setKotaAsal(String kotaAsal) {
        this.kotaAsal = kotaAsal;
    }

    public void setKotaTujuan(String kotaTujuan) {
        this.kotaTujuan = kotaTujuan;
    }

    public void setJamBrg(String jamBrg) {
        this.jamBrg = jamBrg;
    }

    public void setJamDtg(String jamDtg) {
        this.jamDtg = jamDtg;
    }

    public Integer getLowestFare() {
        return lowestFare;
    }

    public void setLowestFare(Integer lowestFare) {
        this.lowestFare = lowestFare;
    }

    public Integer getAdultFare() {
        return AdultFare;
    }

    public void setAdultFare(Integer adultFare) {
        AdultFare = adultFare;
    }
}
