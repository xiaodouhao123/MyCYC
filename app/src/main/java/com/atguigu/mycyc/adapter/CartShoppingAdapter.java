package com.atguigu.mycyc.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.CartProvider;
import com.atguigu.mycyc.bean.ShoppingCart;
import com.atguigu.mycyc.view.NumberAddSubView;

import java.util.List;

/**
 * Created by 徐达
 * on 2016/8/29 on 16:27.
 * 作用:
 */
public class CartShoppingAdapter extends RecyclerView.Adapter<CartShoppingAdapter.MyHolder> {
    private  CheckBox checkbox_all;
    private Context mContext;
    private List<ShoppingCart> datas;
    private TextView tv_total_price;
    private CartProvider cartProvider;
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    public CartShoppingAdapter(Context mContext, final List<ShoppingCart> datas, final CheckBox checkbox_all, TextView tv_total_price) {
        this.mContext = mContext;
        this.datas = datas;
        this.checkbox_all=checkbox_all;
        this.tv_total_price=tv_total_price;
        cartProvider=new CartProvider(mContext);
        //显示总价格
        showTotalPrice();
        //设置点击某一条的监听
        setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                //设置点击item的商品状态
                ShoppingCart shoppingCart = datas.get(position);
                shoppingCart.setIsChecked(!shoppingCart.isChecked());
                //设置全选和非全选的监听
                checkListener();
//                notifyItemChanged(position);
                notifyDataSetChanged();
                //显示总价格
                showTotalPrice();
            }
        });
        //设置全选和反选的监听
        checkbox_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //全选和反选
                checkAll_none(checkbox_all.isChecked());
//                notifyDataSetChanged();
                //显示价格
                showTotalPrice();
            }
        });
    }
    /**
     * 全选个非全选的监听
     */
    private void checkListener() {
        int num=0;
        if(datas!=null&&datas.size()>0) {
            for(int i = 0; i < datas.size(); i++) {
                ShoppingCart shoppingCart = datas.get(i);
                if(!shoppingCart.isChecked()) {//只要有一个未选中,就不是全选
                    checkbox_all.setChecked(false);

                }else {
                    num+=1;
                }
            }
            if(datas.size()==num) {
                checkbox_all.setChecked(true);
            }
        }
    }
    /**
     * 全选和反选
     * @param checked
     */
    public void checkAll_none(boolean checked) {
        if(datas!=null&&datas.size()>0) {
            for(int i = 0; i < datas.size(); i++) {
                ShoppingCart shoppingCart = datas.get(i);
                shoppingCart.setIsChecked(checked);
                notifyItemChanged(i);
            }
        }
    }
    private void showTotalPrice() {
        tv_total_price.setText("¥"+getTotalPrice());
    }
    /**
     * 删除被选中的商品
     */
    public void deleteCart() {
        if(datas!=null&&datas.size()>0) {
            for(int i = 0; i <datas.size() ; i++) {
                ShoppingCart shoppingCart = datas.get(i);
                if(shoppingCart.isChecked()) {
                    int position = datas.indexOf(shoppingCart);
                    //内训中删除
                    datas.remove(shoppingCart);
                    //本地更新同步
                    cartProvider.delete(shoppingCart);
                    //刷新数据

                   // LogUtil.e("购物车里"+(position==i));
                    notifyItemRemoved(position);
                    i--;

                }
            }
        }
    }
    private double getTotalPrice() {

        double totalPrice=0;
        if(datas!=null&&datas.size()>0) {
            for(int i = 0; i < datas.size(); i++) {
                ShoppingCart cart = datas.get(i);
                if(cart.isChecked()) {
                    totalPrice+=Double.parseDouble(cart.getCover_price())*cart.getCount();
                }
            }
        }

        return totalPrice;
    }

    @Override
    public CartShoppingAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_govafair, null);
        return new MyHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartShoppingAdapter.MyHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private CheckBox checkbox;
        private ImageView iv_icon;
        private TextView tv_name;
        private TextView tv_price;
        private NumberAddSubView numberAddSubView;

        public MyHolder(View itemView) {
            super(itemView);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            numberAddSubView = (NumberAddSubView) itemView.findViewById(R.id.numberAddSubView);
            if(onItemClickListener!=null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onItemClickListener.onItemClick(v,getLayoutPosition());
                    }
                });
            }
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View v,int position);
    }
}
