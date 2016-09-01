package com.atguigu.mycyc.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.PriceFillterAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 徐达
 * on 2016/8/31 on 19:29.
 * 作用:
 */
public class FillterPriceFragment extends Fragment {
    @Bind(R.id.btn_choose_back)
    TextView btnChooseBack;
    @Bind(R.id.btn_choose_ok)
    TextView btnChooseOk;
    @Bind(R.id.tv_choose_title)
    TextView tvChooseTitle;
    @Bind(R.id.rel_new_common_bar)
    RelativeLayout relNewCommonBar;
    @Bind(R.id.lv_choose)
    ListView lvChoose;
    @Bind(R.id.id_price_start)
    EditText idPriceStart;
    @Bind(R.id.line)
    View line;
    @Bind(R.id.id_price_end)
    EditText idPriceEnd;
    @Bind(R.id.id_status2)
    ImageView idStatus2;
    @Bind(R.id.id_lay)
    RelativeLayout idLay;
    @Bind(R.id.rl_choose_drawer)
    LinearLayout rlChooseDrawer;
    private PriceFillterAdapter priceFillterAdapter;
    private FragmentManager fm;
    private String priceRange;
    //刚进入界面时的点击位置
    public static int prePosition;
    //现在被点击的位置
    private int curPosition;
    private List<String> list;
    //是否选择list,true代表选择,false代表不选择
    private boolean chooseList=true;
    //起始价格
    private static String startPrice;
    //种植价格价格
    private static String endPrice;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("TAG", " filletr price ----onCreateView");
        View view = View.inflate(getActivity(), R.layout.goodslist_choose, null);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
        Log.e("TAG", " filletr price ----onCreate");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG", " filletr price---- onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", " filletr price---- onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", " filletr price---- onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("TAG", " filletr price---- onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", " filletr price---- onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG", " filletr price---- onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", " filletr price---- onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("TAG", " filletr price---- onDetach");
    }

    private void initData() {
        //初始化edittext
        idPriceStart.setText(startPrice);
        idPriceEnd.setText(endPrice);
        list = new ArrayList<>();
        list.add("不限");
        list.add("0-15");
        list.add("15-30");
        list.add("30-50");
        list.add("50-70");
        list.add("70-100");
        list.add("100以上");
        //初始化edittext的选择状态
        idStatus2.setVisibility(prePosition == list.size() ? View.VISIBLE : View.GONE);
        //设置listview的显示
        priceFillterAdapter = new PriceFillterAdapter(getActivity(), list);
        priceFillterAdapter.setmSelect(prePosition);
        priceFillterAdapter.setChooseList(chooseList);
        lvChoose.setAdapter(priceFillterAdapter);
        //设置listview的条目点击监听
        lvChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priceFillterAdapter.changeSelected(position, chooseList);
                //priceFillterAdapter.adapterChange(chooseList);
                //更改listview被选择的状态
                chooseList =true;
                //记录被点击的位置
                curPosition = position;
                //隐藏被显示的edittext箭头
                idStatus2.setVisibility(View.INVISIBLE);
            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        Log.e("TAG", " filletr price---- onDestroyView");
    }

    @OnClick({R.id.btn_choose_back, R.id.btn_choose_ok,R.id.id_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose_back://返回按钮
                //获取需要切换的值
                //切换到FillterFragment
                switctToFillterFragment();
                break;
            case R.id.btn_choose_ok://确认按钮
                //切换到FillterFragment
                if(chooseList) {
                    prePosition=curPosition;
                }else {
                    //更改prePosition;
                    prePosition=list.size();
                }
                switctToFillterFragment();
                break;
            case R.id.id_lay://edit_text按钮
                //切换到FillterFragment
                Log.e("TAG", "edit_text被点击了");
                //显示对勾
                idStatus2.setVisibility(View.VISIBLE);
                //更改listview选择状态
                chooseList =false;
                //prePosition=list.size();
                //刷新listview适配器
                priceFillterAdapter.changeSelected(prePosition, chooseList);
                //priceFillterAdapter.adapterChange(chooseList);
                break;
        }
    }

    private void switctToFillterFragment() {
        if(prePosition==list.size()) {//选择的是edittext
            startPrice = idPriceStart.getText().toString();
            endPrice=idPriceEnd.getText().toString();
            priceRange = startPrice +"-"+endPrice;
        }else {//选择的是listview
            priceRange = list.get(prePosition);
        }
        FillterFragment fillterFragment = new FillterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("name", priceRange);
        fillterFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fl_drawer_right, fillterFragment).commit();

    }
}
