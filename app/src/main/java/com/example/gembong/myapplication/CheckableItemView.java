package com.example.gembong.myapplication;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Lenovo on 04/07/2016.
 */

public class CheckableItemView extends View implements Checkable{

//    private boolean mChecked = false;
//    private CheckableView.OnCheckedChangeListener mOnCheckedChangeListener;
//    private CheckableView.OnCheckedChangeListener mOnCheckedChangeWidgetListener;

//    @Override
//    public void setOnCheckedChangeListener(OnCheckedChangeListener widgetListener) {
//        mOnCheckedChangeListener = widgetListener;
//    }
//
//    @Override
//    public void setOnCheckedChangeWidgetListener(OnCheckedChangeListener widgetListener) {
//        mOnCheckedChangeWidgetListener = widgetListener;
//    }

    /**
     * The checked state of the CheckableView
     */
    private boolean mIsChecked = false;
    /**
     * OnCheckedChangeListener to receive callbacks when state has changed
     */
//    private CheckableGroup.OnCheckedChangeListener mOnCheckedChangeListener;


    private OnClickListener mOnClickListener;

    /**
     * Contructor
     *
     * @param context
     */
    public CheckableItemView(Context context) {
        super(context);
        super.setOnClickListener(mOnClickListener);
    }

    public CheckableItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CheckableItemView(Context context, AttributeSet attributeSet, int deftStyleAttr) {
        super(context, attributeSet, deftStyleAttr);
    }

    /**
     * @return Boolean determining state of CheckableView
     * @see #setChecked(boolean)
     */
    public boolean isChecked() {
        return mIsChecked;
    }

    /**
     * @param isChecked Boolean to set the state of the CheckableView
     */
    public void setChecked(boolean isChecked) {
        mIsChecked = isChecked;
//        if (isChecked()) {
//            animateChecked(mIsInflated);
//        } else {
//            animateUnchecked(mIsInflated);
//        }
//
//        if (mOnCheckedChangeListener != null && mIsInflated) {
//            mOnCheckedChangeListener.onCheckedChanged(this, isChecked());
//        }
    }


    /**
     * Toggles the CheckableView
     */
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    public void setOnClickListener(OnClickListener l) {
        mOnClickListener = l;
    }


    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        ColorDrawable stateListDrawable = (ColorDrawable) getBackground();
        int[] state;
            Drawable dr = this.getBackground();
        if (mIsChecked) {
            state = new int[]{android.R.attr.state_checked};
            playSoundEffect(SoundEffectConstants.CLICK);
            Toast.makeText(getContext(),state.toString(),Toast.LENGTH_SHORT).show();
            View view = (View)this;
//           view.setBackground(getBackground());
            this.setBackgroundResource(R.drawable.ic_check);
            stateListDrawable.setState(state);
        } else {
            state = new int[]{android.R.attr.state_empty};
            this.setBackground(dr);
            stateListDrawable.setState(state);
        }

        //redraw
        invalidate();
    }

    @Override
    public boolean performClick() {
        toggle();
        return super.performClick();
    }
}
