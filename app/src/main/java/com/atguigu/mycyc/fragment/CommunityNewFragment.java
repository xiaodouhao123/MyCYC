package com.atguigu.mycyc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.CommunityNewAdapter;
import com.atguigu.mycyc.bean.CommunityBean;
import com.atguigu.mycyc.utils.AppNetConfig;
import com.atguigu.mycyc.utils.CacheUtils;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by 徐达
 * on 2016/8/27 on 11:51.
 * 作用:
 */
public class CommunityNewFragment extends Fragment {


    @Bind(R.id.article_list_view)
    RecyclerView articleListView;
    @Bind(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.hint_iv)
    ImageView hintIv;
    @Bind(R.id.refresh_load_btn)
    Button refreshLoadBtn;
    @Bind(R.id.error_layout)
    LinearLayout errorLayout;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    private CommunityNewAdapter adapter;
    //显示的数据
    private List<CommunityBean.ResultBean> result;
    /**
     * 第几页的数据
     */
    private int curPage = 1;
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
    /**
     * 请求路径的集合
     */
    private String[] urls = {AppNetConfig.COMMUNITY_NEW_PAG1, AppNetConfig.COMMUNITY_NEW_PAG2, AppNetConfig.COMMUNITY_NEW_PAG3, AppNetConfig.COMMUNITY_NEW_PAG4, AppNetConfig.COMMUNITY_NEW_PAG5};
    /**
     * 网络请求地址
     */
    private String url;
    /**
     * 总页数
     */
    private int totalPage = 5;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_community_new, null);


        ButterKnife.bind(this, view);
        initRefresh();
        initData();
        return view;

    }

    private void initRefresh() {
        //设置可以上拉加载更多
        refreshLayout.setLoadMore(true);
        refreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                //下拉刷新
                curPage = 1;
                state = STATE_REFRESH;
                getDataFromNet();
            }

            @Override
            public void onfinish() {
                super.onfinish();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                if (curPage < totalPage) {
                    //上拉刷新
                    curPage = curPage + 1;
                    state = STATE_MORE;
                    getDataFromNet();

                } else {
                    Toast.makeText(getActivity(), "没有更多数据可以加载了", Toast.LENGTH_SHORT).show();
                    refreshLayout.finishRefreshLoadMore();//隐藏上拉刷新控件
                }
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initData() {
        //显示progressbar
        progressbar.setVisibility(View.VISIBLE);
        //读取缓存
        String saveJson = CacheUtils.getString(getActivity(), AppNetConfig.COMMUNITY_NEW_PAG1);
        if (!TextUtils.isEmpty(saveJson)) {
            processData(saveJson);
        }
        //配置请求地址
        setParams();
        getDataFromNet();
    }

    private void setParams() {
        state = STATE_NORMAL;
        curPage = 1;

    }

    /**
     * 联网加载数据
     */
    private void getDataFromNet() {
        url = urls[curPage - 1];
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
                        Log.e("TAG", "加载数据错误:" + e.toString());
                        errorLayout.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求成功
                        //隐藏加载错误图标
                        errorLayout.setVisibility(View.INVISIBLE);
                        //解析和展示数据
                        processData(response);
                    }
                });
    }

    /**
     * 解析和展示数据
     *
     * @param response
     */
    private void processData(String response) {
        CommunityBean communityBean = new Gson().fromJson(response, CommunityBean.class);
        result = communityBean.getResult();
        //根据状态显示数据
        showData(state);


    }

    private void showData(int state) {
        switch (state) {
            case STATE_NORMAL://正常状态
                if (result != null && result.size() > 0) {//有数据存在
                    //显示数据
                    //设置recyview
                    adapter = new CommunityNewAdapter(getActivity(), result);
                    articleListView.setAdapter(adapter);
                    articleListView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

                } else {//没有数据
                    Toast.makeText(getActivity(), "没有数据", Toast.LENGTH_SHORT).show();

                }
                break;
            case STATE_REFRESH://下拉刷新
//                //1.把之前的数据清除
//                adapter.cleanData();
//
//                //2.添加新的数据
//                adapter.addDate(result);
                  adapter.setResult(result);

                //3.把下拉刷新控件隐藏
                refreshLayout.finishRefresh();
                break;
            case STATE_MORE://加载更多
                 //1.在数据的末尾加上更多数据
                adapter.addDate(adapter.getCount(), result);
                //2.隐藏上拉刷新控件
                refreshLayout.finishRefreshLoadMore();
                break;
        }
        //隐藏加载进度条
        progressbar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

}
