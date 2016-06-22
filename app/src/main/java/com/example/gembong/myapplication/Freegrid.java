package com.example.gembong.myapplication;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Toast;

/**
 * Created by gembong on 6/10/16.
 */
public class Freegrid extends FrameLayout {
    DataSetObserver dataSetObserver;
    Click listener;
    LongClick l;

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
            LayoutParams lp = (LayoutParams) v.getLayoutParams();
            lp.width = rect.width();
            lp.height = rect.height();
            v.setLayoutParams(lp);
            v.setTranslationX(rect.left);
            v.setTranslationY(rect.top);
        }
    }
    public interface Click{
        void onItemClick(AdapterView<?> parent, View view, int position, long id);
    }

    public interface LongClick{
        void onItemLongClick(AdapterView<?> parent, View view, int position, long id);
    }
    public void setOnItemClickListener(Click click) {
        listener = click;
    }

    public void setOnItemLongClickListener(LongClick click){
        l = click;
    }
}



