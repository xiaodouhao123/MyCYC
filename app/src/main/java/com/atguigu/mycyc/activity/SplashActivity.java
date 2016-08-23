package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.atguigu.mycyc.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashActivity extends Activity {

    @Bind(R.id.vp_splash_activity)
    ViewPager vpSplashActivity;
    @Bind(R.id.iv_splash_start)
    ImageView ivSplashStart;
    private List<ImageView> imgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        //照片资源id数组
        int[] array = {R.drawable.welcome1, R.drawable.welcome2, R.drawable.welcome3};
        //图像集合
        imgs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(array[i]);
            imgs.add(imageView);
        }
        //viewpager的显示
        vpSplashActivity.setAdapter(new MyPagerAdapter());
        //viewpager的监听
        vpSplashActivity.addOnPageChangeListener(new MyOnPageChangeListener());
        //设置点击图片监听
        ivSplashStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SplashActivity.this, "点击跳转了", Toast.LENGTH_SHORT).show();
            }
        });
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if(position==2) {//滑到第三张
                //图片显示
                ivSplashStart.setVisibility(View.VISIBLE);
            }else {
                ivSplashStart.setVisibility(View.GONE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imgs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imgs.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }
}
