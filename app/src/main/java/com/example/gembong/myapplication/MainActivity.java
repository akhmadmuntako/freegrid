package com.example.gembong.myapplication;

import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.OverScroller;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;

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

        CustomScrollView scrollView = new CustomScrollView(this);

        final Freegrid freegrid = (Freegrid) findViewById(R.id.a);
        freegrid.setAdapter(adapter);


//        ViewParent parent = view.getParent();

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
//        for (int i = 0;i<3;i++) {
//            View view = new View(this);
//
////            layout.addView(view, new LinearLayout.LayoutParams(1280, 720));
//            ImageView iv = new ImageView(this);
//            iv.setImageResource(R.drawable.ic_launcher);
//            iv.setScaleType(ImageView.ScaleType.FIT_CENTER);
//            layout.addView(view, new LinearLayout.LayoutParams(1280, 720));
//        }
        View view = findViewById(R.id.main);
        if(view.getParent()!=null)
            ((ViewGroup)view.getParent()).removeView(view);
        layout.addView(view, new LinearLayout.LayoutParams(1280,2400));
        scrollView.addView(layout);
//        scrollView.setLayoutParams(new LinearLayout.LayoutParams(3600, 2400));
        setContentView(scrollView);

        freegrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "posisi : " + position, Toast.LENGTH_SHORT).show();
            }
        });
        freegrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "long posisi : " + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });



//        freegrid.setOnLongClickListener(new AdapterView.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//                ClipData dragData = new ClipData((CharSequence) v.getTag(), mimeTypes, item);
//                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(freegrid);
//                v.startDrag(dragData, myShadow, null, 0);
//                v.setVisibility(View.INVISIBLE);
//                Toast.makeText(MainActivity.this, "toas", Toast.LENGTH_SHORT).show();
//                OverScrollDecoratorHelper.setUpStaticOverScroll(v, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL);
//                int curX = mScroller.getCurrX();
//                int curY = mScroller.getCurrY();
//                float velocity = mScroller.getCurrVelocity();
//                freegrid.setX(curX);
//                freegrid.setY(curY);
//                return true;
//            }
//        });
//        freegrid.setOnDragListener(new MyDragListener());
//
//        freegrid.setOnTouchListener(new View.OnTouchListener() {
//
//            CustomScrollView customScrollView = new CustomScrollView(MainActivity.this);
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    ClipData data = ClipData.newPlainText("", "");
//                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(freegrid);
//                    freegrid.startDrag(data, shadowBuilder, freegrid, 0);
//                    freegrid.setVisibility(View.INVISIBLE);
//                } else {
//                    return false;
//                }
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
//            }while (action !=0);
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