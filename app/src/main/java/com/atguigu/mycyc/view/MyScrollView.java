package com.atguigu.mycyc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Keith
 * Create time 2016/8/29.
 * Simple Introduce:自定义ScrollView
 */
public class MyScrollView extends ScrollView {

    public interface OnScrollChangedListener {
        void OnScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
    }

    private OnScrollChangedListener onScrollChangedListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public MyScrollView(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollChangedListener != null) {
            onScrollChangedListener.OnScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public void setOnScrollChangedListener(OnScrollChangedListener onScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener;
    }
}

