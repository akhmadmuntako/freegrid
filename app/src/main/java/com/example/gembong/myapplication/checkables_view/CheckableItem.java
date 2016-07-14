//package com.example.gembong.myapplication.checkables_view;
//
//
//import android.content.Context;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.StateListDrawable;
//import android.util.AttributeSet;
//import android.view.SoundEffectConstants;
//import android.view.View;
//import android.view.ViewParent;
//import android.widget.ImageView;
//
//import com.example.gembong.myapplication.CheckableItemGroup;
//import com.example.gembong.myapplication.CheckableView;
//import com.example.gembong.myapplication.R;
///**
// * Created by Lenovo on 11/07/2016.
// */
//public class CheckableItem extends View implements CheckableView{
//    private boolean mChecked = false;
//    private CheckableView.OnCheckedChangeListener mOnCheckedChangeListener;
//    private CheckableView.OnCheckedChangeListener mOnCheckedChangeWidgetListener;
//
//    public CheckableItem(Context context) {
//        super(context);
//        setClickable(true);
//    }
//
//    public CheckableItem(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        setClickable(true);
//    }
//
//    public CheckableItem(Context context, AttributeSet attrs, int defStyle) {
//        super(context, attrs, defStyle);
//        setClickable(true);
//    }
//
//    @Override
//    public boolean isChecked() {
//        return mChecked;
//    }
//
//    @Override
//    public void toggle() {
//        setChecked(!mChecked);
//    }
//
//    @Override
//    public void setChecked(boolean checked) {
//        mChecked = checked;
//        if (mOnCheckedChangeListener != null) {
//            mOnCheckedChangeListener.onCheckedChanged(this, mChecked);
//        }
//        if (mOnCheckedChangeWidgetListener != null) {
//            mOnCheckedChangeWidgetListener.onCheckedChanged(this, mChecked);
//        }
//
//        refreshDrawableState();
//        invalidate();
//    }
//
//    @Override
//    protected void drawableStateChanged() {
//        super.drawableStateChanged();
//        ColorDrawable stateListDrawable = (ColorDrawable) getBackground();
//        if (stateListDrawable == null) {
//            stateListDrawable = (ColorDrawable) getResources().getDrawable(R.drawable.drawable_selector);
//            setBackground(stateListDrawable);
//        }
//        int[] state;
//        if (mChecked) {
//            state = new int[]{android.R.attr.state_checked};
//        } else {
//            state = new int[]{android.R.attr.state_empty};
//        }
//        stateListDrawable.setState(state);
//        //redraw
//        invalidate();
//    }
//
//    @Override
//    public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
//        mOnCheckedChangeListener = listener;
//    }
//
//    @Override
//    public void setOnCheckedChangeWidgetListener(CheckableView.OnCheckedChangeListener widgetListener) {
//        mOnCheckedChangeWidgetListener = widgetListener;
//    }
//
//    @Override
//    public boolean performClick() {
//        ViewParent parent = getParent();
//        if (parent != null && parent instanceof CheckableItemGroup && ((CheckableItemGroup) parent).isRadioGroupMode()) {
//            setChecked(true);
//        } else {
//            setChecked(!mChecked);
//        }
//        final boolean handled = super.performClick();
//        if (!handled) {
//            // View only makes a sound effect if the onClickListener was
//            // called, so we'll need to make one here instead.
//            playSoundEffect(SoundEffectConstants.CLICK);
//        }
//
//        return handled;
//    }
//
//    @Override
//    public boolean isClickable() {
//        return true;
//    }
//
//    @Override
//    public boolean isContextClickable() {
//        return true;
//    }
//
//}
//
//


