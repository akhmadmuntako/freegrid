package com.example.gembong.myapplication;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private android.widget.LinearLayout.LayoutParams layoutParams;
    private OverScroller mScroller;
    private boolean mDragging = false;
    String msg;


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

        PanningView scrollView = new PanningView(this);

        final Freegrid freegrid = (Freegrid) findViewById(R.id.a);
        freegrid.setAdapter(adapter);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        View view = findViewById(R.id.main);

        if(view.getParent()!=null)
            ((ViewGroup)view.getParent()).removeView(view);

        layout.addView(view, new LinearLayout.LayoutParams(1280,2400));
        scrollView.addView(layout);
        setContentView(scrollView);

        freegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "posisi : " + position, Toast.LENGTH_SHORT).show();

            }
        });
        freegrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return true;
            }
        });
//        freegrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "long posisi : " + position, Toast.LENGTH_SHORT).show();
//                freegrid.createNewItem(adapter,rects);
//                return true;
//            }
//        });

    }

    class MyDragListener implements View.OnDragListener {

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
//            do{
            switch (action) {
                case DragEvent.ACTION_DRAG_STARTED:
                    layoutParams = (LinearLayout.LayoutParams) v.getLayoutParams();
                    Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");

                    int x_cord = (int) event.getX();
                    int y_cord = (int) event.getY();
                    v.invalidate();
                    break;
                case DragEvent.ACTION_DRAG_LOCATION:
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    Log.d(msg, "Drag Location xcord " + x_cord + " Ycord " + y_cord);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    View view = (View) event.getLocalState();
                    x_cord = (int) event.getX();
                    y_cord = (int) event.getY();
                    Log.d(msg, "Drag Exited xcord " + x_cord + " Ycord " + y_cord);
//                    v.setX(x_cord);
//                    v.setY(y_cord);
//                    layoutParams.leftMargin(x_cord);
                    layoutParams.leftMargin = x_cord;
                    layoutParams.topMargin = y_cord;
                    v.setLayoutParams(layoutParams);
                    v.setVisibility(View.VISIBLE);
                    v.invalidate();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    v.setVisibility(View.VISIBLE);
                    break;
                case DragEvent.ACTION_DROP:
                    v.setVisibility(View.VISIBLE);
                    break;
            }
            return true;
        }
    }

    class MyAdapter extends BaseAdapter implements PositionProfider {

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