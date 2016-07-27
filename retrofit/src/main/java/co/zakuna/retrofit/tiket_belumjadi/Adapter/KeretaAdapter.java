package co.zakuna.retrofit.tiket_belumjadi.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import android.view.ViewGroup;
import android.widget.TextView;

import co.zakuna.retrofit.R;
import co.zakuna.retrofit.tiket_belumjadi.Objek.JadwalPerjalanan;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;

/**
 * Created by Lenovo on 20/06/2016.
 */
public class KeretaAdapter extends RecyclerView.Adapter<KeretaAdapter.MyViewHolder> {
    private List<JadwalPerjalanan> perjalananList;
    private Context context;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_kereta, parent, false);
        return new MyViewHolder(itemView);
    }

    public KeretaAdapter(List<JadwalPerjalanan> listData, Context c){
        this.perjalananList = listData;
        this.context = c;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        JadwalPerjalanan jadwal= perjalananList.get(position);
        holder.train_name.setText(jadwal.getNamaKereta()+" ");
        holder.train_no.setText(jadwal.getNomorKereta());
        holder.origin.setText(jadwal.getAsal()+" - ");
        holder.destination.setText(jadwal.getTujuan());
        holder.departure_date.setText(jadwal.getTanggalBrg());
        holder.arrival_date.setText(jadwal.getTanggalDtg());
        holder.adult_fare.setText(String.valueOf(jadwal.getAdultFare()));
    }

    public int getItemCount() {
        return perjalananList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id,origin,destination,train_no,train_name,departure_date,arrival_date;
        TextView arival_time, departure_time,lowes_fare,subclass,_class,adult_fare;
        public MyViewHolder(View view){
            super(view);
            train_name = (TextView)view.findViewById(R.id.train_name);
            train_no = (TextView)view.findViewById(R.id.train_no);
            origin = (TextView)view.findViewById(R.id.origin);
            destination = (TextView)view.findViewById(R.id.destination);
            departure_date = (TextView)view.findViewById(R.id.departure_date);
            arrival_date =(TextView)view.findViewById(R.id.arrival_date);
//            departure_time = (TextView)view.findViewById(R.id.departure_time);
//            arival_time = (TextView)view.findViewById(R.id.arrival_time);
//            lowes_fare = (TextView)view.findViewById(R.id.lowest_fare);
//            subclass = (TextView)view.findViewById(R.id.sub_class);
//            _class = (TextView)view.findViewById(R.id._class);
            adult_fare = (TextView)view.findViewById(R.id.adult_fare);
        }
    }

}

