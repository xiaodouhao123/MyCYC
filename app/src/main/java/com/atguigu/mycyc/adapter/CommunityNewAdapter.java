package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.CommunityBean;
import com.atguigu.mycyc.utils.TimeUtil;
import com.atguigu.mycyc.utils.UIUtils;
import com.atguigu.mycyc.view.FlowLayout;
import com.bumptech.glide.Glide;
import com.opendanmaku.DanmakuItem;
import com.opendanmaku.DanmakuView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by 徐达
 * on 2016/8/27 on 15:06.
 * 作用:吱吱最新界面的adapter
 */
public class CommunityNewAdapter extends RecyclerView.Adapter<CommunityNewAdapter.MyViewHolder> {


    private Context mContext;
    private List<CommunityBean.ResultBean> result;

    public CommunityNewAdapter(Context context, List<CommunityBean.ResultBean> result) {
        this.mContext = context;
        this.result = result;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.adapter_community_articles_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        //得到相应位置的数据
        CommunityBean.ResultBean resultBean = result.get(position);
        //设置名字
        holder.authorNameTv.setText(resultBean.getUsername());
        //设置头像
        Glide.with(mContext)
                .load(resultBean.getAvatar())
                        // .placeholder(R.drawable.new_img_loading_1)
                .into(holder.authorIconIv);
        //设置图片
        Glide.with(mContext)
                .load(resultBean.getFigure())
                        // .placeholder(R.drawable.new_img_loading_1)
                .into(holder.articleImgView);
        //设置时间显示
        holder.articleTimeTv.setText(TimeUtil.getSmartDate(Long.parseLong(resultBean.getAdd_time())));
        //设置喜欢数
        holder.likeTv.setText(resultBean.getLikes());
        //设置评论数
        holder.commentTv.setText(resultBean.getComments());
        //填充流式布局
        setFrameLayout(holder, resultBean);
        //弹幕
        showDanmaku(holder, resultBean);
    }

    private void showDanmaku(MyViewHolder holder, CommunityBean.ResultBean resultBean) {
        List<String> comment_list = resultBean.getComment_list();
        if (comment_list != null && comment_list.size() > 0) {//有评论
            holder.danmakuView.setVisibility(View.VISIBLE);
            //List<IDanmakuItem> list=new ArrayList<>();
            //holder.danmakuView.addItem(list,true);
            for (int i = 0; i < comment_list.size(); i++) {

                holder.danmakuView.addItem(new DanmakuItem(mContext, comment_list.get(i), holder.danmakuView.getWidth()));
            }
            holder.danmakuView.show();
        } else {
            holder.danmakuView.setVisibility(View.GONE);
        }
    }

    private void setFrameLayout(MyViewHolder holder, CommunityBean.ResultBean resultBean) {
          holder.fl_post_label.removeAllViews();
        if (!resultBean.getIs_top().equals("0")) {//存在置顶

            ImageView view_top = new ImageView(mContext);
            view_top.setBackgroundResource(R.drawable.zhizhi_post_hot);
            ViewGroup.MarginLayoutParams params1 = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params1.leftMargin = UIUtils.dp2px(2);
            params1.rightMargin = UIUtils.dp2px(2);
            params1.topMargin = UIUtils.dp2px(2);
            params1.bottomMargin = UIUtils.dp2px(2);
            view_top.setLayoutParams(params1);
            //设置背景颜色
            //view_top.setBackgroundColor(mContext.getResources().getColor(R.color.tv_color_red));
            //设置内边距
            //int padding1 = UIUtils.dp2px(4);

            //view_top.setPadding(padding1, padding1, padding1, padding1);
            holder.fl_post_label.addView(view_top);
        }
        if (!resultBean.getIs_hot().equals("0")) {//存在置顶

            ImageView view_hot = new ImageView(mContext);
            view_hot.setBackgroundResource(R.drawable.zhizhi_post_top);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = UIUtils.dp2px(2);
            params.rightMargin = UIUtils.dp2px(2);
            params.topMargin = UIUtils.dp2px(2);
            params.bottomMargin = UIUtils.dp2px(2);
            view_hot.setLayoutParams(params);
            //设置背景颜色
            //view_hot.setBackgroundColor(mContext.getResources().getColor(R.color.holo_blue_light));
            //设置内边距
            //int padding = UIUtils.dp2px(4);

           // view_hot.setPadding(padding, padding, padding, padding);
            holder.fl_post_label.addView(view_hot);
        }
        if (!resultBean.getIs_essence().equals("0")) {//存在置顶

            ImageView view_essence = new ImageView(mContext);
            view_essence.setBackgroundResource(R.drawable.zhizhi_post_cream);
            ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = UIUtils.dp2px(2);
            params.rightMargin = UIUtils.dp2px(2);
            params.topMargin = UIUtils.dp2px(2);
            params.bottomMargin = UIUtils.dp2px(2);
            view_essence.setLayoutParams(params);
            //设置背景颜色
            //view_essence.setBackgroundColor(mContext.getResources().getColor(R.color.holo_blue_light));
            //设置内边距
            //int padding = UIUtils.dp2px(4);

            //view_essence.setPadding(padding, padding, padding, padding);
            holder.fl_post_label.addView(view_essence);
        }

    }

    @Override
    public int getItemCount() {
        return result == null ? 0 : result.size();
    }

    public void setResult(List<CommunityBean.ResultBean> result) {
        this.result.clear();
        this.result.addAll(0, result);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.danmakuView)
        DanmakuView danmakuView;
        @Bind(R.id.author_name_tv)
        TextView authorNameTv;
        @Bind(R.id.article_time_tv)
        TextView articleTimeTv;
        @Bind(R.id.author_name_layout)
        RelativeLayout authorNameLayout;
        @Bind(R.id.article_img_view)
        ImageView articleImgView;
        @Bind(R.id.article_img_click_view)
        View articleImgClickView;
        @Bind(R.id.article_img_layout)
        FrameLayout articleImgLayout;
        @Bind(R.id.article_top_layout)
        LinearLayout articleTopLayout;
        @Bind(R.id.article_summary_tv)
        TextView articleSummaryTv;
        @Bind(R.id.comm_line)
        View commLine;
        @Bind(R.id.like_icon_iv)
        ImageView likeIconIv;
        @Bind(R.id.like_tv)
        TextView likeTv;
        @Bind(R.id.like_click_layout)
        LinearLayout likeClickLayout;
        @Bind(R.id.comment_icon_iv)
        ImageView commentIconIv;
        @Bind(R.id.comment_tv)
        TextView commentTv;
        @Bind(R.id.comment_click_layout)
        LinearLayout commentClickLayout;
        @Bind(R.id.author_icon_iv)
        ImageView authorIconIv;
        @Bind(R.id.fl_post_label)
        FlowLayout fl_post_label;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            fl_post_label = (FlowLayout) itemView.findViewById(R.id.fl_post_label);

        }
    }

    /**
     * 清除数据
     */
    public void cleanData() {
        result.clear();
        notifyItemRangeRemoved(0, result.size());
    }

    /**
     * 添加数据
     *
     * @param
     */
    public void addDate(List<CommunityBean.ResultBean> result) {
        addDate(0, result);
    }

    /**
     * 更加对应的位置加载数据
     *
     * @param position
     * @param result
     */
    public void addDate(int position, List<CommunityBean.ResultBean> result) {
        if (result != null && result.size() > 0) {
            this.result.addAll(position, result);
            // notifyItemRangeChanged(position, result.size());
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return result.size();
    }
}
