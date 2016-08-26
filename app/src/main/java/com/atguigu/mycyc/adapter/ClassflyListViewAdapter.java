package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.atguigu.mycyc.bean.ClassfilyBean;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/26 on 13:40.
 * 作用:
 */
public class ClassflyListViewAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<ClassfilyBean.ResultBean> resultBeans;

    public ClassflyListViewAdapter(Context mContext, List<ClassfilyBean.ResultBean> result) {
        this.mContext = mContext;
        resultBeans = result;
    }

    @Override
    public int getCount() {
        return resultBeans==null?0:resultBeans.size();
    }

    @Override
    public Object getItem(int position) {
        return resultBeans.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return convertView;
    }
}
