package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.HomeBean;
import com.atguigu.mycyc.utils.UIUtils;
import com.atguigu.mycyc.view.NoScrollGridView;
import com.atguigu.mycyc.view.ZoomOutPageTransformer;
import com.bumptech.glide.Glide;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 徐达
 * on 2016/8/24 on 14:48.
 * 作用:
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private HomeBean.ResultBean resultBeant;
    private static final int VIEW_PAGER = 0;
    private static final int LINEAR_LAYOUT = 1;
    private static final int VIEW_PAGER_ANIMATION = 2;
    private static final int HOT_BUY = 3;
    private static final int NEW_RECOMMEND = 4;
    private static final int GOODS = 5;
    private final Context mContext;
    //viewpager的集合
    private List<HomeBean.ResultBean.BannerInfoBean> banner_info;
    //导航图的集合
    private List<HomeBean.ResultBean.ChannelInfoBean> channel_info;
    //带动画效果的viewpager
    private List<HomeBean.ResultBean.ActInfoBean> act_info;
    //热卖
    private HomeBean.ResultBean.SeckillInfoBean seckill_info;
    //推荐
    private List<HomeBean.ResultBean.RecommendInfoBean> recommend_info;
    //recyview
    private List<HomeBean.ResultBean.HotInfoBean> hot_info;


    @Override
    public int getItemViewType(int position) {
        if (position < 5) {
            return position;
        } else {

            return 5;
        }

    }


    public MyRecyclerViewAdapter(Context mContext, HomeBean.ResultBean resultBeant) {
        this.resultBeant = resultBeant;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MyRecyclerViewAdapter.VIEW_PAGER://轮播图
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_home_banner_info, parent, false);
//                View view = View.inflate(mContext, R.layout.item_home_recyview, parent);

                return new ViewHolder0(view);

            case MyRecyclerViewAdapter.LINEAR_LAYOUT://频道通知
                View itemView1 = View.inflate(mContext, R.layout.item_home_channel_info, null);

                return new ViewHolder1(itemView1);

            case MyRecyclerViewAdapter.VIEW_PAGER_ANIMATION://动画vieepager
                View itemView2 = LayoutInflater.from(mContext).inflate(R.layout.item_home_act_info, parent, false);

                return new ViewHolder2(itemView2);

            case MyRecyclerViewAdapter.HOT_BUY://热卖
                View itemView3 = LayoutInflater.from(mContext).inflate(R.layout.item_home_hot_buy, parent, false);

                return new ViewHolder3(itemView3);

            case MyRecyclerViewAdapter.NEW_RECOMMEND://值键
                View itemView4 = LayoutInflater.from(mContext).inflate(R.layout.item_home_new_recommend, parent, false);
                return new ViewHolder4(itemView4);

            case MyRecyclerViewAdapter.GOODS://商品
                View itemView5 = View.inflate(mContext, R.layout.item_home_recyview, null);
                return new ViewHolder5(itemView5);


        }
