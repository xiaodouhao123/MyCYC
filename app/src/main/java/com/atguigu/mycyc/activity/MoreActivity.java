package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.GridView;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.ChannelMoreAdapter;
import com.atguigu.mycyc.bean.ChannelMoreBean;
import com.atguigu.mycyc.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

public class MoreActivity extends Activity {

    @Bind(R.id.gv_channel_more)
    GridView gvChannelMore;
    private String mUrl;
    private ChannelMoreBean mChannelMoreBean;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra(Constant.NEW_GOODS_MORE);
        getDataFromNet();
    }

    private void getDataFromNet() {
        OkHttpUtils
                .get()
                .url(mUrl)
//                    .addParams("username", "hyman")
//                    .addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MoreActivity.this, "网络连接有点问题", Toast.LENGTH_SHORT).show();
                        //TODO：执行网络错误动画
                    }

                    @Override
                    public void onResponse(String response, int id) {

                        if (!TextUtils.isEmpty(response)) {

                            processData(response);
                        }
                    }
                });
    }
    private void processData(String json) {
        mChannelMoreBean = parseJson(json);

        ChannelMoreAdapter adapter = new ChannelMoreAdapter(MoreActivity.this, mChannelMoreBean);
        gvChannelMore.setAdapter(adapter);

    }

    private ChannelMoreBean parseJson(String json) {
        return new Gson().fromJson(json,ChannelMoreBean.class);
    }
}
