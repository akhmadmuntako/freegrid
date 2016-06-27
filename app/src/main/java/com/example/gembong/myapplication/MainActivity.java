package com.example.gembong.myapplication;

import android.app.Dialog;
import android.graphics.Rect;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
//import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Rect> rects = new ArrayList<>();

        Rect rect = new Rect(100, 100, 200, 200);
        rects.add(rect);
        rects.add(new Rect(150, 300, 250, 400));

        rects.add(new Rect(300, 100, 800, 200));

        final MyAdapter adapter = new MyAdapter();
        adapter.setRects(rects);

        final Freegrid freegrid = (Freegrid) findViewById(R.id.a);
        freegrid.setAdapter(adapter);

        freegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "posisi : " + position, Toast.LENGTH_SHORT).show();
            }
        });
        freegrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Dialog d = new Dialog(MainActivity.this,0);
//
//                d.setContentView(R.layout.new_item);
//                d.setTitle("Create new Item");
//
//                final EditText leftEdit = (EditText)d.findViewById(R.id.left);
//                final EditText topEdit = (EditText)d.findViewById(R.id.top);
//                final EditText rightEdit = (EditText)d.findViewById(R.id.right);
//                final EditText buttomEdit = (EditText)d.findViewById(R.id.bottom);
//                Button createButton = (Button) d.findViewById(R.id.create_button);
//                Button cancelButton = (Button) d.findViewById(R.id.cancel_button);
//                // if button create is clicked, create new item
//                createButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        try {
//                            int L = Integer.parseInt(String.valueOf(leftEdit.getText()));
//                            int T = Integer.parseInt(String.valueOf(topEdit.getText()));
//                            int R = Integer.parseInt(String.valueOf(rightEdit.getText()));
//                            int B = Integer.parseInt(String.valueOf(buttomEdit.getText()));
//                            Rect rect = new Rect(L, T, R, B);
////                        ArrayList<Rect> rects = new ArrayList<>();
//                            rects.add(rect);
//                            adapter.setRects(rects);
////                        freegrid.re
//                        }catch (Exception e){
//                            e.printStackTrace();
//                        }
//                    }
//                });
//                d.show();
                Toast.makeText(MainActivity.this, "long posisi : " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        freegrid.setOnDragListener(new MyDragListener());

    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_STARTED:
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    }

    class MyAdapter extends BaseAdapter implements PositionProfider{

        List<Rect> rects;

        public void setRects(List<Rect> dd) {
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
    }
}
