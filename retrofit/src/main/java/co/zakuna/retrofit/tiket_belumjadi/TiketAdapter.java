package co.zakuna.retrofit.tiket_belumjadi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import co.zakuna.retrofit.R;

/**
 * Created by Lenovo on 13/07/2016.
 */
public class TiketAdapter extends RecyclerView.Adapter<TiketAdapter.TiketViewHolder>{
    private List<Tiket> tikets;
    private int Row;
    private Context context;

    @Override
    public TiketViewHolder onCreateViewHolder(ViewGroup parent, int ViewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(Row,parent,false);
        return new TiketViewHolder(view);
    }
    @Override
    public void onBindViewHolder(TiketViewHolder holder, final int position) {
        holder.namaKereta.setText(tikets.get(position).getNamaKereta());
        holder.asal.setText(tikets.get(position).getAsal());
        holder.tujuan.setText(tikets.get(position).getTujuan());
    }

    @Override
    public int getItemCount() {
        return tikets.size();
    }

    public static  class TiketViewHolder extends RecyclerView.ViewHolder{
        LinearLayout TiketLayout;
        TextView namaKereta;
        TextView asal;
        TextView tujuan;

        public TiketViewHolder(View itemView) {
            super(itemView);
            TiketLayout = (LinearLayout) itemView.findViewById(R.id.tiket_layout);
            namaKereta = (TextView) itemView.findViewById(R.id.train_name);
            asal = (TextView) itemView.findViewById(R.id.origin_name);
            tujuan = (TextView)itemView.findViewById(R.id.destination_name);
        }
    }

    public TiketAdapter(List<Tiket> tiket,int row,Context context){
        this.tikets = tiket;
        this.Row = row;
        this.context = context;
    }

}
