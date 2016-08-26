package com.atguigu.mycyc.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.base.BaseFragment;
import com.atguigu.mycyc.fragment.ClassfilyFragment;
import com.atguigu.mycyc.fragment.HomeFragment;
import com.atguigu.mycyc.fragment.SelfCenterFragment;
import com.atguigu.mycyc.fragment.ShoppingCartFragment;
import com.atguigu.mycyc.fragment.SqueakFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity {

    @Bind(R.id.fl_main_replaced)
    FrameLayout flMainReplaced;
    @Bind(R.id.rb_home_main)
    RadioButton rbHomeMain;
    @Bind(R.id.rb_classfiy_main)
    RadioButton rbClassfiyMain;
    @Bind(R.id.rb_squeak_main)
    RadioButton rbSqueakMain;
    @Bind(R.id.rb_shoppingcart_main)
    RadioButton rbShoppingcartMain;
    @Bind(R.id.rb_selfcenter_main)
    RadioButton rbSelfcenterMain;
    @Bind(R.id.rg_main)
    RadioGroup rgMain;
    @Bind(R.id.iv_menu_squeak_pressed)
    ImageView ivMenuSqueakPressed;
    @Bind(R.id.rel_squeak_bg_pressed)
    RelativeLayout relSqueakBgPressed;
    /**
     * 选中的Fragment的对应的位置
     */
    private int position;

    /**
     * 上次切换的Fragment
     */
    private Fragment mContent;
    private ArrayList<BaseFragment> mBaseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化fragment
        initFragment();
        //初始化监听
        initListener();



    }

    private void initAnimation() {
        boolean checked = rbSqueakMain.isChecked();
        if (checked) {//吱吱选项卡被选中

            AlphaAnimation aam = new AlphaAnimation(0, 1);
            ScaleAnimation sam = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            AnimationSet set = new AnimationSet(this, null);
            set.addAnimation(aam);
            set.addAnimation(sam);
            set.setDuration(300);
            set.setFillAfter(true);
            ivMenuSqueakPressed.startAnimation(set);
            relSqueakBgPressed.setVisibility(View.VISIBLE);
        } else {
            AlphaAnimation aam = new AlphaAnimation(1, 0);
            ScaleAnimation sam = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            AnimationSet set = new AnimationSet(this, null);
            set.addAnimation(aam);
            set.addAnimation(sam);
            set.setDuration(300);
            set.setFillAfter(true);
            ivMenuSqueakPressed.startAnimation(set);
            //设置动画的监听
            set.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    relSqueakBgPressed.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

        }
    }

    private void initListener() {
        //RadioGroup的选择切换监听,根据选择位置,切换到相应的fragment
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home_main://主页面
                        position = 0;
                        break;
                    case R.id.rb_classfiy_main://分类页面
                        position = 1;
                        break;
                    case R.id.rb_squeak_main://吱吱页面
                        position = 2;

                        break;
                    case R.id.rb_shoppingcart_main://购物车
                        position = 3;
                        break;
                    case R.id.rb_selfcenter_main://个人中心
                        position = 4;
                        break;
                }
                //根据位置得到对应的Fragment
                BaseFragment to = getFragment();
                //替换
                switchFrament(mContent, to);
                //初始化动画,根据吱吱被选中的转态来设置动画
                initAnimation();
            }
        });
        //设置默认选中主页面
        rgMain.check(R.id.rb_home_main);
    }

    /**
     * @param from 刚显示的Fragment,马上就要被隐藏了
     * @param to   马上要切换到的Fragment，一会要显示
     */
    private void switchFrament(Fragment from, BaseFragment to) {
        if (from != to) {
            mContent = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //才切换
            //判断有没有被添加
            if (!to.isAdded()) {
                //to没有被添加
                //from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //添加to
                if (to != null) {
                    ft.add(R.id.fl_main_replaced, to).commit();
                }
            } else {
                //to已经被添加
                // from隐藏
                if (from != null) {
                    ft.hide(from);
                }
                //显示to
                if (to != null) {
                    ft.show(to).commit();
                }
            }
        }

    }

    /**
     * 根据位置的得到对应的Fragment
     *
     * @return
     */
    private BaseFragment getFragment() {
        BaseFragment fragment = mBaseFragment.get(position);
        return fragment;
    }

    private void initFragment() {
        mBaseFragment = new ArrayList<>();
        mBaseFragment.add(new HomeFragment());//主页面Fragment
        mBaseFragment.add(new ClassfilyFragment());//分类Fragment
        mBaseFragment.add(new SqueakFragment());//吱吱Fragment
        mBaseFragment.add(new ShoppingCartFragment());//购物车Fragment
        mBaseFragment.add(new SelfCenterFragment());//个人中心Fragment

    }
}
