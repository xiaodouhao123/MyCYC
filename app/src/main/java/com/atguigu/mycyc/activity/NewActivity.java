package com.atguigu.mycyc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.NewActivityAdapter;
import com.atguigu.mycyc.bean.HomeYxc;
import com.atguigu.mycyc.fragment.FillterFragment;
import com.atguigu.mycyc.fragment.FillterPriceFragment;
import com.atguigu.mycyc.fragment.ThemeFragment;
import com.atguigu.mycyc.utils.AppNetConfig;
import com.atguigu.mycyc.utils.CacheUtils;
import com.atguigu.mycyc.utils.Constant;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import net.simonvt.menudrawer.MenuDrawer;
import net.simonvt.menudrawer.Position;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class NewActivity extends FragmentActivity {

    public static final String NEW_ACTIVITY_CAECH = "new_activity_caech";
    public static final String HOME = "home";
    @Bind(R.id.good_recyclerView)
    RecyclerView goodRecyclerView;
    @Bind(R.id.network_iv_describe)
    ImageView networkIvDescribe;
    @Bind(R.id.network_btn_refresh)
    ImageView networkBtnRefresh;
    @Bind(R.id.error_network)
    LinearLayout errorNetwork;
    @Bind(R.id.loader_progress)
    ProgressBar loaderProgress;
    @Bind(R.id.error_page)
    RelativeLayout errorPage;
    @Bind(R.id.header_back)
    ImageView headerBack;
    @Bind(R.id.header_right_btn)
    ImageView headerRightBtn;
    @Bind(R.id.header_tv)
    TextView headerTv;
    @Bind(R.id.search_bar)
    LinearLayout searchBar;
    @Bind(R.id.tv_btn_composite)
    TextView tvBtnComposite;
    @Bind(R.id.lay_btn_composite)
    LinearLayout layBtnComposite;
    @Bind(R.id.tv_btn_price)
    TextView tvBtnPrice;
    @Bind(R.id.iv_btn_price)
    ImageView ivBtnPrice;
    @Bind(R.id.lay_btn_price)
    LinearLayout layBtnPrice;
    @Bind(R.id.tv_btn_filter)
    TextView tvBtnFilter;
    @Bind(R.id.lay_btn_filter)
    LinearLayout layBtnFilter;
    @Bind(R.id.scroll_herder)
    LinearLayout scrollHerder;
    @Bind(R.id.btn_top_fire)
    ImageButton btnTopFire;
    @Bind(R.id.fl_drawer_right)
    FrameLayout flDrawerRight;

    private String[] urls = {AppNetConfig.HOME_YXC};
    private NewActivityAdapter adapter;
    private MenuDrawer mDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDrawer = MenuDrawer.attach(this, Position.RIGHT);
        mDrawer.setContentView(R.layout.activity_new_goods_list);
        mDrawer.setMenuView(R.layout.draw_right);
        ButterKnife.bind(this);
        //设置recyview的点击监听

        initData();

    }

    private void initData() {
        //默认线束筛选的主页面
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_right,new FillterFragment(), HOME).commit();
        int position = getIntent().getIntExtra(Constant.NEW_GOODS_POSITION, 0);
        //读取缓存
        String saveJson = CacheUtils.getString(NewActivity.this, NEW_ACTIVITY_CAECH);
        if (!TextUtils.isEmpty(saveJson)) {
            procesData(saveJson);
        }
        getDataFromNet(position);
    }

    private void getDataFromNet(int position) {
        String url = urls[0];
        OkHttpUtils
                .get()
                .url(url)
                        // .addParams("username", "hyman")
                        //.addParams("password", "123")
                .build()
                .execute(new StringCallback() {
                    //response就是返回来的数据
                    @Override
                    public void onError(Call call, Exception e, int id) {//请求错误
                        Log.e("TAG", "请求数据出错了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求成功
                        //保存json数据做缓存
                        CacheUtils.putString(NewActivity.this, NEW_ACTIVITY_CAECH, response);
                        //解析和显示数据
                        procesData(response);
                    }
                });

    }

    /**
     * 解析和显示数据
     *
     * @param response
     */
    private void procesData(String response) {
        HomeYxc homeYxc = new Gson().fromJson(response, HomeYxc.class);
        HomeYxc.ResultBean result = homeYxc.getResult();
        List<HomeYxc.ResultBean.PageDataBean> page_data = result.getPage_data();
        if (page_data != null && page_data.size() > 0) {//有数据
            //显示适配器
            adapter = new NewActivityAdapter(this, page_data);
            goodRecyclerView.setAdapter(adapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position == 0) {
                        return 2;
                    }
                    return 1;
                }
            });
            goodRecyclerView.setLayoutManager(gridLayoutManager);
            goodRecyclerView.setItemViewCacheSize(5);
            //设置点击监听
            adapter.setOnItemOnclickListener(new NewActivityAdapter.OnItemOnclickListener() {
                @Override
                public void onItemOnclick(View view, int position) {
                    Intent intent = new Intent(NewActivity.this, GoodsDetailActivity.class);
                    intent.putExtra(Constant.GOODS_DETAIL, AppNetConfig.GOOD_DETAIL);
                    startActivity(intent);
                }
            });

        } else {
            Toast.makeText(NewActivity.this, "请求数据为空", Toast.LENGTH_SHORT).show();
        }
    }


    @OnClick({R.id.header_back, R.id.tv_btn_filter,R.id.header_right_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.header_back://返回
                finish();
                break;
            case R.id.tv_btn_filter://筛选
                mDrawer.openMenu();
                break;
            case R.id.header_right_btn://回到主页面
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FillterPriceFragment price = (FillterPriceFragment) getSupportFragmentManager().findFragmentByTag("price");
        price.prePosition=0;
        ThemeFragment theme1 = (ThemeFragment) getSupportFragmentManager().findFragmentByTag("theme");
        theme1.prePosition=0;
    }
}

