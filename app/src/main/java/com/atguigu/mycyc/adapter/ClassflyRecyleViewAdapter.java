package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.ClassfilyBean;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 徐达
 * on 2016/8/26 on 14:24.
 * 作用:
 */
public class ClassflyRecyleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int HOT_PRODUCT = 0;
    private static final int CHILD = 1;
    private final Context mContext;
    //需要显示的数据对象
    private final ClassfilyBean.ResultBean resultBean;

    //热卖推荐的商品列表
    private List<ClassfilyBean.ResultBean.HotProductListBean> hot_product_list;
    //常用分类展示的数据
    private List<ClassfilyBean.ResultBean.ChildBean> child;

    public ClassflyRecyleViewAdapter(Context mContext, ClassfilyBean.ResultBean resultBean) {
        this.mContext = mContext;
        this.resultBean = resultBean;
    }

    public int getItemViewType(int position) {
        if (position < 1) {
            return position;
        } else {

            return 1;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HOT_PRODUCT://热卖推荐
                View itemView0 = LayoutInflater.from(mContext).inflate(R.layout.item_classfily_hot_buy, parent, false);
                return new ViewHolder0(itemView0);

            case CHILD://常用分类
                View itemView1 = View.inflate(mContext, R.layout.item_classfily_recyview, null);
                return new ViewHolder1(itemView1);

        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {//热卖推荐
            //获取集合数据
            hot_product_list = resultBean.getHot_product_list();
            //根据集合数据装配horziontialview
            //设置图片和文字
            setHotProducutData(holder);

        } else {//常用分类
            //获取集合数据
            child = resultBean.getChild();
            //设置图片
            Glide.with(mContext)
                    .load(child.get(position-1).getPic())
                            // .placeholder(R.drawable.new_img_loading_1)
                    .into(((ViewHolder1) holder).cardImg0);
            //设置当前价格
            ((ViewHolder1) holder).tvPrice0.setText( child.get(position-1).getName());
        }


    }

    /**
     * 设置热卖推荐的商品
     *
     * @param holder
     */
    private void setHotProducutData(RecyclerView.ViewHolder holder) {
        for (int i = 0; i < hot_product_list.size(); i++) {
            View view = View.inflate(mContext, R.layout.classfily_hot_recommend_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.card_img0);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price0);

            //设置图片
            Glide.with(mContext)
                    .load(hot_product_list.get(i).getFigure())
                            // .placeholder(R.drawable.new_img_loading_1)
                    .into(iv);
            //设置当前价格
            tv_price.setText("¥" + hot_product_list.get(i).getCover_price());
            //把item加入到linearlayout中
            ((ViewHolder0) holder).llHotRecommend.addView(view);
        }

    }

    @Override
    public int getItemCount() {
        return resultBean.getChild() == null ? 0 : resultBean.getChild().size() + 1;
    }

    static public class ViewHolder0 extends RecyclerView.ViewHolder {
        @Bind(R.id.ll_hot_recommend)
        LinearLayout llHotRecommend;

        ViewHolder0(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static public class ViewHolder1 extends RecyclerView.ViewHolder {

        @Bind(R.id.card_img0)
        ImageView cardImg0;
        @Bind(R.id.tv_price0)
        TextView tvPrice0;
        public ViewHolder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
