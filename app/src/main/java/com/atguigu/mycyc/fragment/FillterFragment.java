package com.atguigu.mycyc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 徐达
 * on 2016/8/31 on 19:15.
 * 作用:
 */
public class FillterFragment extends Fragment {
    @Bind(R.id.iv_fillter_back)
    ImageView ivFillterBack;
    @Bind(R.id.tv_fillter_sure)
    TextView tvFillterSure;
    @Bind(R.id.tv_fillter_hot)
    TextView tvFillterHot;
    @Bind(R.id.tv_fillter_new)
    TextView tvFillterNew;
    @Bind(R.id.ll_price)
    LinearLayout llPrice;
    @Bind(R.id.ll_theme)
    LinearLayout llTheme;
    @Bind(R.id.ll_goodlist_classfily)
    LinearLayout llGoodlistClassfily;
    @Bind(R.id.tv_fillter_overwrite)
    TextView tvFillterOverwrite;
    @Bind(R.id.tv_fillter_price)
    TextView tvFillterPrice;
    @Bind(R.id.tv_fillter_theme)
    TextView tvFillterTheme;
    @Bind(R.id.tv_fillter_classfily)
    TextView tvFillterClassfily;
    private String priceRange="不限";
    private String theme="全部";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.menu_goods_fillter, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle!=null) {

            priceRange = bundle.getString("name", "不限");
            theme = bundle.getString("theme", "全部");
        }
    }

    private void initData() {
        tvFillterPrice.setText(priceRange);
        tvFillterTheme.setText(theme);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.iv_fillter_back, R.id.tv_fillter_sure, R.id.tv_fillter_hot, R.id.tv_fillter_new, R.id.ll_price, R.id.ll_theme, R.id.ll_goodlist_classfily, R.id.tv_fillter_overwrite})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_fillter_back:
                break;
            case R.id.tv_fillter_sure:
                break;
            case R.id.tv_fillter_hot:
                break;
            case R.id.tv_fillter_new:
                break;
            case R.id.ll_price:
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_right,new FillterPriceFragment(),"price").commit();
                break;
            case R.id.ll_theme://推荐主题
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_right, new ThemeFragment(),"theme").commit();
                break;
            case R.id.ll_goodlist_classfily://分类
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fl_drawer_right, new TypeFrgment(),"type").commit();
                break;
            case R.id.tv_fillter_overwrite:
                break;
        }
    }
}
