package com.atguigu.mycyc.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * Created by 徐达
 * on 2016/8/23 on 19:46.
 * 作用:
 */
public class SqueakFragment extends BaseFragment {


    @Bind(R.id.own_icon_iv)
    ImageView ownIconIv;
    @Bind(R.id.message_icon_iv)
    ImageView messageIconIv;
    @Bind(R.id.tv_msg_num)
    TextView tvMsgNum;
    @Bind(R.id.new_tab_tv)
    TextView newTabTv;
    @Bind(R.id.new_tab_tv_subscript)
    ImageView newTabTvSubscript;
    @Bind(R.id.new_tab_layout)
    LinearLayout newTabLayout;
    @Bind(R.id.hot_tab_tv)
    TextView hotTabTv;
    @Bind(R.id.hot_tab_tv_subscript)
    ImageView hotTabTvSubscript;
    @Bind(R.id.hot_tab_layout)
    LinearLayout hotTabLayout;
    @Bind(R.id.article_tab_layout)
    LinearLayout articleTabLayout;
    @Bind(R.id.community_header_layout)
    RelativeLayout communityHeaderLayout;
    @Bind(R.id.article_viewpager)
    ViewPager articleViewpager;
    @Bind(R.id.layout_container)
    LinearLayout layoutContainer;
    private List<Fragment> fragmentList = new ArrayList<Fragment>();
    @Override
    protected void initData(String content) {
        initFragment();
        //设置viewpager,加载数据显示
        articleViewpager.setAdapter(new MyViewPagerAdapter(getFragmentManager()));
        //设置viewpager的监听,改变标题栏状态
        articleViewpager.addOnPageChangeListener(new MyPageChangeListener());

    }
    class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
             //设置标题栏状态
            setTitleState(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /**
     * 根据滑动位置设置标题栏状态
     * @param position
     */
    private void setTitleState(int position) {
        newTabTv.setTextColor(position==0?getResources().getColor(R.color.tv_color_red):getResources().getColor(R.color.tv_color_black));
        newTabTvSubscript.setVisibility(position==0? View.VISIBLE:View.INVISIBLE);
        hotTabTv.setTextColor(position==1?getResources().getColor(R.color.tv_color_red):getResources().getColor(R.color.tv_color_black));
        hotTabTvSubscript.setVisibility(position==1? View.VISIBLE:View.INVISIBLE);
    }

    private void initFragment() {
        CommunityNewFragment communityNewFragment = new CommunityNewFragment();
        CommunityHotFragment communityHotFragment = new CommunityHotFragment();
        fragmentList.add(communityNewFragment);
        fragmentList.add(communityHotFragment);
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
        return R.layout.fragment_squeak;
    }


    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

    }
}
