package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.os.Bundle;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.utils.CacheUtils;
import com.atguigu.mycyc.utils.Constant;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initData();
    }

    private void initData() {
        CacheUtils.putBoolean(this, Constant.IS_START_MAIN,true);
    }
}
