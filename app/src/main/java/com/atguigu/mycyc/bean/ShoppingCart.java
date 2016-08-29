package com.atguigu.mycyc.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/12.
 * 代表每个商品在购物车的状态
 */
public class ShoppingCart extends GoodDetailBean.ResultBean.ProductInfoBean implements Serializable {
    private int count;
    /**
     * 数否被选中
     */
    private boolean isChecked = true;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
