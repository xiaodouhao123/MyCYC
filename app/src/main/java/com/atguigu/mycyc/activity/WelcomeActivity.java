package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.utils.CacheUtils;
import com.atguigu.mycyc.utils.Constant;

public class WelcomeActivity extends Activity {
    /**
     * 标识是否启动主页面
     *
     */
    private boolean isEnterMainActivity=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initData();
    }

    private void initData() {
        //保存跳转状态
        CacheUtils.putBoolean(this, Constant.IS_START_MAIN, true);
        //实现4秒后页面自动跳转到主页面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进入主界面
                startMainActivity();
            }
        }, 4000);

    }

    private void startMainActivity() {
        if(!isEnterMainActivity) {
            isEnterMainActivity=true;
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }

    }
    //实现点击后马上跳转到主页面

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        startMainActivity();

        return super.onTouchEvent(event);
    }
}
