package com.atguigu.mycyc.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.CartShoppingAdapter;
import com.atguigu.mycyc.base.BaseFragment;
import com.atguigu.mycyc.bean.CartProvider;
import com.atguigu.mycyc.bean.ShoppingCart;
import com.cjj.MaterialRefreshLayout;

import java.util.List;

import butterknife.Bind;

/**
 * Created by 徐达
 * on 2016/8/23 on 19:46.
 * 作用:
 */
public class ShoppingCartFragment extends BaseFragment {
    @Bind(R.id.btn_cart_back)
    ImageView btnCartBack;
    @Bind(R.id.tv_main_title)
    TextView tvMainTitle;
    @Bind(R.id.btn_cart_edit)
    Button btnCartEdit;
    @Bind(R.id.rl_cart_head)
    RelativeLayout rlCartHead;
    @Bind(R.id.rcv_cart)
    RecyclerView rcvCart;
    @Bind(R.id.refreshLayout)
    MaterialRefreshLayout refreshLayout;
    @Bind(R.id.cb_cart_select)
    CheckBox cbCartSelect;
    @Bind(R.id.tv_select_all)
    TextView tvSelectAll;
    @Bind(R.id.tv_cart_total)
    TextView tvCartTotal;
    @Bind(R.id.tv_origin_total)
    TextView tvOriginTotal;
    @Bind(R.id.tv_sub_total)
    TextView tvSubTotal;
    @Bind(R.id.btn_cart_pay)
    Button btnCartPay;
    @Bind(R.id.ll_menu_bottom)
    RelativeLayout llMenuBottom;
    @Bind(R.id.cb_collect_select)
    CheckBox cbCollectSelect;
    @Bind(R.id.tv_collect_all)
    TextView tvCollectAll;
    @Bind(R.id.tv_delete)
    TextView tvDelete;
    @Bind(R.id.tv_collect)
    TextView tvCollect;
    @Bind(R.id.rl_edit_bottom)
    RelativeLayout rlEditBottom;
    @Bind(R.id.no_resouce_iv)
    LinearLayout noResouceIv;
    @Bind(R.id.progress)
    ProgressBar progress;
    /**
     * 购物车的数据集合
     */
    private List<ShoppingCart> datas;
    /**
     * 购物车集合数据的管理类
     */
    private CartProvider cartProvider;
    /**
     * 编辑状态
     */
    private static final int ACTION_EDIT = 1;
    /**
     * 完成状态
     */
    private static final int ACTION_COMLLETE = 2;
    private CartShoppingAdapter adapter;

    @Override
    protected void initData(String content) {
        //初始化购物车集合数据的管理类
        cartProvider = new CartProvider(mContext);
        //设置默认是编辑状态
        btnCartEdit.setTag(ACTION_EDIT);
        btnCartEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int action = (int) btnCartEdit.getTag();
                //1,如果是编辑状态就改为完成
                if (action == ACTION_EDIT) {
                    showDeleteControl();
                }
                //2,如果是完成就改变为编辑状态
                else if (action == ACTION_COMLLETE) {
                    hideDeleteControl();
                }
            }
        });
        //删除按钮的监听
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //删除被选中的商品
                adapter.deleteCart();
            }
        });
        showData();
    }

    public void showData() {
        datas=cartProvider.getAll();
        adapter=new CartShoppingAdapter(mContext,datas,cbCartSelect,tvCartTotal);
        rcvCart.setAdapter(adapter);
        rcvCart.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden) {
            showData();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "执行了onstart");
    }
    public void onResume() {
        super.onResume();
        Log.e("TAG", "onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG", "onStop()");
    }



    private void hideDeleteControl() {
        //显示主控制栏
        llMenuBottom.setVisibility(View.VISIBLE);
        //隐藏编辑控制栏
        rlEditBottom.setVisibility(View.GONE);
        //改成编辑
        btnCartEdit.setText("编辑");
        btnCartEdit.setTag(ACTION_EDIT);
    }

    private void showDeleteControl() {
        //隐藏主控制栏
        llMenuBottom.setVisibility(View.GONE);
        //显示编辑控制栏
        rlEditBottom.setVisibility(View.VISIBLE);
        //改成完成
        btnCartEdit.setText("完成");
        btnCartEdit.setTag(ACTION_COMLLETE);
    }

    /**
     * 提供网络地址
     *
     * @return
     */
    @Override
    protected String getUrl() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shoppingcart;
    }


}