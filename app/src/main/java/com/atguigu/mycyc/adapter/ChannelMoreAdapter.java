package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.ChannelMoreBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 殴打小熊猫
 * on 2016/8/28 on 16:28.
 * 作用：
 */
public class ChannelMoreAdapter extends BaseAdapter {
    private final Context context;
    private final List<ChannelMoreBean.ResultBean> bean;

    public ChannelMoreAdapter(Context context, ChannelMoreBean channelMoreBean) {
        this.context = context;
        this.bean = channelMoreBean.getResult();
    }


    @Override
    public int getCount() {
        return bean == null ? 0 : bean.size();
    }

    @Override
    public Object getItem(int position) {
        return bean.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.channel_item, parent, false);
            holder=new MyViewHolder(convertView);
                    convertView.setTag(holder);
        }else {
            holder= (MyViewHolder) convertView.getTag();
        }


        ChannelMoreBean.ResultBean resultBean = bean.get(position);
        holder.mTvChannelItem.setText(resultBean.getChannel_name());
        Glide
                .with(context)
                .load(resultBean.getImage())
                .centerCrop()
                .fitCenter()
                //.placeholder(R.drawable.new_img_loading_11)
                .crossFade()
                .into(holder.mIvChannelItem);
        return convertView;
    }

    static class MyViewHolder {
        @Bind(R.id.iv_channel_item)
        ImageView mIvChannelItem;
        @Bind(R.id.tv_channel_item)
        TextView mTvChannelItem;
        @Bind(R.id.ll_fushicang)
        RelativeLayout mLlFushicang;

        MyViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
