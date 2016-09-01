package com.atguigu.mycyc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.bean.CartProvider;
import com.atguigu.mycyc.bean.GoodDetailBean;
import com.atguigu.mycyc.utils.CacheUtils;
import com.atguigu.mycyc.utils.Constant;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class GoodsDetailActivity extends Activity {

    private static final String GOODS_DETAIL_CAECH = "goods detail";
    private TextView tv_title;
    private ImageView btn_ok;
    private LinearLayout error_network;
    private ViewPager vp_good_imgs;
    private TextView tv_good_name;
    private TextView tv_good_price;
    private TextView tv_label_server;
    private TextView tv_btn_addCart;//加入购物车
    //需要显示的数据
    private GoodDetailBean.ResultBean.ProductInfoBean product_info;
    /**
     * 购物车信息维护类
     */
    private CartProvider cartProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_goods_detail_view);
        cartProvider=new CartProvider(this);//初始化购物车维护类
        initView();
        initTitle();
        initListener();
        initData();
    }

    private void initView() {
        tv_title = (TextView)findViewById(R.id.tv_title);
        btn_ok = (ImageView)findViewById(R.id.btn_ok);
        error_network = (LinearLayout)findViewById(R.id.error_network);//加载界面
        error_network.setVisibility(View.VISIBLE);
        vp_good_imgs = (ViewPager)findViewById(R.id.vp_good_imgs);//显示图片的viewpager
        tv_good_name = (TextView)findViewById(R.id.tv_good_name);//显示商品名字
        tv_good_price = (TextView)findViewById(R.id.tv_good_price);//显示商品价格
        tv_label_server = (TextView)findViewById(R.id.tv_label_server);//发货者
        tv_btn_addCart = (TextView)findViewById(R.id.tv_btn_addCart);//加购物车
    }

    private void initData() {
        String url = getIntent().getStringExtra(Constant.GOODS_DETAIL);
        //读取缓存
        String saveJson = CacheUtils.getString(this, GOODS_DETAIL_CAECH);
        if (!TextUtils.isEmpty(saveJson)) {
            procesData(saveJson);
        }
        getDataFromNet(url);
    }

    private void initListener() {
        tv_btn_addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartProvider.put(cartProvider.conversionData(product_info));
                Log.e("TAG", "添加购物车成功");
            }
        });

    }

    //返回
    public void back(View v) {
        finish();
    }

    private void initTitle() {
        tv_title.setText("商品详情");
        btn_ok.setVisibility(View.VISIBLE);
        btn_ok.setImageResource(R.drawable.icon_more);
    }

    private void getDataFromNet(String url) {
        //String url = urls[0];
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
                        Log.e("TAG", "请求数据出错了" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求成功
                        //保存json数据做缓存
                        CacheUtils.putString(GoodsDetailActivity.this, GOODS_DETAIL_CAECH, response);
                        //解析和显示数据
                        procesData(response);
                    }
                });

    }

    private void procesData(String response) {
        GoodDetailBean goodDetailBean = new Gson().fromJson(response, GoodDetailBean.class);
        GoodDetailBean.ResultBean result = goodDetailBean.getResult();
        product_info = result.getProduct_info();
        if(product_info !=null) {//有数据
            //显示界面
            //显示图片
            showGoodImage();
            //显示价格
            tv_good_name.setText(product_info.getName());
            //显示价格
            tv_good_price.setText("¥"+product_info.getCover_price());
            //显示发货
            tv_label_server.setText("由"+product_info.getSupplier_name()+"发货");

        }else {//没有数据
            Toast.makeText(GoodsDetailActivity.this, "请求数据为空", Toast.LENGTH_SHORT).show();
        }
        //请求数据成功过后隐藏加载界面
        error_network.setVisibility(View.GONE);
    }

    private void showGoodImage() {
        vp_good_imgs.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 1;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView iv=new ImageView(GoodsDetailActivity.this);
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(GoodsDetailActivity.this).load(product_info.getFigure()).into(iv);
                container.addView(iv);
                return iv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
                container.removeView((View) object);
            }
        });
    }
}
