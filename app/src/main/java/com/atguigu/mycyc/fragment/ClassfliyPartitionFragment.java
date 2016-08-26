package com.atguigu.mycyc.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.atguigu.mycyc.R;
import com.atguigu.mycyc.adapter.ClassflyRecyleViewAdapter;
import com.atguigu.mycyc.base.BaseFragment;
import com.atguigu.mycyc.bean.ClassfilyBean;
import com.atguigu.mycyc.utils.AppNetConfig;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;

/**
 * Created by 徐达
 * on 2016/8/26 on 9:01.
 * 作用:分类界面中的分类fragment
 */
public class ClassfliyPartitionFragment extends BaseFragment {
    @Bind(R.id.ll_classfily)
    ListView llClassfily;
    @Bind(R.id.rlv_classfiy_partion)
    RecyclerView rlvClassfiyPartion;
    private ArrayAdapter<String> listviewAdapter;
    //listview的数据
    private List<String> list;
    // private ClassflyListViewAdapter listviewAdapter;
    //点击的位置
    private int clickPosition;
    //recyle需要显示的数据
    private ClassfilyBean.ResultBean resultBean;
    private ClassflyRecyleViewAdapter recyleviewAdapter;

    @Override
    protected void initData(String content) {
        /*//读取缓存
        String saveJson = CacheUtils.getString(mContext, AppNetConfig.HOME);
        if (!TextUtils.isEmpty(saveJson)) {
            showData(saveJson,state);
        }*/

        if (!TextUtils.isEmpty(content)) {//数据不为空
            ClassfilyBean classfilyBean = new Gson().fromJson(content, ClassfilyBean.class);
            List<ClassfilyBean.ResultBean> result = classfilyBean.getResult();
            //设置listview
            //listviewAdapter = new ClassflyListViewAdapter(mContext, result);
            //准备集合数据
            list = new ArrayList<>();
            for (int i = 0; i < result.size(); i++) {
                list.add(result.get(i).getName());
            }
            listviewAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, list);
            llClassfily.setAdapter(listviewAdapter);
            //设置recyleview的显示
            resultBean = result.get(0);
            recyleviewAdapter = new ClassflyRecyleViewAdapter(mContext, resultBean);
            rlvClassfiyPartion.setAdapter(recyleviewAdapter);
            // rlvClassfiyPartion.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.VERTICAL,false));
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3, GridLayoutManager.VERTICAL, false);
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position < 1) {
                        return 3;
                    } else {

                        return 1;
                    }
                }
            });
            rlvClassfiyPartion.setLayoutManager(gridLayoutManager);
            //初始化监听
            initListenr();


        } else {
            Toast.makeText(this.getActivity(), "没有数据", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    private void initListenr() {
        //listview的item的点击监听
        llClassfily.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.RED);
                Toast.makeText(mContext, "name=" + list.get(position), Toast.LENGTH_SHORT).show();
                //根据点击的listview的位置来动态显示recyliview
                //得到需要显示点击的位置
                clickPosition = position;
                getDataFromNet(clickPosition);

            }
        });
    }

    private void getDataFromNet(int clickPosition) {
        //获取请求路径
       // String url = getPositionUrl(clickPosition);
        String url = AppNetConfig.CLASSFILY_YXZQ;
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
                        Log.e("TAG", "下拉刷新时候加载数据错误");
                    }

                    @Override
                    public void onResponse(String response, int id) {//请求成功
                        //展示RecyleView的数据

                        showRecyleViewData(response);
                    }
                });
    }

    /**
     * 解析请求得到的json数据并在recyleview上展示
     *
     * @param response
     */
    private void showRecyleViewData(String response) {
        ClassfilyBean classfilyBean = new Gson().fromJson(response, ClassfilyBean.class);
        List<ClassfilyBean.ResultBean> result = classfilyBean.getResult();
        //设置recyleview的显示
        resultBean = result.get(0);
        //更新适配器的显示
        String name = resultBean.getHot_product_list().get(0).getName();
        Log.e("TAG", "name==" + name);
        recyleviewAdapter.setResultBean(resultBean);
        recyleviewAdapter.notifyDataSetChanged();
//        recyleviewAdapter.notifyItemRangeChanged(0,resultBean.getChild().size()+1);
    }

    /**
     * 根据listview的点击位置获取相应的请求路径
     *
     * @param clickPosition
     * @return
     */
    private String getPositionUrl(int clickPosition) {
        return null;
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.CLASSFILY_CLASSFILYPATRATION;
    }

    @Override
    public int getLayoutId() {
        return R.layout.classfliy_partition_fragment;
    }


}
