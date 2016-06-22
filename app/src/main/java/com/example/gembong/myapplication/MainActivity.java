package com.example.gembong.myapplication;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<Rect> rects = new ArrayList<>();

            Rect rect = new Rect(100,100,200,200);
        rects.add(rect);
        rects.add(new Rect(150, 300, 250, 400));

        rects.add(new Rect(300, 100, 800, 200));

        MyAdapter adapter = new MyAdapter();
        adapter.setRects(rects);
//        ListView l = new ListView(this);
//        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                System.exit(0);
//            }
//        });
        Freegrid freegrid = (Freegrid) findViewById(R.id.a);
        freegrid.setAdapter(adapter);

        freegrid.setOnItemClickListener(new Freegrid.Click() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_LONG).show();
            }
        });
        freegrid.setOnItemLongClickListener(new Freegrid.LongClick() {
            @Override
            public void onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"long click",Toast.LENGTH_LONG).show();
            }
        });
    }


    class MyAdapter extends BaseAdapter implements PositionProfider,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener{

        List<Rect> rects;
        public void setRects(List<Rect> dd){
            rects = dd;
        }
        @Override
        public int getCount() {
            return rects.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            View v = inflater.inflate(R.layout.item, null, false);
            return v;
        }

        @Override
        public Rect getPositionRect(int position) {
            return rects.get(position);
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),"click",Toast.LENGTH_LONG).show();
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(),"Long",Toast.LENGTH_LONG).show();
            return true;
        }
    }
}
