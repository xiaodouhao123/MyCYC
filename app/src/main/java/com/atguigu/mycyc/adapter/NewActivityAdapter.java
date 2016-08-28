package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.HomeYxc;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/28 on 15:48.
 * 作用:
 */
public class NewActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context mContext;
    private final List<HomeYxc.ResultBean.PageDataBean> page_data;

    public NewActivityAdapter(Context context, List<HomeYxc.ResultBean.PageDataBean> page_data) {
        mContext = context;
        this.page_data = page_data;
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0) {
           return 0;
        }
        return 1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==0) {
            View itemView = LayoutInflater.from(mContext).inflate(R.layout.new_header_view_empty, parent, false);
            return new MyViewHoler0(itemView);
        }else {

            View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_item_good_view, parent, false);
            return new MyViewHoler1(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position!=0) {
           //设置数据
            setData((MyViewHoler1)holder, position);
        }
    }



    private void setData(MyViewHoler1 holder, int position) {
        //获取当前数据
        HomeYxc.ResultBean.PageDataBean pageDataBean = page_data.get(position-1);
        //设置图片
        Glide
                .with(mContext)
                .load(pageDataBean.getFigure())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                .into(holder.iv);
        //设置名字
        holder.tv1.setText(pageDataBean.getName());
        //显示当前价格
        holder.tv2.setText(pageDataBean.getCover_price());
        //显示原始价格
        holder.tv_origin_price.setText(pageDataBean.getOrigin_price());
    }

    @Override
    public int getItemCount() {
        return page_data == null ? 0 : page_data.size()+1;
    }

    public class MyViewHoler1 extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv1;
        TextView tv2;
        TextView tv_origin_price;
        public MyViewHoler1(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.card_img);
            tv1 = (TextView) itemView.findViewById(R.id.card_tv1);
            tv2 = (TextView) itemView.findViewById(R.id.card_tv2);
            tv_origin_price = (TextView) itemView.findViewById(R.id.tv_origin_price);
        }
    }
    public class MyViewHoler0 extends RecyclerView.ViewHolder {

        public MyViewHoler0(View itemView) {
            super(itemView);

        }
    }
}
