package com.atguigu.mycyc.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.TableAdapter;
import com.atguigu.mycyc.base.BaseFragment;
import com.atguigu.mycyc.bean.LabelBean;
import com.atguigu.mycyc.utils.AppNetConfig;
import com.atguigu.mycyc.utils.CacheUtils;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by 徐达
 * on 2016/8/26 on 11:29.
 * 作用:
 */
public class TableFragment extends BaseFragment {
    @Bind(R.id.recyclerView_label)
    RecyclerView recyclerViewLabel;
    @Bind(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
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
    private TableAdapter tableAdapter;
    //标签数据
    private List<LabelBean.ResultBean> result;
    /**
     * 默认状态
     */
    private static final int STATE_NORMAL = 1;

    /**
     * 下拉刷新状态
     */
    private static final int STATE_REFRESH = 2;

    /**
     * 加载更多（上拉刷新）状态
     */
    private static final int STATE_MORE = 3;

    /**
     * 状态
     */
    private int state = STATE_NORMAL;
    //请求的路径
    private String url;

    @Override
    protected void initData(String content) {
         /*//读取缓存
        String saveJson = CacheUtils.getString(mContext, AppNetConfig.HOME);
        if (!TextUtils.isEmpty(saveJson)) {
            showData(saveJson,state);
        }*/

        if (!TextUtils.isEmpty(content)) {//数据不为空



            showData(content, state);
            //初始化监听
            initListenr();
        }
        else {
            Toast.makeText(this.getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private void initListenr() {
        //MaterialRefresh的监听
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新
                Log.e("TAG", "下拉刷新了");
                url=AppNetConfig.CLASSFILY_LABEL;
                state=STATE_REFRESH;
                getDataFromNet(state);
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                //上拉加载更多
                Log.e("TAG", "上拉加载更多");
            }
        });
    }

    private void getDataFromNet(final int state) {
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
                        Log.e("TAG", "下拉刷新时候加载数据错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求成功
                        showData(response,state);
                    }
                });
    }

    private void showData(String content, int state) {
        //保存json数据做缓存
        CacheUtils.putString(mContext, AppNetConfig.CLASSFILY_LABEL, content);
        LabelBean labelBean = new Gson().fromJson(content, LabelBean.class);
        result = labelBean.getResult();
        switch (state) {
            case STATE_NORMAL :
                tableAdapter = new TableAdapter(mContext, result);
                recyclerViewLabel.setAdapter(tableAdapter);
                recyclerViewLabel.setLayoutManager(new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false));
                break;
            case STATE_REFRESH ://刷新
                //1.刷新适配器
                tableAdapter.notifyDataSetChanged();
                //2.把下拉刷新控件隐藏
                refreshLayout.finishRefresh();
                break;
        }
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.CLASSFILY_LABEL;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_table;
    }


}
