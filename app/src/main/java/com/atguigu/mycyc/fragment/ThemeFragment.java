package com.atguigu.mycyc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
 * on 2016/9/1 on 11:54.
 * 作用:
 */
public class ThemeFragment extends Fragment {
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
    //刚进入界面时的点击位置
    public static int prePosition;
    //现在被点击的位置
    private int curPosition;
    private List<String> list;
    private PriceFillterAdapter priceFillterAdapter;
    private FragmentManager fm;
    private String theme;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.goodslist_choose, null);
        ButterKnife.bind(this, view);
        //隐藏edittext
        idLay.setVisibility(View.GONE);
        //修改标题
        tvChooseTitle.setText("推荐主题");
        initData();
        return view;
    }
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getActivity().getSupportFragmentManager();
    }
    private void initData() {
        //初始化lsit
        list = new ArrayList<>();
        list.add("全部");
        list.add("盗墓笔记");
        list.add("FUNKO");
        list.add("GSC");
        list.add("古风原创");
        list.add("剑侠情缘叁");
        list.add("零食仓");
        list.add("秦时明月");
        list.add("全职高手");
        list.add("长草颜文字");

        //设置listview的显示
        priceFillterAdapter = new PriceFillterAdapter(getActivity(), list);
        priceFillterAdapter.setmSelect(prePosition);
        priceFillterAdapter.setChooseList(true);
        lvChoose.setAdapter(priceFillterAdapter);
        //设置listview的条目点击监听
        lvChoose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                priceFillterAdapter.changeSelected(position, true);
                //priceFillterAdapter.adapterChange(chooseList);
                //更改listview被选择的状态
                //chooseList =true;
                //记录被点击的位置
                curPosition = position;

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.btn_choose_back, R.id.btn_choose_ok, R.id.id_lay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_choose_back://返回按钮
                //获取需要切换的值
                //切换到FillterFragment
                switctToFillterFragment();
                break;
            case R.id.btn_choose_ok://确认按钮
                //切换到FillterFragment
                prePosition = curPosition;
                switctToFillterFragment();
                break;

        }
    }

    private void switctToFillterFragment() {

        theme = list.get(prePosition);
        FillterFragment fillterFragment = new FillterFragment();
        Bundle bundle = new Bundle();
        bundle.putString("theme", theme);
        fillterFragment.setArguments(bundle);
        fm.beginTransaction().replace(R.id.fl_drawer_right, fillterFragment).commit();


    }
}