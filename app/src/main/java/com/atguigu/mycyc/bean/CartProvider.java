package com.atguigu.mycyc.bean;

import android.content.Context;
import android.text.TextUtils;
import android.util.SparseArray;

import com.atguigu.mycyc.utils.CacheUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/12.
 * 数据库存储类
 */
public class CartProvider {
    public static final String CART_JSON = "cart_json";
    private Context mContext;
    /**
     * 性能比Hashmap强大的一个控件,Android提供的
     */
    private static SparseArray<ShoppingCart> datas;

    public CartProvider(Context mContext) {
        this.mContext = mContext;
        datas = new SparseArray<>(10);
        //把list集合转换成SparseArray
        listToSparse();

    }

    /**
     * 从列表中的数据，加入到SparseArray<ShopingCart>中
     */
    private void listToSparse() {
        List<ShoppingCart> carts = getDataFromLocal();
        if (carts != null && carts.size() > 0) {
            for (int i = 0; i < carts.size(); i++) {
                datas.put(Integer.parseInt(carts.get(i).getProduct_id()), carts.get(i));
            }
        }
    }

    /**
     * 从本地中获取保存的所有数据
     *
     * @return
     */
    private List<ShoppingCart> getDataFromLocal() {
        List<ShoppingCart> list = new ArrayList<>();
        //获取本地的Json数据
        String json = CacheUtils.getString(mContext, CART_JSON);
        //解析成列表
        if (!TextUtils.isEmpty(json)) {
            list = new Gson().fromJson(json, new TypeToken<List<ShoppingCart>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 增加保持数据
     *
     * @param cart
     */
    public void put(ShoppingCart cart) {
        //1,内存中增加
        ShoppingCart temp = datas.get(Integer.parseInt(cart.getProduct_id()));
        if (temp != null) {
            temp.setCount(temp.getCount() + 1);
        } else {
            temp = cart;
            temp.setCount(1);
        }
        datas.put(Integer.parseInt(cart.getProduct_id()), temp);

        //2,保持本地
        commit();

    }

    //保存数据到本地
    private void commit() {
//1,保持前要转换成List<ShoppingCart>
        List<ShoppingCart> list=spareToList();
        //2,还需要用Gson转换成Json格式的String
        String json=new Gson().toJson(list);

        //3,保持到sp
        CacheUtils.putString(mContext, CART_JSON, json);

    }

    /**
     * 把当前的 SparseArray<ShopingCart> 集合转换成 List<ShopingCart> 集合
     * @return
     */
    private List<ShoppingCart> spareToList() {
        List<ShoppingCart> list=new ArrayList<>();
        for(int i = 0; i < datas.size(); i++) {
          list.add(datas.valueAt(i));
        }
        return list;
    }

    /**
     * 更新数据
     *
     * @param cart
     */
    public void upsdate(ShoppingCart cart) {
        //1,在内存中更新
        datas.put(Integer.parseInt(cart.getProduct_id()), cart);

        //2,保存到本地
        commit();

    }

    /**
     * 删除数据
     *
     * @param cart
     */
    public void delete(ShoppingCart cart) {
        //1,在内存中更新
        datas.delete(Integer.parseInt(cart.getProduct_id()));
//        datas.remove(cart.getId());
        ShoppingCart temp = datas.get(Integer.parseInt(cart.getProduct_id()));
        //2,保存到本地
        commit();

    }

    public List<ShoppingCart> getAll() {
        return getDataFromLocal();
    }

    public ShoppingCart conversionData(GoodDetailBean.ResultBean.ProductInfoBean wares) {
        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.setProduct_id(wares.getProduct_id());
        shoppingCart.setName(wares.getName());
        shoppingCart.setChannel_id(wares.getChannel_id());
        shoppingCart.setBrand_id(wares.getBrand_id());
        shoppingCart.setP_catalog_id(wares.getP_catalog_id());
        shoppingCart.setSupplier_type(wares.getSupplier_type());
        shoppingCart.setSupplier_code(wares.getSupplier_code());
        shoppingCart.setCover_price(wares.getCover_price());
        shoppingCart.setBrief(wares.getBrief());
        shoppingCart.setFigure(wares.getFigure());
        shoppingCart.setAssoc_products(wares.isAssoc_products());
        shoppingCart.setSort_order(wares.getSort_order());
        shoppingCart.setSell_time_start(wares.getSell_time_start());
        shoppingCart.setSell_time_end(wares.getSell_time_end());
        shoppingCart.setOrigin_price(wares.getOrigin_price());
        shoppingCart.setSupplier_name(wares.getSupplier_name());
        shoppingCart.setMeiqia_ent_id(wares.getMeiqia_ent_id());
        shoppingCart.setTag_data(wares.getTag_data());
        shoppingCart.setMeiqia_ent_url(wares.getMeiqia_ent_url());
        shoppingCart.setIs_fav(wares.getIs_fav());
        shoppingCart.setDetail_url(wares.getDetail_url());
        shoppingCart.setPrice_gap_string(wares.getPrice_gap_string());
        shoppingCart.setTotal_price(wares.getTotal_price());
        shoppingCart.setFigure(wares.getFigure());

        return  shoppingCart;

    }
}
