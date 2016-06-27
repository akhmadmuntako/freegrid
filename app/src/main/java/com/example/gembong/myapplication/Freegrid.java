package com.example.gembong.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;


/**
 * Created by gembong on 6/10/16.
 */
public class Freegrid extends FrameLayout {
    DataSetObserver dataSetObserver;

    private AdapterView.OnItemClickListener clickListener;
    private AdapterView.OnItemLongClickListener longClickListener;

    private MyClickListener localClickListener;
    private LongClickListener longClick;


    public Freegrid(Context context) {
        super(context);
    }

    public Freegrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Freegrid(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    private void init() {
        dataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
            }

            @Override
            public void onInvalidated() {
                super.onInvalidated();
            }
        };
    }

    public void setAdapter(BaseAdapter adapter) {
        localClickListener = new MyClickListener();
        longClick = new LongClickListener();

        if (!(adapter instanceof PositionProfider)) {
            Exception e = new Exception();
            try {
                throw e;
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        adapter.registerDataSetObserver(dataSetObserver);
        for (int i = 0; i < adapter.getCount(); i++) {
            View v = adapter.getView(i, null, this);
            Rect rect = ((PositionProfider) adapter).getPositionRect(i);
            addView(v);
            dispatchSetPressed(true);
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            lp.width = rect.width();
            lp.height = rect.height();
            v.setLayoutParams(lp);
            v.setTranslationX(rect.left);
            v.setTranslationY(rect.top);
            v.setOnClickListener(localClickListener);
            v.setOnLongClickListener(longClick);
//            v.setOnDragListener(new OnDragListener);
        }
    }

    private class MyClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(null, v, indexOfChild(v), v.getId());
        }
    }

    private class LongClickListener implements OnLongClickListener{

        @Override
        public boolean onLongClick(View v) {
            longClickListener.onItemLongClick(null,v,indexOfChild(v),v.getId());
            return true;
        }
    }
    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener click) {
        longClickListener = click;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener click) {
        clickListener = click;
    }
    public boolean createNewItem(){

        return true;
    }


}