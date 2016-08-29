package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.cjj.MaterialRefreshLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeWebiewActivity extends Activity {

    @Bind(R.id.btn_back)
    ImageView btnBack;
    @Bind(R.id.tv_right_btn)
    TextView tvRightBtn;
    @Bind(R.id.btn_ok)
    ImageView btnOk;
    @Bind(R.id.top_right)
    FrameLayout topRight;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.btn_close)
    ImageView btnClose;
    @Bind(R.id.icd_head)
    RelativeLayout icdHead;
    @Bind(R.id.webview)
    WebView webview;
    @Bind(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.main)
    LinearLayout main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_webiew);
        ButterKnife.bind(this);
        initWebview();
    }

    private void initWebview() {
        WebSettings settings = webview.getSettings();
        //设置WebView属性，能够执行Javascript脚本
        settings.setJavaScriptEnabled(true);
        //设置可以访问文件
//        settings.setAllowFileAccess(true);
        //设置支持缩放
        settings.setBuiltInZoomControls(true);
        //加载需要显示的网页
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        webview.loadUrl(url);
        //设置Web视图,不调用浏览器-自定义浏览器
        webview.setWebViewClient(new WebViewClient());
    }
    /**
     * 返回键的点击事件监听
     * @param view
     */
    public void back(View view) {
        finish();
    }
}