/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//package com.example.gembong.myapplication.checkables_view;
//
////import android.annotation.NonNull;
//import android.support.annotation.DrawableRes;
//import android.support.annotation.Nullable;
//import android.view.View;
////import android.view.ViewHierarchyEncoder;
////import com.android.internal.R;
////import android.annotation.DrawableRes;
////import android.annotation.Nullable;
//import android.content.Context;
//import android.content.res.ColorStateList;
//import android.content.res.TypedArray;
//import android.graphics.Canvas;
//import android.graphics.PorterDuff;
//import android.graphics.drawable.Drawable;
//import android.util.AttributeSet;
//import android.view.Gravity;
////import android.view.RemotableViewMethod;
//import android.view.ViewDebug;
//import android.view.accessibility.AccessibilityEvent;
//import android.view.accessibility.AccessibilityNodeInfo;
//import android.widget.Checkable;
//import android.widget.TextView;
//
//import com.example.gembong.myapplication.R;
//
///**
// * An extension to {@link TextView} that supports the {@link Checkable}
// * interface and displays.
// * <p>
// * This is useful when used in a {@link android.widget.ListView ListView} where
// * the {@link android.widget.ListView#setChoiceMode(int) setChoiceMode} has
// * been set to something other than
// * {@link android.widget.ListView#CHOICE_MODE_NONE CHOICE_MODE_NONE}.
// *
// * @attr ref android.R.styleable#CheckedTextView_checked
// * @attr ref android.R.styleable#CheckedTextView_checkMark
// */
//public class CheckedTextView extends View implements Checkable {
//    private boolean mChecked;
//
//    private int mCheckMarkResource;
//    private Drawable mCheckMarkDrawable;
//    private ColorStateList mCheckMarkTintList = null;
//    private PorterDuff.Mode mCheckMarkTintMode = null;
//    private boolean mHasCheckMarkTint = false;
//    private boolean mHasCheckMarkTintMode = false;
//
//    private int mBasePadding;
//    private int mCheckMarkWidth;
//    private int mCheckMarkGravity = Gravity.END;
//
//    private boolean mNeedRequestlayout;
//
//    private static final int[] CHECKED_STATE_SET = {
//            R.attr.checked_item
//    };
//
//    public CheckedTextView(Context context) {
//        this(context, null);
//    }
//
//    public CheckedTextView(Context context, AttributeSet attrs) {
//        this(context, attrs, R.attr.checkedTextViewStyle);
//    }
//
//    public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr) {
//        this(context, attrs, defStyleAttr, 0);
//    }
//
//    public CheckedTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//
//        final TypedArray a = context.obtainStyledAttributes(
//                attrs, R.styleable.CheckableView, defStyleAttr, defStyleRes);
//
//        final Drawable d = a.getDrawable(R.styleable.CheckableItemGroup_checked_item);
//        if (d != null) {
//            setCheckMarkDrawable(d);
//        }
//
//        if (a.hasValue(R.styleable.CheckedTextView_checkMarkTintMode)) {
//            mCheckMarkTintMode = Drawable.parseTintMode(a.getInt(
//                    R.styleable.CheckedTextView_checkMarkTintMode, -1), mCheckMarkTintMode);
//            mHasCheckMarkTintMode = true;
//        }
//
//        if (a.hasValue(R.styleable.CheckedTextView_checkMarkTint)) {
//            mCheckMarkTintList = a.getColorStateList(R.styleable.CheckedTextView_checkMarkTint);
//            mHasCheckMarkTint = true;
//        }
//
//        mCheckMarkGravity = a.getInt(R.styleable.CheckedTextView_checkMarkGravity, Gravity.END);
//
//        final boolean checked = a.getBoolean(R.styleable.CheckedTextView_checked, false);
//        setChecked(checked);
//
//        a.recycle();
//
//        applyCheckMarkTint();
//    }
//
//    public void toggle() {
//        setChecked(!mChecked);
//    }
//
//    @ViewDebug.ExportedProperty
//    public boolean isChecked() {
//        return mChecked;
//    }
//
//    /**
//     * Sets the checked state of this view.
//     *
//     * @param checked {@code true} set the state to checked, {@code false} to
//     *                uncheck
//     */
//    public void setChecked(boolean checked) {
//        if (mChecked != checked) {
//            mChecked = checked;
//            refreshDrawableState();
//            notifyViewAccessibilityStateChangedIfNeeded(
//                    AccessibilityEvent.CONTENT_CHANGE_TYPE_UNDEFINED);
//        }
//    }
//
//    /**
//     * Sets the check mark to the drawable with the specified resource ID.
//     * <p>
//     * When this view is checked, the drawable's state set will include
//     * {@link android.R.attr#state_checked}.
//     *
//     * @param resId the resource identifier of drawable to use as the check
//     *              mark
//     * @attr ref android.R.styleable#CheckedTextView_checkMark
//     * @see #setCheckMarkDrawable(Drawable)
//     * @see #getCheckMarkDrawable()
//     */
//    public void setCheckMarkDrawable(@DrawableRes int resId) {
//        if (resId != 0 && resId == mCheckMarkResource) {
//            return;
//        }
//
//        mCheckMarkResource = resId;
//
//        Drawable d = null;
//        if (mCheckMarkResource != 0) {
//            d = getContext().getDrawable(mCheckMarkResource);
//        }
//        setCheckMarkDrawable(d);
//    }
//
//    /**
//     * Set the check mark to the specified drawable.
//     * <p>
//     * When this view is checked, the drawable's state set will include
//     * {@link android.R.attr#state_checked}.
//     *
//     * @param d the drawable to use for the check mark
//     * @attr ref android.R.styleable#CheckedTextView_checkMark
//     * @see #setCheckMarkDrawable(int)
//     * @see #getCheckMarkDrawable()
//     */
//    public void setCheckMarkDrawable(Drawable d) {
//        if (mCheckMarkDrawable != null) {
//            mCheckMarkDrawable.setCallback(null);
//            unscheduleDrawable(mCheckMarkDrawable);
//        }
//        mNeedRequestlayout = (d != mCheckMarkDrawable);
//        if (d != null) {
//            d.setCallback(this);
//            d.setVisible(getVisibility() == VISIBLE, false);
//            d.setState(CHECKED_STATE_SET);
//            setMinHeight(d.getIntrinsicHeight());
//
//            mCheckMarkWidth = d.getIntrinsicWidth();
//            d.setState(getDrawableState());
//            applyCheckMarkTint();
//        } else {
//            mCheckMarkWidth = 0;
//        }
//        mCheckMarkDrawable = d;
//
//        // Do padding resolution. This will call internalSetPadding() and do a
//        // requestLayout() if needed.
//        resolvePadding();
//    }
//
//    /**
//     * Applies a tint to the check mark drawable. Does not modify the
//     * current tint mode, which is {@link PorterDuff.Mode#SRC_IN} by default.
//     * <p>
//     * Subsequent calls to {@link #setCheckMarkDrawable(Drawable)} will
//     * automatically mutate the drawable and apply the specified tint and
//     * tint mode using
//     * {@link Drawable#setTintList(ColorStateList)}.
//     *
//     * @param tint the tint to apply, may be {@code null} to clear tint
//     *
//     * @attr ref android.R.styleable#CheckedTextView_checkMarkTint
//     * @see #getCheckMarkTintList()
//     * @see Drawable#setTintList(ColorStateList)
//     */
//    public void setCheckMarkTintList(@Nullable ColorStateList tint) {
//        mCheckMarkTintList = tint;
//        mHasCheckMarkTint = true;
//
//        applyCheckMarkTint();
//    }
//
//    /**
//     * Returns the tint applied to the check mark drawable, if specified.
//     *
//     * @return the tint applied to the check mark drawable
//     * @attr ref android.R.styleable#CheckedTextView_checkMarkTint
//     * @see #setCheckMarkTintList(ColorStateList)
//     */
//    @Nullable
//    public ColorStateList getCheckMarkTintList() {
//        return mCheckMarkTintList;
//    }
//
//    /**
//     * Specifies the blending mode used to apply the tint specified by
//     * {@link #setCheckMarkTintList(ColorStateList)} to the check mark
//     * drawable. The default mode is {@link PorterDuff.Mode#SRC_IN}.
//     *
//     * @param tintMode the blending mode used to apply the tint, may be
//     *                 {@code null} to clear tint
//     * @attr ref android.R.styleable#CheckedTextView_checkMarkTintMode
//     * @see #setCheckMarkTintList(ColorStateList)
//     * @see Drawable#setTintMode(PorterDuff.Mode)
//     */
//    public void setCheckMarkTintMode(@Nullable PorterDuff.Mode tintMode) {
//        mCheckMarkTintMode = tintMode;
//        mHasCheckMarkTintMode = true;
//
//        applyCheckMarkTint();
//    }
//
//    /**
//     * Returns the blending mode used to apply the tint to the check mark
//     * drawable, if specified.
//     *
//     * @return the blending mode used to apply the tint to the check mark
//     *         drawable
//     * @attr ref android.R.styleable#CheckedTextView_checkMarkTintMode
//     * @see #setCheckMarkTintMode(PorterDuff.Mode)
//     */
//    @Nullable
//    public PorterDuff.Mode getCheckMarkTintMode() {
//        return mCheckMarkTintMode;
//    }
//
//    private void applyCheckMarkTint() {
//        if (mCheckMarkDrawable != null && (mHasCheckMarkTint || mHasCheckMarkTintMode)) {
//            mCheckMarkDrawable = mCheckMarkDrawable.mutate();
//
//            if (mHasCheckMarkTint) {
//                mCheckMarkDrawable.setTintList(mCheckMarkTintList);
//            }
//
//            if (mHasCheckMarkTintMode) {
//                mCheckMarkDrawable.setTintMode(mCheckMarkTintMode);
//            }
//
//            // The drawable (or one of its children) may not have been
//            // stateful before applying the tint, so let's try again.
//            if (mCheckMarkDrawable.isStateful()) {
//                mCheckMarkDrawable.setState(getDrawableState());
//            }
//        }
//    }
//
//    @RemotableViewMethod
//    @Override
//    public void setVisibility(int visibility) {
//        super.setVisibility(visibility);
//
//        if (mCheckMarkDrawable != null) {
//            mCheckMarkDrawable.setVisible(visibility == VISIBLE, false);
//        }
//    }
//
//    @Override
//    public void jumpDrawablesToCurrentState() {
//        super.jumpDrawablesToCurrentState();
//
//        if (mCheckMarkDrawable != null) {
//            mCheckMarkDrawable.jumpToCurrentState();
//        }
//    }
//
//    @Override
//    protected boolean verifyDrawable(Drawable who) {
//        return who == mCheckMarkDrawable || super.verifyDrawable(who);
//    }
//
//    /**
//     * Gets the checkmark drawable
//     *
//     * @return The drawable use to represent the checkmark, if any.
//     *
//     * @see #setCheckMarkDrawable(Drawable)
//     * @see #setCheckMarkDrawable(int)
//     *
//     * @attr ref android.R.styleable#CheckedTextView_checkMark
//     */
//    public Drawable getCheckMarkDrawable() {
//        return mCheckMarkDrawable;
//    }
//
//    /**
//     * @hide
//     */
//    @Override
//    protected void internalSetPadding(int left, int top, int right, int bottom) {
//        super.internalSetPadding(left, top, right, bottom);
//        setBasePadding(isCheckMarkAtStart());
//    }
//
//    @Override
//    public void onRtlPropertiesChanged(int layoutDirection) {
//        super.onRtlPropertiesChanged(layoutDirection);
//        updatePadding();
//    }
//
//    private void updatePadding() {
//        resetPaddingToInitialValues();
//        int newPadding = (mCheckMarkDrawable != null) ?
//                mCheckMarkWidth + mBasePadding : mBasePadding;
//        if (isCheckMarkAtStart()) {
//            mNeedRequestlayout |= (mPaddingLeft != newPadding);
//            mPaddingLeft = newPadding;
//        } else {
//            mNeedRequestlayout |= (mPaddingRight != newPadding);
//            mPaddingRight = newPadding;
//        }
//        if (mNeedRequestlayout) {
//            requestLayout();
//            mNeedRequestlayout = false;
//        }
//    }
//
//    private void setBasePadding(boolean checkmarkAtStart) {
//        if (checkmarkAtStart) {
//            mBasePadding = mPaddingLeft;
//        } else {
//            mBasePadding = mPaddingRight;
//        }
//    }
//
//    private boolean isCheckMarkAtStart() {
//        final int gravity = Gravity.getAbsoluteGravity(mCheckMarkGravity, getLayoutDirection());
//        final int hgrav = gravity & Gravity.HORIZONTAL_GRAVITY_MASK;
//        return hgrav == Gravity.LEFT;
//    }
//
//    @Override
//    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
//
//        final Drawable checkMarkDrawable = mCheckMarkDrawable;
//        if (checkMarkDrawable != null) {
//            final int verticalGravity = getGravity() & Gravity.VERTICAL_GRAVITY_MASK;
//            final int height = checkMarkDrawable.getIntrinsicHeight();
//
//            int y = 0;
//
//            switch (verticalGravity) {
//                case Gravity.BOTTOM:
//                    y = getHeight() - height;
//                    break;
//                case Gravity.CENTER_VERTICAL:
//                    y = (getHeight() - height) / 2;
//                    break;
//            }
//
//            final boolean checkMarkAtStart = isCheckMarkAtStart();
//            final int width = getWidth();
//            final int top = y;
//            final int bottom = top + height;
//            final int left;
//            final int right;
//            if (checkMarkAtStart) {
//                left = mBasePadding;
//                right = left + mCheckMarkWidth;
//            } else {
//                right = width - mBasePadding;
//                left = right - mCheckMarkWidth;
//            }
//            checkMarkDrawable.setBounds(mScrollX + left, top, mScrollX + right, bottom);
//            checkMarkDrawable.draw(canvas);
//
//            final Drawable background = getBackground();
//            if (background != null) {
//                background.setHotspotBounds(mScrollX + left, top, mScrollX + right, bottom);
//            }
//        }
//    }
//
//    @Override
//    protected int[] onCreateDrawableState(int extraSpace) {
//        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
//        if (isChecked()) {
//            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
//        }
//        return drawableState;
//    }
//
//    @Override
//    protected void drawableStateChanged() {
//        super.drawableStateChanged();
//
//        if (mCheckMarkDrawable != null) {
//            int[] myDrawableState = getDrawableState();
//
//            // Set the state of the Drawable
//            mCheckMarkDrawable.setState(myDrawableState);
//
//            invalidate();
//        }
//    }
//
//    @Override
//    public void drawableHotspotChanged(float x, float y) {
//        super.drawableHotspotChanged(x, y);
//
//        if (mCheckMarkDrawable != null) {
//            mCheckMarkDrawable.setHotspot(x, y);
//        }
//    }
//
//    @Override
//    public CharSequence getAccessibilityClassName() {
//        return CheckedTextView.class.getName();
//    }
//
//    /** @hide */
//    @Override
//    public void onInitializeAccessibilityEventInternal(AccessibilityEvent event) {
//        super.onInitializeAccessibilityEventInternal(event);
//        event.setChecked(mChecked);
//    }
//
//    /** @hide */
//    @Override
//    public void onInitializeAccessibilityNodeInfoInternal(AccessibilityNodeInfo info) {
//        super.onInitializeAccessibilityNodeInfoInternal(info);
//        info.setCheckable(true);
//        info.setChecked(mChecked);
//    }
//
//    /** @hide */
//    @Override
//    protected void encodeProperties(@NonNull ViewHierarchyEncoder stream) {
//        super.encodeProperties(stream);
//        stream.addProperty("text:checked", isChecked());
//    }
//}

