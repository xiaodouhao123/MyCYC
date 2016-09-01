package com.atguigu.mycyc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.MyBaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 徐达
 * on 2016/9/1 on 15:13.
 * 作用:
 */
public class TypeFrgment extends Fragment {
    @Bind(R.id.btn_choose_back)
    TextView btnChooseBack;
    @Bind(R.id.btn_choose_ok)
    TextView btnChooseOk;
    @Bind(R.id.tv_choose_title)
    TextView tvChooseTitle;
    @Bind(R.id.rel_new_common_bar)
    RelativeLayout relNewCommonBar;
    @Bind(R.id.id_img)
    ImageView idImg;
    @Bind(R.id.id_txt)
    TextView idTxt;
    @Bind(R.id.id_status)
    ImageView idStatus;
    @Bind(R.id.id_lay)
    RelativeLayout idLay;
    @Bind(R.id.lv_choose)
    ExpandableListView lvChoose;
    @Bind(R.id.rl_choose_drawer)
    LinearLayout rlChooseDrawer;
    private List<String> groupData;//group的数据源
    private Map<Integer, List<String>> childData;//child的数据源
    private MyBaseExpandableListAdapter myAdapter;
    private FragmentManager fm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.type_choose, null);
        ButterKnife.bind(this, view);
        initDatas();
        initView();
        return view;
    }

    private void initView() {
        lvChoose.setGroupIndicator(null);//这里不显示系统默认的group indicator
        lvChoose.setAdapter(myAdapter);
    }

    private void initDatas() {
        //初始化组名
        groupData = new ArrayList<String>();
        groupData.add("红色水果");
        groupData.add("黄色水果");
        groupData.add("其他水果");
        //初始化组名下的子类
        List<String> childItems = new ArrayList<String>();
        childItems.add("香蕉");
        childItems.add("芒果");
        childItems.add("橘子");
        childItems.add("梨子");
        List<String> childItems2 = new ArrayList<String>();
        childItems2.add("苹果");
        childItems2.add("樱桃");
        childItems2.add("草莓");
        List<String> childItems3 = new ArrayList<String>();
        childItems3.add("葡萄");
        childItems3.add("西瓜");
        childData = new HashMap<Integer, List<String>>();
        childData.put(0, childItems);
        childData.put(1, childItems2);
        childData.put(2, childItems3);
        //设置适配器
        myAdapter = new MyBaseExpandableListAdapter(getActivity(), groupData, childData);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_choose_back, R.id.btn_choose_ok})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose_back://返回
                switctToFillterFragment();
                break;
            case R.id.btn_choose_ok://确定
                switctToFillterFragment();
                break;
        }
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
    }
    private void switctToFillterFragment() {

        //theme = list.get(prePosition);
        FillterFragment fillterFragment = new FillterFragment();
       // Bundle bundle = new Bundle();
        //bundle.putString("theme", theme);
        //fillterFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fl_drawer_right, fillterFragment).commit();


    }
}
