package com.atguigu.mycyc.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.utils.UIUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


/**
 * Created by shkstart on 2016/8/15 0015.
 * 提供联网操作的4种不同状态对应的View。
 */
public abstract class LoadingPage extends FrameLayout {
    //1.提供4种显示状态
    private static final int PAGE_STATE_LOADING = 1;
    private static final int PAGE_STATE_ERROR = 2;
    private static final int PAGE_STATE_EMPTY = 3;
    private static final int PAGE_STATE_SUCCESS = 4;

    //当前显示的状态：默认显示正在加载的页面
    private int page_state_current = 1;

    //2.提供4种不同显示界面
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;


    private LayoutParams params;


    private Context mContext;

    public LoadingPage(Context context) {
        this(context, null);
    }

    public LoadingPage(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    //初始化4种不同的界面
    private void init() {
        params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        if (loadingView == null) {
            loadingView = UIUtils.getXmlView(R.layout.page_loading);
            addView(loadingView, params);
        }

        if (errorView == null) {
            errorView = UIUtils.getXmlView(R.layout.page_error);
            addView(errorView, params);
        }

        if (emptyView == null) {
            emptyView = UIUtils.getXmlView(R.layout.page_empty);
            addView(emptyView, params);
        }
        //选择要加载显示的界面
        showSafePage();
    }

    private void showSafePage() {
        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showPage();
            }
        });

    }


    //保证此方法在主线程中执行
    private void showPage() {
        loadingView.setVisibility(page_state_current == PAGE_STATE_LOADING ? View.VISIBLE : View.GONE);
        errorView.setVisibility(page_state_current == PAGE_STATE_ERROR ? View.VISIBLE : View.GONE);
        emptyView.setVisibility(page_state_current == PAGE_STATE_EMPTY ? View.VISIBLE : View.GONE);

        //如果加载成功的界面没有初始化，需要初始化
        if (successView == null) {
//            successView = UIUtils.getXmlView(layoutId());//内部使用Application的实例作为Context实例
            successView = View.inflate(mContext, layoutId(), null);//内部使用Activity的实例作为Context实例
            addView(successView, params);
        }

        successView.setVisibility(page_state_current == PAGE_STATE_SUCCESS ? View.VISIBLE : View.GONE);

    }

    public abstract int layoutId();

    private ResultState resultState;

    //联网操作，根据返回的情况，决定page_state_current
    public void show() {
        String url = url();
        if (TextUtils.isEmpty(url)) {
            resultState = ResultState.SUCCESS;
            resultState.setContent("");
            loadPage();
        } else {
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
                            resultState = ResultState.ERROR;
                            resultState.setContent("");
                            loadPage();
                        }

                        @Override
                        public void onResponse(String response, int id) {//请求失败
                            if (TextUtils.isEmpty(response)) {
                                resultState = ResultState.EMPTY;
                                resultState.setContent("");

                            } else {
                                Log.e("TAG", "加载成功了");
                                resultState = ResultState.SUCCESS;
                                resultState.setContent(response);

                            }
                            loadPage();
                        }
                    });
        }
    }

    private void loadPage() {
        switch (resultState) {
            case ERROR:
                page_state_current = PAGE_STATE_ERROR;
                break;
            case EMPTY:
                page_state_current = PAGE_STATE_EMPTY;
                break;
            case SUCCESS:
                page_state_current = PAGE_STATE_SUCCESS;
                break;
        }

        showSafePage();

        //如果是加载成功的状态
        if (page_state_current == PAGE_STATE_SUCCESS) {
            onSuccess(resultState, successView);
        }

    }

    protected abstract void onSuccess(ResultState resultState, View successView);


    //提供联网请求的url
    protected abstract String url();

    //提供内部的枚举类
    public enum ResultState {
        ERROR(2), EMPTY(3), SUCCESS(4);

        private int state;

        ResultState(int state) {
            this.state = state;
        }

        private String content;//保存的内部数据

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
