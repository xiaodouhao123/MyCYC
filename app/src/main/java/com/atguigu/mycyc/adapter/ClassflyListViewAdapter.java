package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atguigu.mycyc.R;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/26 on 13:40.
 * 作用:
 */
public class ClassflyListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> list;


    int mSelect = 0;   //选中项

    public ClassflyListViewAdapter(Context mContext, List<String> list) {
        this.mContext = mContext;
        this.list = list;
    }


    public void 刷新(int position) {
        changeSelected(position);
    }

    public void changeSelected(int positon) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = View.inflate(mContext, R.layout.text_view, null);
            TextView tv= (TextView) convertView.findViewById(R.id.tv);
            tv.setText(list.get(position));
        }
        if (mSelect == position) {
            convertView.setBackgroundResource(R.color.gray_light);  //选中项背景
        } else {
            convertView.setBackgroundResource(R.color.gray_dark_bg);  //其他项背景
        }
        return convertView;
    }
}
