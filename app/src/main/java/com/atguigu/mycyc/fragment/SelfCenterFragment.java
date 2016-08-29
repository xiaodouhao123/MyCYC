package com.atguigu.mycyc.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.activity.LoginActivity;
import com.atguigu.mycyc.activity.MessageCenterActivity;
import com.atguigu.mycyc.base.BaseFragment;
import com.atguigu.mycyc.view.MyScrollView;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by 徐达
 * on 2016/8/23 on 19:46.
 * 作用:
 */
public class SelfCenterFragment extends BaseFragment {


    @Bind(R.id.iv_user_icon)
    ImageView ivUserIcon;
    @Bind(R.id.btn_login_register)
    TextView btnLoginRegister;
    @Bind(R.id.tv_btn_all_orders)
    TextView tvBtnAllOrders;
    @Bind(R.id.ib_order)
    ImageButton ibOrder;
    @Bind(R.id.rel_user_order)
    RelativeLayout relUserOrder;
    @Bind(R.id.view_status1)
    View viewStatus1;
    @Bind(R.id.tv_order_count_status1)
    TextView tvOrderCountStatus1;
    @Bind(R.id.lay_btn_order_status1)
    RelativeLayout layBtnOrderStatus1;
    @Bind(R.id.view_status2)
    View viewStatus2;
    @Bind(R.id.tv_order_count_status2)
    TextView tvOrderCountStatus2;
    @Bind(R.id.lay_btn_order_status2)
    RelativeLayout layBtnOrderStatus2;
    @Bind(R.id.view_status3)
    View viewStatus3;
    @Bind(R.id.tv_order_count_status3)
    TextView tvOrderCountStatus3;
    @Bind(R.id.lay_btn_order_status3)
    RelativeLayout layBtnOrderStatus3;
    @Bind(R.id.view_status4)
    View viewStatus4;
    @Bind(R.id.lay_btn_order_status4)
    RelativeLayout layBtnOrderStatus4;
    @Bind(R.id.rel_user_order_no)
    LinearLayout relUserOrderNo;
    @Bind(R.id.iv_icon_address)
    ImageView ivIconAddress;
    @Bind(R.id.rel_user_address)
    RelativeLayout relUserAddress;
    @Bind(R.id.iv_icon_collection)
    ImageView ivIconCollection;
    @Bind(R.id.rel_user_collection)
    RelativeLayout relUserCollection;
    @Bind(R.id.iv_icon_coupon)
    ImageView ivIconCoupon;
    @Bind(R.id.rel_user_coupon)
    RelativeLayout relUserCoupon;
    @Bind(R.id.iv_icon_score)
    ImageView ivIconScore;
    @Bind(R.id.rel_user_score)
    RelativeLayout relUserScore;
    @Bind(R.id.iv_icon_prize)
    ImageView ivIconPrize;
    @Bind(R.id.rel_user_prize)
    RelativeLayout relUserPrize;
    @Bind(R.id.iv_icon_ticket)
    ImageView ivIconTicket;
    @Bind(R.id.rel_user_ticket)
    RelativeLayout relUserTicket;
    @Bind(R.id.iv_icon_invitation)
    ImageView ivIconInvitation;
    @Bind(R.id.rel_user_invitation)
    RelativeLayout relUserInvitation;
    @Bind(R.id.iv_icon_callCenter)
    ImageView ivIconCallCenter;
    @Bind(R.id.rel_user_callCenter)
    RelativeLayout relUserCallCenter;
    @Bind(R.id.iv_icon_complain)
    ImageView ivIconComplain;
    @Bind(R.id.rel_user_complain)
    RelativeLayout relUserComplain;
    @Bind(R.id.sc_new_user_scroll)
    MyScrollView scNewUserScroll;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_ok)
    ImageView btnOk;
    @Bind(R.id.iv_btn_right)
    ImageView ivBtnRight;
    @Bind(R.id.tv_msg_num)
    TextView tvMsgNum;
    @Bind(R.id.rel_new_common_bar)
    RelativeLayout relNewCommonBar;

    @Override
    protected void initData(String content) {
        tvTitle.setTextColor(Color.argb(0, 255, 255, 255));
        tvTitle.setClickable(false);
        //设置背景透明度
        relNewCommonBar.setBackgroundColor(Color.argb(0, 237, 63, 63));
        initListener();
    }

    private void initListener() {
        ivBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MessageCenterActivity.class);
                startActivity(intent);
            }
        });
        scNewUserScroll.setOnScrollChangedListener(new MyScrollView.OnScrollChangedListener() {
            @Override
            public void OnScrollChanged(ScrollView who, int l, int t, int oldl, int oldt) {
                int headerHeight = relNewCommonBar.getHeight() * 2;
                final float ratio = (float) Math.min(Math.max(t, 0), headerHeight) / headerHeight;
                //设置字体颜色
                tvTitle.setTextColor(Color.argb((int) (ratio * 255), 255, 255, 255));
                //设置背景透明度
                relNewCommonBar.setBackgroundColor(Color.argb((int) (ratio * 255), 237, 63, 63));
            }
        });
    }

    @OnClick({R.id.iv_user_icon, R.id.btn_login_register, R.id.tv_btn_all_orders, R.id.rel_user_order, R.id.lay_btn_order_status1, R.id.lay_btn_order_status2, R.id.lay_btn_order_status3, R.id.lay_btn_order_status4, R.id.rel_user_address, R.id.rel_user_collection, R.id.rel_user_coupon, R.id.rel_user_score, R.id.rel_user_prize, R.id.rel_user_ticket, R.id.rel_user_invitation, R.id.rel_user_callCenter, R.id.rel_user_complain})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user_icon:
                break;
            case R.id.btn_login_register:
                break;
            case R.id.tv_btn_all_orders:
                break;
            case R.id.lay_btn_order_status1:
                break;
            case R.id.lay_btn_order_status2:
                break;
            case R.id.lay_btn_order_status3:
                break;
            case R.id.lay_btn_order_status4:
                break;
            case R.id.rel_user_address:
                break;
            case R.id.rel_user_collection:
                break;
            case R.id.rel_user_coupon:
                break;
            case R.id.rel_user_score:
                break;
            case R.id.rel_user_prize:
                break;
            case R.id.rel_user_ticket:
                break;
            case R.id.rel_user_invitation:
                break;
            case R.id.rel_user_callCenter:
                break;
            case R.id.rel_user_complain:
                break;
        }
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    /**
     * 提供网络地址
     *
     * @return
     */
    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_selfcenter;
    }



}
