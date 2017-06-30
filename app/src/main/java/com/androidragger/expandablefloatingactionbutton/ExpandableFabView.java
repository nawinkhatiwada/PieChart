package com.androidragger.expandablefloatingactionbutton;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Nabin on 6/23/17.
 */

public class ExpandableFabView extends RelativeLayout {
    private LayoutInflater infater;
    private TimeInterpolator expandInterpolator;
    private TimeInterpolator collapseInterpolator;
    private long animationDuration;
    private boolean animating;
    private boolean expanded;
    private final int maxWidth;
    private OnExpandListener onExpandListener;

    public ExpandableFabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (!isInEditMode())
            this.infater = LayoutInflater.from(context);

        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView, defStyle, 0);
        this.animationDuration = attributes.getInt(R.styleable.ExpandableTextView_animation_duration, BuildConfig.DEFAULT_ANIMATION_DURATION);
        attributes.recycle();
        this.maxWidth = this.getWidth();

        // create default interpolators
        this.expandInterpolator = new AccelerateDecelerateInterpolator();
        this.collapseInterpolator = new AccelerateDecelerateInterpolator();
    }

    public boolean expand() {

        if (!this.expanded && !this.animating && this.maxWidth >= 0) {
            this.animating = true;
        }
        return true;
    }

    public void setOnExpandListener(final OnExpandListener onExpandListener)
    {
        this.onExpandListener = onExpandListener;
    }

    /**
     * Returns the {@link OnExpandListener}.
     * @return the listener.
     */
    public OnExpandListener getOnExpandListener()
    {
        return this.onExpandListener;
    }

    public interface OnExpandListener
    {
        /**
         * The {@link TextView} is being expanded.
         * @param view the textview
         */
        void onExpand(TextView view);

        /**
         * The {@link TextView} is being collapsed.
         * @param view the textview
         */
        void onCollapse(TextView view);
    }



}