//        View itemView=View.inflate(parent.getContext(), R.layout.item_home_recyview,null);
//        return new MyViewHolder(itemView);

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {//轮播图
            //获取集合数据

            banner_info = resultBeant.getBanner_info();
            //显示viewpager
            ((ViewHolder0) holder).vp_banner_info.setAdapter(new MyPagerAdapter());
            //加上viewpagerIndicator
            ((ViewHolder0) holder).cpi_indicator.setViewPager(((ViewHolder0) holder).vp_banner_info);
        } else if (position == 1) {//频道通知
            //获取集合数据

            channel_info = resultBeant.getChannel_info();
            //显示gridview
            ((ViewHolder1) holder).gv_home_channel_info.setAdapter(new MyGridViewAdapter());

        } else if (position == 2) {
            //获取集合数据

            act_info = resultBeant.getAct_info();
            //显示viewpager
            ((ViewHolder2) holder).vp_act_info.setAdapter(new MyAnimationPagerAdapter());

            ((ViewHolder2) holder).vp_act_info.setPageTransformer(true, new ZoomOutPageTransformer());
        } else if (position == 3) {
            //热卖
            seckill_info = resultBeant.getSeckill_info();

            //设置倒计时
            Long time_end = Long.parseLong(seckill_info.getEnd_time());
            Long time_start = Long.parseLong(seckill_info.getStart_time());
            UIUtils.quickTime(time_end - time_start, ((ViewHolder3) holder).tvTime2);
            //设置图片和文字
            setSeckillData(holder);

        } else if (position == 4) {
            //推荐
            recommend_info = resultBeant.getRecommend_info();

            //显示gridview
            ((ViewHolder4) holder).cv_home_recommend.setAdapter(new MyThreeGridViewAdapter());

        } else {//商品
            hot_info = resultBeant.getHot_info();
            //设置描述
            ((ViewHolder5) holder).tvRecyviewDes.setText(hot_info.get(position - 5).getName());
            //设置价格
            ((ViewHolder5) holder).tvRecyviewPrice.setText(hot_info.get(position - 5).getCover_price());
            //设置图片
            Glide
                    .with(mContext)
                    .load(hot_info.get(position - 5).getFigure())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                    .into(((ViewHolder5) holder).ivRecyviewIcon);
        }

    }


    @Override
    public int getItemCount() {
        return resultBeant.getHot_info() == null ? 0 : resultBeant.getHot_info().size() + 5;
    }

    //轮播图的viewholder
    private class ViewHolder0 extends RecyclerView.ViewHolder {
        ViewPager vp_banner_info;
        CirclePageIndicator cpi_indicator;

        public ViewHolder0(View itemView) {
            super(itemView);
            cpi_indicator = (CirclePageIndicator) itemView.findViewById(R.id.cpi_indicator);
            vp_banner_info = (ViewPager) itemView.findViewById(R.id.vp_banner_info);

        }
    }

    //频道通知的viewholder
    private class ViewHolder1 extends RecyclerView.ViewHolder {
        NoScrollGridView gv_home_channel_info;

        public ViewHolder1(View itemView) {
            super(itemView);
            gv_home_channel_info = (NoScrollGridView) itemView.findViewById(R.id.gv_home_channel_info);

        }
    }

    private class ViewHolder2 extends RecyclerView.ViewHolder {
        ViewPager vp_act_info;

        public ViewHolder2(View itemView) {
            super(itemView);
            vp_act_info = (ViewPager) itemView.findViewById(R.id.vp_act_info);
        }
    }

    static public class ViewHolder3 extends RecyclerView.ViewHolder {
        @Bind(R.id.lineView)
        View lineView;
        @Bind(R.id.tv_label)
        TextView tvLabel;
        @Bind(R.id.tv_time)
        TextView tvTime;
        @Bind(R.id.tv_time2)
        TextView tvTime2;
        @Bind(R.id.tv_more)
        TextView tvMore;

        @Bind(R.id.ll_seckill_item)
        LinearLayout ll_seckill_item;

        public ViewHolder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private class ViewHolder4 extends RecyclerView.ViewHolder {
        TextView tv_more;
        TextView tv__hot_more;
        NoScrollGridView cv_home_recommend;

        public ViewHolder4(View itemView) {
            super(itemView);
            tv_more = (TextView) itemView.findViewById(R.id.tv_more);
            tv__hot_more = (TextView) itemView.findViewById(R.id.tv__hot_more);
            cv_home_recommend = (NoScrollGridView) itemView.findViewById(R.id.cv_home_recommend);

        }
    }

    static public class ViewHolder5 extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_recyview_icon)
        ImageView ivRecyviewIcon;
        @Bind(R.id.tv_recyview_des)
        TextView tvRecyviewDes;
        @Bind(R.id.tv_recyview_price)
        TextView tvRecyviewPrice;

        public ViewHolder5(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    //三列的GridView
    class MyThreeGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return recommend_info.size();
        }

        @Override
        public Object getItem(int position) {
            return recommend_info.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_gridview_recommend_info, null);
                holder = new ViewHolder(convertView);
                holder.ivRecommendIcon = (ImageView) convertView.findViewById(R.id.iv_recommend_icon);
                holder.tvRecommendDes = (TextView) convertView.findViewById(R.id.tv_recommend_des);
                holder.tvRecommendPrice = (TextView) convertView.findViewById(R.id.tv_recommend_price);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            //设置商品描述
            holder.tvRecommendDes.setText(recommend_info.get(position).getName());
            //设置价格
            holder.tvRecommendPrice.setText(recommend_info.get(position).getCover_price());
            //设置图片
            Glide
                    .with(mContext)
                    .load(recommend_info.get(position).getFigure())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                    .into(holder.ivRecommendIcon);
            return convertView;
        }


        public class ViewHolder {
            @Bind(R.id.iv_recommend_icon)
            ImageView ivRecommendIcon;
            @Bind(R.id.tv_recommend_des)
            TextView tvRecommendDes;
            @Bind(R.id.tv_recommend_price)
            TextView tvRecommendPrice;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }

    class MyGridViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return channel_info.size();
        }

        @Override
        public Object getItem(int position) {
            return channel_info.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = View.inflate(mContext, R.layout.item_gridview_channel_info, null);
                holder = new ViewHolder();
                holder.iv_channel_info = (ImageView) convertView.findViewById(R.id.iv_channel_info);
                holder.tv_channel_info = (TextView) convertView.findViewById(R.id.tv_channel_info);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.tv_channel_info.setText(channel_info.get(position).getChannel_name());
            //设置图片
            Glide
                    .with(mContext)
                    .load(channel_info.get(position).getImage())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                    .into(holder.iv_channel_info);
            return convertView;
        }

        public class ViewHolder {
            private ImageView iv_channel_info;
            private TextView tv_channel_info;
        }
    }

    class MyAnimationPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return act_info.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            Glide
                    .with(mContext)
                    .load(act_info.get(position).getIcon_url())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                    .into(imageView);
            container.addView(imageView);

            return imageView;


        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return banner_info.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            ImageView imageView = new ImageView(mContext);
            Glide
                    .with(mContext)
                    .load(banner_info.get(position).getImage())
