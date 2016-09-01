package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mycyc.R;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/31 on 20:30.
 * 作用:
 */
public class PriceFillterAdapter extends BaseAdapter {
    private final Context mContext;
    private final List<String> list;
    int mSelect = 0;   //选中项

    public void setChooseList(boolean chooseList) {
        this.chooseList = chooseList;
    }

    private boolean chooseList;
    public void setmSelect(int mSelect) {
        this.mSelect = mSelect;
    }

    public PriceFillterAdapter(Context context, List<String> list) {
        this.mContext = context;
        this.list = list;
    }

    public void changeSelected(int positon, boolean chooseList) { //刷新方法
        if (positon != mSelect) {
            mSelect = positon;
        }
        this.chooseList = chooseList;
            notifyDataSetChanged();
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
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_fillter_price, null);
            holder = new ViewHolder();
            holder.iv_status = (ImageView) convertView.findViewById(R.id.iv_status);
            holder.tv_price_range = (TextView) convertView.findViewById(R.id.tv_price_range);
            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_price_range.setText(list.get(position));
//        if(mSelect==list.size()) {//listviewbei没被选择,隐藏对勾
//            holder.tv_price_range.setTextColor(Color.BLACK);
//            holder.iv_status.setVisibility(View.INVISIBLE);
//        }else {//listviewbei选择
//            if (position == mSelect) {
//                holder.tv_price_range.setTextColor(Color.RED);
//                holder.iv_status.setVisibility(View.VISIBLE);
//            } else {
//                holder.tv_price_range.setTextColor(Color.BLACK);
//                holder.iv_status.setVisibility(View.INVISIBLE);
//            }
//        }


        if(!chooseList) {//listviewbei没被选择,隐藏对勾
            holder.tv_price_range.setTextColor(Color.BLACK);
            holder.iv_status.setVisibility(View.INVISIBLE);
        }else {//listviewbei选择
            if (position == mSelect) {
                holder.tv_price_range.setTextColor(Color.RED);
                holder.iv_status.setVisibility(View.VISIBLE);
            } else {
                holder.tv_price_range.setTextColor(Color.BLACK);
                holder.iv_status.setVisibility(View.INVISIBLE);
            }
        }

        return convertView;
    }

//    public void adapterChange(boolean chooseList) {
//        this.chooseList=chooseList;
//        notifyDataSetChanged();
//    }

    public class ViewHolder {
        TextView tv_price_range;
        ImageView iv_status;
    }
}
