package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.LabelBean;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/26 on 20:01.
 * 作用:
 */
public class TableAdapter extends RecyclerView.Adapter<TableAdapter.MyViewHolder> {
    private final Context mContext;
    private List<LabelBean.ResultBean> result;

    public TableAdapter(Context mContext, List<LabelBean.ResultBean> result) {
        this.mContext=mContext;
        this.result=result;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView= LayoutInflater.from(mContext).inflate(R.layout.item_table,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //绑定数据
        holder.tv1.setText(result.get(position).getName());
        //设置颜色
        if(position%9==0||position%9==5||position%9==7) {//黄色
            holder.tv1.setTextColor(Color.YELLOW);
        }
        if(position%9==1||position%9==3||position%9==8) {//蓝色
            holder.tv1.setTextColor(Color.BLUE);
        }
        if(position%9==2||position%9==4||position%9==6) {//红色
            holder.tv1.setTextColor(Color.RED);
        }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1;
        public MyViewHolder(View itemView) {
            super(itemView);
            tv1 = (TextView) itemView.findViewById(R.id.tv1);
        }
    }
}