//                            .centerCrop()
//                            .placeholder(R.drawable.splash)
//                             .crossFade()
                    .into(imageView);
            container.addView(imageView);

            return imageView;

            //return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }


    //    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView tv_home;
//        public MyViewHolder(View itemView) {
//            super(itemView);
//            tv_home = (TextView) itemView.findViewById(R.id.tv_home);
//        }
//    }
    private void setSeckillData(RecyclerView.ViewHolder holder) {

        for (int i = 0; i < seckill_info.getList().size(); i++) {
            View view = View.inflate(mContext, R.layout.home_seckill_item, null);
            ImageView iv = (ImageView) view.findViewById(R.id.card_img0);
            TextView tv_price = (TextView) view.findViewById(R.id.tv_price0);
            TextView tv_orign_price = (TextView) view.findViewById(R.id.tv_origin_price0);
            //设置图片
            Glide.with(mContext)
                    .load(seckill_info.getList().get(i).getFigure())
                            // .placeholder(R.drawable.new_img_loading_1)
                    .into(iv);
            //设置当前价格
            tv_price.setText(seckill_info.getList().get(i).getCover_price());
            //让当前价格的字体更清晰些
            tv_price.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
            //设置原始价格
            tv_orign_price.setText(seckill_info.getList().get(i).getOrigin_price());
            //设置原始价格的下划线
            tv_orign_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            //把item加入到linearlayout中
            ((ViewHolder3) holder).ll_seckill_item.addView(view);
        }

//        //1.设置图片
//        String imageUrl0 = seckill_info.getList().get(0).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl0)
//                        // .placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg0);
//        String imageUrl1 = seckill_info.getList().get(1).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl1)
//                        //.placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg1);
//        String imageUrl2 = seckill_info.getList().get(2).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl2)
//                        //.placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg2);
//        String imageUrl3 = seckill_info.getList().get(3).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl3)
//                        //.placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg3);
//        String imageUrl4 = seckill_info.getList().get(4).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl4)
//                        //.placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg4);
//        String imageUrl5 = seckill_info.getList().get(5).getFigure();
//        Glide.with(mContext)
//                .load(imageUrl5)
//                        //.placeholder(R.drawable.new_img_loading_1)
//                .into(((ViewHolder3) holder).cardImg5);
//
//        //2.设置字体
//        //当前价格
//        ((ViewHolder3) holder).tvPrice0.setText(seckill_info.getList().get(0).getCover_price());
//        ((ViewHolder3) holder).tvPrice1.setText(seckill_info.getList().get(1).getCover_price());
//        ((ViewHolder3) holder).tvPrice2.setText(seckill_info.getList().get(2).getCover_price());
//        ((ViewHolder3) holder).tvPrice3.setText(seckill_info.getList().get(3).getCover_price());
//        ((ViewHolder3) holder).tvPrice4.setText(seckill_info.getList().get(4).getCover_price());
//        ((ViewHolder3) holder).tvPrice5.setText(seckill_info.getList().get(5).getCover_price());
//        //让当前价格字体更清晰一些
//        ((ViewHolder3) holder).tvPrice0.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//        ((ViewHolder3) holder).tvPrice1.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//        ((ViewHolder3) holder).tvPrice2.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//        ((ViewHolder3) holder).tvPrice3.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//        ((ViewHolder3) holder).tvPrice4.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//        ((ViewHolder3) holder).tvPrice5.getPaint().setFlags(Paint.ANTI_ALIAS_FLAG);
//
//
//        //原始价格
//        ((ViewHolder3) holder).tvOriginPrice0.setText(seckill_info.getList().get(0).getOrigin_price());
//        ((ViewHolder3) holder).tvOriginPrice1.setText(seckill_info.getList().get(1).getOrigin_price());
//        ((ViewHolder3) holder).tvOriginPrice2.setText(seckill_info.getList().get(2).getOrigin_price());
//        ((ViewHolder3) holder).tvOriginPrice3.setText(seckill_info.getList().get(3).getOrigin_price());
//        ((ViewHolder3) holder).tvOriginPrice4.setText(seckill_info.getList().get(4).getOrigin_price());
//        ((ViewHolder3) holder).tvOriginPrice5.setText(seckill_info.getList().get(5).getOrigin_price());
//        //设置原始价格的划线
//        ((ViewHolder3) holder).tvOriginPrice0.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        ((ViewHolder3) holder).tvOriginPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        ((ViewHolder3) holder).tvOriginPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        ((ViewHolder3) holder).tvOriginPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        ((ViewHolder3) holder).tvOriginPrice4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        ((ViewHolder3) holder).tvOriginPrice5.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }


}
