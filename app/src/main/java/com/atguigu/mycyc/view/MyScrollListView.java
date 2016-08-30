package com.atguigu.mycyc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by 徐达
 * on 2016/8/30 on 15:29.
 * 作用:
 */
public class MyScrollListView extends ListView {
    public MyScrollListView(Context context) {
        super(context);
    }

    public MyScrollListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
